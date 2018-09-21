package com.bestapp.batch;

import com.bestapp.mgr.domain.Match;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class MatchItemReader {
    private static final String[] NAMES = {"local", "rellocal", "visitor", "relvisitor", "reldraw", "open", "close"};

    @Bean
    public FlatFileItemReader<Match> readFileDelimiter(Resource in) {
        return new FlatFileItemReaderBuilder<Match>()
                .name("file-reader")
                .resource(in)
                .targetType(Match.class)
                .linesToSkip(1)
                .delimited().delimiter(",").names(NAMES)
                .build();
    }
}
