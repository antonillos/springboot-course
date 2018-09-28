package com.bestapp.bets.web;

import com.bestapp.bets.service.UserBetDTO;
import com.bestapp.bets.service.UserBetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserBetResource {
    @Autowired
    private UserBetService userBetService;

    @RequestMapping(value = "/user-bets", method = RequestMethod.GET, produces = "application/json")
    public List<UserBetDTO> findAll() {
        return userBetService.findAll();
    }

}
