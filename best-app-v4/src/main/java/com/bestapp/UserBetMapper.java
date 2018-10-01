package com.bestapp;

import com.bestapp.bets.domain.UserBet;
import com.bestapp.bets.service.UserBetDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public class UserBetMapper {
    @Mappings({
            @Mapping(source = "user.id", target = "user"),
            @Mapping(source = "match.id", target = "match")
    })
    public UserBetDTO toDTO(UserBet userBet) {
        return null;
    };

    public List<UserBetDTO> toDTO(List<UserBet> userBets) {
        return null;
    };

}
