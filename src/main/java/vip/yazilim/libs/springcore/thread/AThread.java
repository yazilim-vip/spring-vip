package vip.yazilim.libs.springcore.thread;

import java.util.Calendar;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Async;

import vip.yazilim.libs.springcore.exception.general.ThreadException;
import vip.yazilim.libs.springcore.util.LogHelper;

public abstract class AThread {

    private boolean stopThread;
    private String threadName;
    private long threadInterval;

    // Error fields
    private boolean threadError; // any error occurred during the execution of the thread
    private int errorTryCountThreshold;
    private int tryCount;
    private long errorSleepTime;

    // --- Thread Initialization ----------

    @PostConstruct
    public void init() {
        this.stopThread = false;
        this.threadName = getThreadName();
        this.threadInterval = getThreadInterval();

        this.threadError = false;
        this.errorTryCountThreshold = getErrorTryCountThreshold();
        this.tryCount = 0;
        this.errorSleepTime = getErrorSleepTime();

        initThread();
    }

    /**
     * Used to initialize thread if needed
     */
    protected void initThread() {
    }

    protected abstract Logger getLogger();

    /**
     * It is just using for logging
     *
     * @return name of the thread
     */
    protected abstract String getThreadName();

    /**
     * Interval of the thread in milliseconds
     *
     * @return long value in milliseconds
     */
    protected abstract long getThreadInterval();

    /**
     * @return long value in milliseconds
     */
    protected int getErrorTryCountThreshold() {
        return 3;
    }

    /**
     * @return long value in milliseconds
     */
    protected int getErrorSleepTime() {
        return 15 * 60 * 1000;
    }

    // --- Thread Loop ----------

    @Async
    public void run() {

        final Logger LOGGER = getLogger();

        LOGGER.info(String.format("__%s Started__", threadName));
        preThreadRun();
        do {

            if (hasError()) {
                errorSleep();
                continue;
            }

            try {
                long startOfProcess = initializeThread();
                applyThreadAlgorithm();
                finalizeThread(startOfProcess);
            } catch (Exception e) {
                LOGGER.error("Handled " + getThreadName() + " Deamon Error!! :: " + e.getMessage(), e);
                raiseError();
            }

        } while (!this.stopThread);
        postThreadRun();
        LOGGER.info(String.format("__%s Ended__", threadName));
    }

    protected void preThreadRun() {
    }

    private long initializeThread() throws ThreadException {

        final Logger LOGGER = getLogger();

        try {
            LOGGER.info(String.format("__%s Loop Started__", threadName));
            return Calendar.getInstance().getTimeInMillis(); // get current time
        } catch (Exception e) {
            throw new ThreadException("Error while initializing thread", e);
        }
    }

    private void applyThreadAlgorithm() {

        final Logger LOGGER = getLogger();

        try {
            doThreadJob();
        } catch (Exception e) {
            LogHelper.logException(LOGGER, e);
            incrementTryCount();
        }
    }

    protected abstract void doThreadJob() throws Exception;

    private void finalizeThread(long startOfProcess) throws ThreadException {

        final Logger LOGGER = getLogger();

        try {
            long endOfProcess = Calendar.getInstance().getTimeInMillis();
            LOGGER.info(String.format("__%s Loop Ended__", threadName));

            // calculate remaining time to sleep thread
            long execTimeOfProcess = endOfProcess - startOfProcess;
            long remaining = (this.threadInterval - execTimeOfProcess);
            if ((remaining) > 0) {
                sleepThread(remaining);
            }

        } catch (Exception e) {
            throw new ThreadException("Error while initializing thread", e);
        }
    }

    protected void postThreadRun() {
    }

    // --- Thread Helper Methods ----------

    /**
     * To sleep thread
     *
     * @param millis sleep duration
     * @throws InterruptedException error
     */
    private void sleepThread(long millis) throws InterruptedException {
        Thread.sleep(millis);
    }

    private void errorSleep() {

        final Logger LOGGER = getLogger();

        try {
            sleepThread(this.errorSleepTime);
            removeError();
        } catch (InterruptedException e) {
            LOGGER.error("Handled " + getThreadName() + " Deamon Error!! :: " + e.getMessage(), e);
            raiseError();
        }
    }

    /**
     * To raise an error flag for the threads
     */
    private void raiseError() {
        this.threadError = true;
    }

    /**
     * To remove error flag of the thread
     */
    private void removeError() {
        this.threadError = false;
        this.tryCount = 0;
    }

    private boolean hasError() {
        return this.threadError;
    }

    private void incrementTryCount() {
        if (++this.tryCount > this.errorTryCountThreshold) {
            raiseError();
        }
    }

    protected void stopThread() {
        this.stopThread = true;
    }
}
