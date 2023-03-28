package com.regpet.api.repositories;

import com.regpet.api.models.Rescue;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequestScoped
public class RescueRepository extends BaseRepository<Rescue, UUID> {
    @Override
    public Class<Rescue> getEntityClass() {
        return Rescue.class;
    }

    public List<Rescue> getByUser(UUID userId, Integer limit, Integer offset) {
        Query query = entityManager.createQuery("FROM Rescue r WHERE r.user.id = :userId");
        query.setParameter("userId", userId);
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        List<Rescue> resultList = query.getResultList();
        return (resultList != null && !resultList.isEmpty()) ? resultList : null;
    }

    public List<Rescue> getByTimeInterval(
            UUID userId, LocalDateTime start, LocalDateTime finish, Integer limit, Integer offset) {
        Query query = entityManager.createQuery("""
                FROM Rescue r WHERE r.user.id = :userId AND r.rescueDate BETWEEN :initial AND :final""");
        query.setParameter("userId", userId);
        query.setParameter("initial", start);
        query.setParameter("final", finish);
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        List<Rescue> resultList = query.getResultList();
        return (resultList != null && !resultList.isEmpty()) ? resultList : null;
    }
}
