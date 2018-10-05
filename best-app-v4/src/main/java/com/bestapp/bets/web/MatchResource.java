package com.bestapp.bets.web;

import com.bestapp.mgr.domain.Match;
import com.bestapp.mgr.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MatchResource {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchResource(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @GetMapping(value = "/matches", produces = "application/json")
    public List<Match> findAll() {
        return matchRepository.findAll();
    }

}
