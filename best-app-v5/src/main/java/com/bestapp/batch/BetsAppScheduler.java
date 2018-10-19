package com.bestapp.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class BetsAppScheduler {

    private static final Logger logger = LoggerFactory.getLogger(BetsAppScheduler.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job importBetsJob;

    @Scheduled(cron = "0 */2 * * * *")
    public void executeJob() {

        JobParameters params = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();

        try {
            JobExecution execution = jobLauncher.run(importBetsJob, params);

            logger.info("Job: {}", execution.getJobId().toString());
            logger.info("Starts at: {}", execution.getStartTime().toString());

        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
                | JobParametersInvalidException e) {
            logger.error("Error running job", e);
        }

    }

}