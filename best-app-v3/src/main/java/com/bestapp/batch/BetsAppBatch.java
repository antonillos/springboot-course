package com.bestapp.batch;

import com.bestapp.BestAppProperties;
import com.bestapp.mgr.domain.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
@EnableBatchProcessing
public class BetsAppBatch {
    private static final Logger logger = LoggerFactory.getLogger(BetsAppBatch.class);

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

//    @Value("${betsconf.file-to-process}")
//    private Resource in;

    @Autowired
    private BestAppProperties properties;

    @Bean
    public Job importMatchesJob(MatchItemReader reader, MatchItemProcessor processor, MatchItemWriter writer)
            throws Exception {

        Step s1 = stepBuilderFactory.get("import-file-step")
                .<Match, Match>chunk(5)
                .reader(reader.readFileDelimiter(properties.getFileToProcessAsResource()))
                .processor(processor.processBet())
                .writer(writer.matchJdbcBatchItemWriter(null))
                .build();

        Step s2 = stepBuilderFactory.get("rename-file").tasklet(new Tasklet() {
            @Override public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
                    throws Exception {
                File oldFile = properties.getFileToProcessAsResource().getFile();
                File newFile =
                        new File(oldFile.getParent(), oldFile.getName().concat(".processed"));
                if (oldFile.exists()) {
                    if (oldFile.renameTo(newFile)) {
                        logger.info("File has been renamed...");
                    } else {
                        logger.error("File cannot be renamed!!");
                    }
                }
                return RepeatStatus.FINISHED;
            }
        }).build();

        return jobBuilderFactory.get("import-matches-job")
                .start(s1).next(s2).incrementer(new RunIdIncrementer())
                .build();
    }
}
