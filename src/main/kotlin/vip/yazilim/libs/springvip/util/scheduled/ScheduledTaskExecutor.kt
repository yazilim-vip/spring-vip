package vip.yazilim.libs.springvip.util.scheduled

import org.slf4j.Logger
import java.time.Instant
import java.time.ZoneId
import java.util.*

open class ScheduledTaskExecutor(
        private val log: Logger

        // Name of the thread
        , private val threadName: String

        // Interval of the thread in milliseconds
        , private val threadInterval: Long

        // Threshold to try on error condition
        , private val errorTryCountThreshold: Int

        // Time to sleep on error in milliseconds
        , private val errorSleepTime: Long
) {

    // --- Properties ----------
    var firstJob: Boolean = true
        private set

    var currentThreadJobFitsIntoIntervalFlag: Boolean = false
        private set

    var stopThread: Boolean = false
        private set

    var threadError: Boolean = false // any error occurred during the execution of the thread
        private set

    var tryCount: Int = 0
        private set

    var firstJobAfterErrorFlag: Boolean = false
        private set

    var preJobOverflowTime: Long = 0
        private set

    var preJobHasOverflowFlag: Boolean = false
        private set

    var preJobHasErrorFlag = false
        private set

    var startOfJobTime: Long = 0
        private set

    // --- Pre/Post methods ----------
    open fun preThreadRun() {}
    open fun postThreadRun() {}

    // --- Thread Loop ----------
    fun run(task: () -> Unit) {
        log.trace("__$threadName Started__")
        preThreadRun()
        do {
            try {
                if (threadError) {
                    errorSleep()
                    firstJobAfterErrorFlag = true
                    currentThreadJobFitsIntoIntervalFlag = false
                    continue
                }

                initializeThread()
                if (currentThreadJobFitsIntoIntervalFlag) {
                    currentThreadJobFitsIntoIntervalFlag = false
                } else if (firstJob || firstJobAfterErrorFlag || preJobHasOverflowFlag) {
                    fitJobIntoInterval()
                    firstJobAfterErrorFlag = false
                    continue
                }
                applyThreadAlgorithm(task)
                finalizeThread()
            } catch (e: Exception) {
                log.error("Handled $threadName Deamon Error!! :: ${e.message} ", e)
                raiseError()
            } finally {
                firstJob = false
            }
        } while (!stopThread)
        postThreadRun()
        log.trace("__$threadName Ended__")
    }

    private fun initializeThread() {
        startOfJobTime = Calendar.getInstance().timeInMillis; // get current time
    }

    /**
     *
     * @return total overflow
     */
    @Throws(InterruptedException::class)
    private fun fitJobIntoInterval() {
        val instant = Instant.ofEpochMilli(startOfJobTime)
        val date = instant.atZone(ZoneId.systemDefault()).toLocalDateTime()
        val waitSecond = 60 - date.second.toLong()
        val waitMillis = waitSecond * 1000
        val totalOverflow = preJobOverflowTime + waitMillis
        when {
            firstJob -> {
                log.debug("$threadName Fitting first JOB into starting second of minute. Wait=$waitMillis")
            }
            firstJobAfterErrorFlag -> {
                log.debug("$threadName Wakes up new. Fitting JOB.  Wait=$waitMillis")
            }
            preJobHasOverflowFlag -> {
                log.debug("$threadName handling previous JOB is taking more time than normal. Overflow=$preJobOverflowTime Wait=$waitMillis")
            }
        }
        sleepThread(waitMillis)
        setPreJobOverflow(totalOverflow)
        currentThreadJobFitsIntoIntervalFlag = true
    }

    private fun applyThreadAlgorithm(task: () -> Unit) {
        preJobHasErrorFlag = try {
            if (tryCount > 0) {
                log.debug("Try[$tryCount]")
            }
            log.trace("__$threadName Loop Started__")
            task.invoke()
            log.trace("__$threadName Loop Ended__")
            false
        } catch (e: Exception) {
            log.error("Error", e)
            incrementTryCount()
            true
        }
    }

    private fun finalizeThread() {
        val endOfProcess = Calendar.getInstance().timeInMillis

        // calculate remaining time to sleep thread
        val execTimeOfProcess = endOfProcess - startOfJobTime
        val remaining = threadInterval - execTimeOfProcess
        log.debug("$threadName Execution Start=$startOfJobTime"
                + ", End=$endOfProcess"
                + ", Duration=$execTimeOfProcess"
                + ", Interval=$threadInterval"
                + ", Remaining=$remaining"
        )
        if (remaining > 0) {
            sleepThread(remaining)
            return
        }
        setPreJobOverflow(-remaining)
    }

    // --- Thread Helper Methods ----------
    /**
     * To sleep thread
     *
     * @param millis sleep duration
     * @throws InterruptedException error
     */
    @Throws(InterruptedException::class)
    private fun sleepThread(millis: Long) {
        Thread.sleep(millis)
    }

    private fun errorSleep() {
        log.debug("An error occurred while doing thread JOB. Sleeping $threadName for $errorSleepTime")
        sleepThread(errorSleepTime)
        removeError()
    }

    /**
     * To remove error flag of the thread
     */
    private fun removeError() {
        threadError = false
        tryCount = 0
    }

    /**
     * To increment try count
     */
    private fun incrementTryCount() {
        if (++tryCount > errorTryCountThreshold) {
            raiseError()
        }
    }

    /**
     * To raise an error flag for the threads
     */
    private fun raiseError() {
        threadError = true
    }

    private fun setPreJobOverflow(preJobOverflowTime: Long) {
        this.preJobOverflowTime = preJobOverflowTime
        preJobHasOverflowFlag = (preJobOverflowTime > 0)
    }

    // --- Public Methods ----------
    fun stopThread() {
        stopThread = true
    }
}