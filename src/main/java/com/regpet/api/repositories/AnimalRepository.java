package com.regpet.api.repositories;

import com.regpet.api.models.Animal;

import javax.enterprise.context.RequestScoped;
import java.util.UUID;

@RequestScoped
public class AnimalRepository extends BaseRepository<Animal, UUID> {
    @Override
    public Class<Animal> getEntityClass() {
        return Animal.class;
    }
}
