package com.bestapp.bets.service;

import com.bestapp.UserBetMapper;
import com.bestapp.bets.domain.UserBet;
import com.bestapp.bets.exceptions.NotFoundException;
import com.bestapp.bets.repository.UserBetRepository;
import com.bestapp.mgr.domain.Match;
import com.bestapp.mgr.repository.MatchRepository;
import com.bestapp.usr.domain.User;
import com.bestapp.usr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserBetService {

    @Autowired
    private UserBetRepository userBetRepository;

    @Autowired
    private UserBetMapper userBetMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MatchRepository matchRepository;

    public List<UserBetDTO> findAll() {
        return userBetMapper.toDTO(userBetRepository.findAll());
    }

    public UserBetDTO create(UserBetDTO userBetDTO) {

        Optional<User> user = userRepository.findById(userBetDTO.getUser());
        user.orElseThrow(() -> new NotFoundException("User not found!!"));

        Optional<Match> match = matchRepository.findById(userBetDTO.getMatch());
        match.orElseThrow(() -> new NotFoundException("Match not found!!"));

        UserBet userBet = userBetMapper.toEntity(userBetDTO);
        userBet.setCreated(LocalDateTime.now());

        userBetRepository.save(userBet);

        return userBetMapper.toDTO(userBet);
    }

    public UserBetDTO findById(Long id) {
        Optional<UserBet> userBet = userBetRepository.findById(id);

        userBet.orElseThrow(() -> new NotFoundException("User not found!!"));
        return userBetMapper.toDTO(userBet.get());
    }
}
