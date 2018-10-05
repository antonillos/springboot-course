package com.bestapp.bets.web;

import com.bestapp.bets.service.UserBetDTO;
import com.bestapp.bets.service.UserBetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserBetResource {
    @Autowired
    private UserBetService userBetService;

    @GetMapping(value = "/user-bets", produces = "application/json")
    public List<UserBetDTO> findAll() {
        return userBetService.findAll();
    }

    @PostMapping(value = "/user-bets")
    public UserBetDTO create(@RequestBody UserBetDTO userBetDTO) {
        return userBetService.create(userBetDTO);
    }


}
