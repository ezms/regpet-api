package com.regpet.api.repositories;

import com.regpet.api.models.Animal;
import org.hibernate.Session;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestScoped
public class AnimalRepository extends BaseRepository<Animal, UUID> {
    @Override
    public Class<Animal> getEntityClass() {
        return Animal.class;
    }

    public List<Animal> getAnimalsByName(String name) {
        Query query = entityManager.createQuery("FROM Animal a WHERE a.name LIKE CONCAT('%', :name, '%')")
                .setParameter("name", name);
        List<Animal> animalList = query.getResultList();
        return (animalList != null && !animalList.isEmpty()) ? animalList : new ArrayList<>();
    }
}
