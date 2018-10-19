package com.bestapp.mgr.repository;

import com.bestapp.mgr.domain.Match;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MatchRepositoryTest {

    @Autowired
    private MatchRepository repository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void createTest() {

        Match match = new Match();
        match.setLocal("LOCAL");
        match.setVisitor("VISITOR");
        match.setOpen(LocalDateTime.now());
        match.setClose(LocalDateTime.now());

        repository.saveOrUpdate(match);

        List<Match> matches = repository.findAll();

        assertThat(matches).isNotEmpty();
    }

}