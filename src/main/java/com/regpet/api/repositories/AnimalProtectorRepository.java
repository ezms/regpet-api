package com.regpet.api.repositories;

import com.regpet.api.models.AnimalProtector;

import javax.enterprise.context.RequestScoped;
import java.util.UUID;

@RequestScoped
public class AnimalProtectorRepository extends BaseRepository<AnimalProtector, UUID> {

    @Override
    public Class<AnimalProtector> getEntityClass() {
        return AnimalProtector.class;
    }
}
