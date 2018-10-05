package com.bestapp.mgr.repository;

import com.bestapp.mgr.domain.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class MatchRepository {

    private final EntityManager em;

    @Autowired
    public MatchRepository(EntityManager em) {
        this.em = em;
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Match> findAll() {
        return em.createQuery("from Match").getResultList();
    }

    public Optional<Match> findById(Long id) {
        return Optional.of(em.find(Match.class, id));
    }

    @Transactional
    public void saveOrUpdate(Match match) {
        if (match.getId() != null && match.getId() > 0) {
            em.merge(match);
        } else {
            em.persist(match);
        }
    }
}
