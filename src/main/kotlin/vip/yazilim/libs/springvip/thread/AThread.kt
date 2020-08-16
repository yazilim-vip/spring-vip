package vip.yazilim.libs.springvip.thread

import java.time.Instant
import java.time.ZoneId
import java.util.*

abstract class AThread(
        // Name of the thread
        private val threadName: String

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
    var threadFittingToInterval: Boolean = false
        private set
    var stopThread: Boolean = false
        private set
    var threadError: Boolean = false // any error occurred during the execution of the thread
        private set
    var tryCount: Int = 0
        private set
    var goodMorningFlag: Boolean = false
        private set
    var preJobOverflowTime: Long = 0
        private set
    var preJobHasErrorFlag = false
        private set
    var startOfJobTime: Long = 0
        private set

    // --- Getters ----------


    // --- Logging Methods ----------
    abstract fun logError(msg: String, throwable: Throwable)
    abstract fun logWarn(msg: String, throwable: Throwable)
    abstract fun logWarn(msg: String)
    abstract fun logInfo(msg: String)
    abstract fun logDebug(msg: String)
    abstract fun logTrace(msg: String)

    // --- Pre/Post methods ----------
    open fun preThreadRun() {}
    open fun postThreadRun() {}

    // --- Thread Loop ----------
    fun run() {
        logTrace("__$threadName Started__")
        preThreadRun()
        do {
            try {
                if (threadError) {
                    errorSleep()
                    threadFittingToInterval = false
                    continue
                }

                initializeThread()
                if (threadFittingToInterval) {
                    threadFittingToInterval = false
                } else if (goodMorningFlag || firstJob || preJobHasErrorFlag) {
                    fitJobIntoInterval()
                    goodMorningFlag = false
                    threadFittingToInterval = true
                    continue
                }
                applyThreadAlgorithm()
                finalizeThread()
            } catch (e: Exception) {
                logError("Handled $threadName Deamon Error!! :: ${e.message} ", e)
                raiseError()
            } finally {
                firstJob = false
            }
        } while (!stopThread)
        postThreadRun()
        logTrace("__$threadName Ended__")
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
                logDebug("$threadName Fitting first JOB into starting second of minute. Wait=$waitMillis")
            }
            goodMorningFlag -> {
                logDebug("$threadName Wakes up new. Fitting JOB.  Wait=$waitMillis")
            }
            else -> {
                logDebug("$threadName handling previous JOB is taking more time than normal. Overflow=$preJobOverflowTime Wait=$waitMillis")
            }
        }
        sleepThread(waitMillis)
        setPreJobOverflow(totalOverflow)
    }

    private fun applyThreadAlgorithm() {
        preJobHasErrorFlag = try {
            if (tryCount > 0) {
                logDebug("Try[$tryCount]")
            }
            logTrace("__$threadName Loop Started__")
            doThreadJob(startOfJobTime, preJobOverflowTime, preJobHasErrorFlag)
            logTrace("__$threadName Loop Ended__")
            false
        } catch (e: Exception) {
            logError("Error", e)
            incrementTryCount()
            true
        }
    }

    /**
     * The main JOB of the thread that will be applied repeatedly
     * @param startOfProcess start time of the Thread JOB in millis
     * @param preJobOverflowTime overflow of the previous Thread JOB in milliseconds. It will be 0 if no overflow
     * @throws Exception
     */
    @Throws(Exception::class)
    protected abstract fun doThreadJob(startOfProcess: Long, preJobOverflowTime: Long, preJobHasError: Boolean)

    private fun finalizeThread() {
        val endOfProcess = Calendar.getInstance().timeInMillis

        // calculate remaining time to sleep thread
        val execTimeOfProcess = endOfProcess - startOfJobTime
        val remaining = threadInterval - execTimeOfProcess
        logDebug("$threadName Execution Start=$startOfJobTime"
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
        logDebug("An error occurred while doing thread JOB. Sleeping $threadName for $errorSleepTime")
        sleepThread(errorSleepTime)
        removeError()
        goodMorningFlag = true
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
        preJobHasErrorFlag = (preJobOverflowTime > 0)
    }

    // --- Public Methods ----------
    fun stopThread() {
        stopThread = true
    }
}