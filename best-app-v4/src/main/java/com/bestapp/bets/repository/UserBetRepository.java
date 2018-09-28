package com.bestapp.bets.repository;

import com.bestapp.bets.domain.UserBet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBetRepository extends JpaRepository<UserBet, Long> {

}
