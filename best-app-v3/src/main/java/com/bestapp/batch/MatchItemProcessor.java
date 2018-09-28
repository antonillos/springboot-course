package com.bestapp.batch;

import com.bestapp.mgr.domain.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MatchItemProcessor {
    private static final Logger logger = LoggerFactory.getLogger(MatchItemProcessor.class);

    @Bean
    public ItemProcessor<Match, Match> processBet() {
        return match -> {
            logger.info("----> Match :" + match);
            return match;
        };
    }

}
