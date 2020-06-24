package vip.yazilim.libs.springvip.thread

import org.slf4j.Logger
import org.springframework.scheduling.annotation.Async
import java.util.*
import javax.annotation.PostConstruct


/**
 * @author Emre Sen, 24.06.2020
 * @contact yazilim.vip
 */

abstract class AThread {
    private var stopThread = false
    private var threadName: String? = null
    private var threadInterval: Long = 0

    // Error fields
    private var threadError // any error occurred during the execution of the thread
            = false
    private var errorTryCountThreshold = 0
    private var tryCount = 0
    private var errorSleepTime: Long = 0

    // --- Thread Initialization ----------
    @PostConstruct
    fun init() {
        stopThread = false
        threadName = getThreadName()
        threadInterval = getThreadInterval()
        threadError = false
        errorTryCountThreshold = getErrorTryCountThreshold()
        tryCount = 0
        errorSleepTime = getErrorSleepTime().toLong()
        initThread()
    }

    /**
     * Used to initialize thread if needed
     */
    protected open fun initThread() {}
    protected abstract val logger: Logger

    /**
     * It is just using for logging
     *
     * @return name of the thread
     */
    protected abstract fun getThreadName(): String

    /**
     * Interval of the thread in milliseconds
     *
     * @return long value in milliseconds
     */
    protected abstract fun getThreadInterval(): Long

    /**
     * @return long value in milliseconds
     */
    protected open fun getErrorTryCountThreshold(): Int {
        return 3
    }

    /**
     * @return long value in milliseconds
     */
    protected open fun getErrorSleepTime(): Int {
        return 15 * 60 * 1000
    }

    // --- Thread Loop ----------
    @Async
    fun run() {
        logger.info(String.format("__%s Started__", threadName))
        preThreadRun()
        do {
            if (hasError()) {
                errorSleep()
                continue
            }
            try {
                val startOfProcess = initializeThread()
                applyThreadAlgorithm()
                finalizeThread(startOfProcess)
            } catch (e: Exception) {
                logger.error("Handled " + getThreadName() + " Deamon Error!! :: " + e.message, e)
                raiseError()
            }
        } while (!stopThread)
        postThreadRun()
        logger.info(String.format("__%s Ended__", threadName))
    }

    protected open fun preThreadRun() {}

    private fun initializeThread(): Long {
        return try {
            logger.info(String.format("__%s Loop Started__", threadName))
            Calendar.getInstance().timeInMillis // get current time
        } catch (e: Exception) {
            throw RuntimeException("Error while initializing thread", e)
        }
    }

    private fun applyThreadAlgorithm() {
        try {
            doThreadJob()
        } catch (e: Exception) {
            logger.error("Error", e);
            incrementTryCount()
        }
    }

    @Throws(Exception::class)
    protected abstract fun doThreadJob()
    private fun finalizeThread(startOfProcess: Long) {
        try {
            val endOfProcess = Calendar.getInstance().timeInMillis
            logger.info(String.format("__%s Loop Ended__", threadName))

            // calculate remaining time to sleep thread
            val execTimeOfProcess = endOfProcess - startOfProcess
            val remaining = threadInterval - execTimeOfProcess
            if (remaining > 0) {
                sleepThread(remaining)
            }
        } catch (e: Exception) {
            throw RuntimeException("Error while finilize thread", e)
        }
    }

    protected open fun postThreadRun() {}

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
        try {
            sleepThread(errorSleepTime)
            removeError()
        } catch (e: InterruptedException) {
            logger.error("Handled " + getThreadName() + " Deamon Error!! :: " + e.message, e)
            raiseError()
        }
    }

    /**
     * To raise an error flag for the threads
     */
    private fun raiseError() {
        threadError = true
    }

    /**
     * To remove error flag of the thread
     */
    private fun removeError() {
        threadError = false
        tryCount = 0
    }

    private fun hasError(): Boolean {
        return threadError
    }

    private fun incrementTryCount() {
        if (++tryCount > errorTryCountThreshold) {
            raiseError()
        }
    }

    protected fun stopThread() {
        stopThread = true
    }
}
