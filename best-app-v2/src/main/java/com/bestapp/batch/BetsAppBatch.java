package com.bestapp.batch;

import com.bestapp.mgr.domain.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
@EnableBatchProcessing
public class BetsAppBatch {
    private static final Logger logger = LoggerFactory.getLogger(BetsAppBatch.class);

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Value("${betsconf.file-to-process}")
    private Resource in;

    @Bean
    public Job importMatchesJob(MatchItemReader reader, MatchItemProcessor processor, MatchItemWriter writer)
            throws Exception {

        Step s1 = stepBuilderFactory.get("import-file-step")
                .<Match, Match>chunk(5)
                .reader(reader.readFileDelimiter(in))
                .processor(processor.processBet())
                .writer(writer.matchJdbcBatchItemWriter(null))
                .build();

        return jobBuilderFactory.get("import-matches-job")
                .start(s1).incrementer(new RunIdIncrementer())
                .build();
    }
}
