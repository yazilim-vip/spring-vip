package vip.yazilim.libs.springvip.thread

import java.time.Instant
import java.time.ZoneId
import java.util.*
import java.util.logging.Logger

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
    private var firstJob: Boolean = true
    private var threadFittingToInterval: Boolean = false
    private var stopThread: Boolean = false
    private var threadError: Boolean = false // any error occurred during the execution of the thread
    private var tryCount: Int = 0
    private var goodMorningFlag: Boolean = false
    private var preJobOverflowTime: Long = 0
    private var preJobHasErrorFlag = false


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
        threadLoop()
        postThreadRun()
        logTrace("__$threadName Ended__")
    }

    private fun threadLoop() {
        do {
            try {
                if(handleError()){
                    continue
                }

                if (tryCount > 0) {
                    logDebug("Try[$tryCount]")
                }

                val startOfProcess = initializeThread()
                if (threadFittingToInterval) {
                    threadFittingToInterval = false
                } else if (goodMorningFlag || firstJob || preJobHasOverflow()) {
                    preJobOverflowTime = fitIntoInterval(startOfProcess, preJobOverflowTime)
                    goodMorningFlag = false
                    threadFittingToInterval = true
                    continue
                }

                preJobHasErrorFlag = applyThreadAlgorithm(startOfProcess, preJobOverflowTime, preJobHasErrorFlag)
                preJobOverflowTime = finalizeThread(startOfProcess)
            } catch (e: Exception) {
                logError("Handled $threadName Deamon Error!! :: ${e.message} ", e)
                raiseError()
            } finally {
                firstJob = false
            }
        } while (!stopThread)
    }

    private fun handleError(): Boolean{
        if (threadError) {
            errorSleep()
            threadFittingToInterval = false
            return true
        }
        return false
    }

    private fun initializeThread(): Long {
        return Calendar.getInstance().timeInMillis; // get current time
    }

    /**
     *
     * @param startOfProcess
     * @param preJobOverflowTime
     * @return total overflow
     */
    @Throws(InterruptedException::class)
    private fun fitIntoInterval(startOfProcess: Long, preJobOverflowTime: Long): Long {
        val instant = Instant.ofEpochMilli(startOfProcess)
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
        return totalOverflow
    }

    private fun applyThreadAlgorithm(startOfProcess: Long, preJobOverflowTime: Long, preJobHasError: Boolean): Boolean {
        return try {
            logTrace("__$threadName Loop Started__")
            doThreadJob(startOfProcess, preJobOverflowTime, preJobHasError)
            logTrace("__$threadName Loop Ended__")
            false;
        } catch (e: Exception) {
            logError("Error", e)
            incrementTryCount()
            true;
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

    private fun finalizeThread(startOfProcess: Long): Long {
        val endOfProcess = Calendar.getInstance().timeInMillis

        // calculate remaining time to sleep thread
        val execTimeOfProcess = endOfProcess - startOfProcess
        val remaining = threadInterval - execTimeOfProcess
        logDebug("$threadName Execution Start=$startOfProcess"
                + ", End=$endOfProcess"
                + ", Duration=$execTimeOfProcess"
                + ", Interval=$threadInterval"
                + ", Remaining=$remaining"
        )
        if (remaining > 0) {
            sleepThread(remaining)
            return 0
        }
        return -remaining
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


    private fun preJobHasOverflow() : Boolean{
        logg
        return preJobOverflowTime > 0
    }

    // --- Public Methods ----------
    fun stopThread() {
        stopThread = true
    }
}