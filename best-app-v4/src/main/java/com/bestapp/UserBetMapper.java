package com.bestapp;

import com.bestapp.bets.domain.UserBet;
import com.bestapp.bets.service.UserBetDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserBetMapper {
    @Mappings({
            @Mapping(source = "user.id", target = "user"),
            @Mapping(source = "match.id", target = "match")
    })
    UserBetDTO toDTO(UserBet userBet);

    List<UserBetDTO> toDTO(List<UserBet> userBets);

}
