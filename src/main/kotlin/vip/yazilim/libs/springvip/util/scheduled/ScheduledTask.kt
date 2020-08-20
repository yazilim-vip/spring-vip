package vip.yazilim.libs.springvip.util.scheduled

import org.slf4j.Logger

abstract class ScheduledTask(
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
    private val scheduledTaskExecutor = ScheduledTaskExecutor(log, threadName, threadInterval, errorTryCountThreshold, errorSleepTime)

    fun run() {
        scheduledTaskExecutor.run(this::doThreadJob)
    }

    /**
     * The main JOB of the thread that will be applied repeatedly
     * @throws Exception
     */
    @Throws(Exception::class)
    protected abstract fun doThreadJob()

}