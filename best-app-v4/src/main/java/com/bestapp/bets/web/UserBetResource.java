package com.bestapp.bets.web;

import com.bestapp.bets.service.UserBetDTO;
import com.bestapp.bets.service.UserBetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserBetResource {
    private final UserBetService userBetService;

    @Autowired
    public UserBetResource(UserBetService userBetService) {
        this.userBetService = userBetService;
    }

    @GetMapping(value = "/user-bets", produces = "application/json")
    public List<UserBetDTO> findAll() {
        return userBetService.findAll();
    }

    @PostMapping(value = "/user-bets")
    public ResponseEntity<UserBetDTO> create(@Valid @RequestBody UserBetDTO userBetDTO) {
        return new ResponseEntity<>(userBetService.create(userBetDTO), HttpStatus.CREATED);
    }

    @GetMapping(value = "/user-bets/{id}")
    public Resource<UserBetDTO> findById(@PathVariable("id") Long id) {
        UserBetDTO userBetDTO = userBetService.findById(id);

        ControllerLinkBuilder linkBuilder =
                ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).findAll());

        Resource<UserBetDTO> resource = new Resource<>(userBetDTO);
        resource.add(linkBuilder.withRel("findAll"));

        return resource;
    }

}
