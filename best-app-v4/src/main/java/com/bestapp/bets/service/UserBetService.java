package com.bestapp.bets.service;

import com.bestapp.UserBetMapper;
import com.bestapp.bets.repository.UserBetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBetService {

    @Autowired
    private UserBetRepository userBetRepository;

    @Autowired
    private UserBetMapper userBetMapper;

    public List<UserBetDTO> findAll() {
        return userBetMapper.toDTO(userBetRepository.findAll());
    }

}
