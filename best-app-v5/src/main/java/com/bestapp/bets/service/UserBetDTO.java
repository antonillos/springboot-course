package com.bestapp.bets.service;

import com.bestapp.mgr.domain.MatchResult;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.StringJoiner;

public class UserBetDTO implements Serializable {

    private Long id;

    @NotNull
    private Long user;

    @NotNull
    private Long match;

    @NotNull
    private MatchResult result;

    @NotNull
    @Min(value = 1)
    private Double amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getMatch() {
        return match;
    }

    public void setMatch(Long match) {
        this.match = match;
    }

    public MatchResult getResult() {
        return result;
    }

    public void setResult(MatchResult result) {
        this.result = result;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override public String toString() {
        return new StringJoiner(", ", UserBetDTO.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("user=" + user)
                .add("match=" + match)
                .add("result=" + result)
                .add("amount=" + amount)
                .toString();
    }
}

