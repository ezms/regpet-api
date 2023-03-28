package com.regpet.api.repositories;

import com.regpet.api.models.Denunciation;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequestScoped
public class DenunciationRepository extends BaseRepository<Denunciation, UUID> {
    @Override
    public Class<Denunciation> getEntityClass() {
        return Denunciation.class;
    }

    public List<Denunciation> getByUser(UUID userId, Integer limit, Integer offset) {
        Query query = entityManager.createQuery("FROM Denunciation d WHERE d.user.id = :userId");
        query.setParameter("userId", userId);
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        List<Denunciation> resultList = query.getResultList();
        return (resultList != null && !resultList.isEmpty()) ? resultList : null;
    }

    public List<Denunciation> getByTimeInterval(
            UUID userId, LocalDateTime start, LocalDateTime finish, Integer limit, Integer offset) {
        Query query = entityManager.createQuery("""
                FROM Denunciation d WHERE d.user.id = :userId AND d.denunciationDate BETWEEN :initial AND :final""");
        query.setParameter("userId", userId);
        query.setParameter("initial", start);
        query.setParameter("final", finish);
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        List<Denunciation> resultList = query.getResultList();
        return (resultList != null && !resultList.isEmpty()) ? resultList : null;
    }
}
