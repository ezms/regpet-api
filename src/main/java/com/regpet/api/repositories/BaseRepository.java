package com.regpet.api.repositories;

import com.regpet.api.exceptions.NotFoundException;
import com.regpet.api.interfaces.ICrudMethods;
import com.regpet.api.interfaces.IEntityDefault;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Transactional
public abstract class BaseRepository<E extends IEntityDefault<T>, T> implements ICrudMethods<E> {

    @Inject
    EntityManager entityManager;

    public abstract Class<E> getEntityClass();

    public static final Integer DEFAULT_MAX_RESULTS = 20;
    public static final Integer DEFAULT_OFFSET_RESULTS = 0;

    @Override
    public E add(E object) {
        entityManager.persist(object);
        entityManager.flush();
        return object;
    }

    @Override
    public List<E> getAll() {
        Query query = entityManager.createQuery("FROM " + getEntityClass().getName());
        List<E> objectList = query.getResultList();
        return (Objects.nonNull(objectList) && !objectList.isEmpty()) ? objectList : null;
    }

    @Override
    public E getById(UUID id) {
        Query query = entityManager.createQuery("FROM " + getEntityClass().getName() + " c WHERE c.id = :id");
        query.setParameter("id", id);
        List<E> objectList = query.getResultList();
        return (Objects.nonNull(objectList) && !objectList.isEmpty()) ? objectList.get(0) : null;
    }

    @Override
    public E update(UUID id, E object) {
        try {
            E foundEntity = getById(id);

            if (foundEntity == null)
                throw new NotFoundException("No data found!");

            entityManager.merge(object);
            return foundEntity;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.flush();
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            E foundEntity = getById(id);

            if (foundEntity == null)
                throw new Exception("No data found!");

            entityManager.remove(foundEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.flush();
        }
    }

    public List<E> paginate(Integer limiter, Integer offset) {
        Query query = entityManager.createQuery("FROM " + getEntityClass().getName());
        query.setMaxResults(limiter != null ? limiter : DEFAULT_MAX_RESULTS);
        query.setFirstResult(offset != null ? offset : DEFAULT_OFFSET_RESULTS);
        List<E> objectList = query.getResultList();
        return (Objects.nonNull(objectList) && !objectList.isEmpty()) ? objectList : null;
    }
}
