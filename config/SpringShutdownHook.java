package com.techmahindra.smartparking.config;

import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.SmartLifecycle;

/**
 * {@link SmartLifecycle} implementation to interrupt all currently executing Quartz jobs to allow them to complete
 * processing gracefully.
 */

public class SpringShutdownHook implements SmartLifecycle {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringShutdownHook.class);

    private boolean isRunning = false;


    private Scheduler scheduler;

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(final Runnable runnable) {
        stop();
        runnable.run();
    }

    @Override
    public void start() {
        LOGGER.info("SmartParking ShutdownHook started.");
        isRunning = true;
    }

    @Override
    public void stop() {
        LOGGER.info("SmartParking Spring container is shutting down.");
        isRunning = false;

        try {
            interruptJobs();

            // Tell the scheduler to shutdown allowing jobs to complete
            scheduler.shutdown(true);
        } catch (SchedulerException e) {
            try {
                // Something has gone wrong so tell the scheduler to shutdown without allowing jobs to complete.
                scheduler.shutdown(false);
            } catch (SchedulerException ex) {
                LOGGER.error("Unable to shutdown the Quartz scheduler.", ex);
            }
        }
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public int getPhase() {
        return Integer.MAX_VALUE;
    }

    private void interruptJobs() throws SchedulerException {
        for (JobExecutionContext jobExecutionContext : scheduler.getCurrentlyExecutingJobs()) {

            final JobDetail jobDetail = jobExecutionContext.getJobDetail();
            LOGGER.info("Interrupting job key=[{}], group=[{}].", jobDetail.getKey().getName(),
                        jobDetail.getKey().getGroup());
            scheduler.interrupt(jobDetail.getKey());
        }
    }

    @Required
    public void setScheduler(final Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
