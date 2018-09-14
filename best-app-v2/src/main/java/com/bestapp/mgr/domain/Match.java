package com.bestapp.mgr.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "MGR_MATCHES")
public class Match implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String local;

    @NotEmpty
    private String visitor;

    @Min(1)
    private Integer relLocal;

    @Min(1)
    private Integer relVisitor;

    @Min(1)
    private Integer relDraw;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime open;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime close;

    @Enumerated(EnumType.ORDINAL)
    private MatchResult result;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getVisitor() {
        return visitor;
    }

    public void setVisitor(String visitor) {
        this.visitor = visitor;
    }

    public Integer getRelLocal() {
        return relLocal;
    }

    public void setRelLocal(Integer relLocal) {
        this.relLocal = relLocal;
    }

    public Integer getRelVisitor() {
        return relVisitor;
    }

    public void setRelVisitor(Integer relVisitor) {
        this.relVisitor = relVisitor;
    }

    public Integer getRelDraw() {
        return relDraw;
    }

    public void setRelDraw(Integer relDraw) {
        this.relDraw = relDraw;
    }

    public LocalDateTime getOpen() {
        return open;
    }

    public void setOpen(LocalDateTime open) {
        this.open = open;
    }

    public LocalDateTime getClose() {
        return close;
    }

    public void setClose(LocalDateTime close) {
        this.close = close;
    }

    public MatchResult getResult() {
        return result;
    }

    public void setResult(MatchResult result) {
        this.result = result;
    }

}

