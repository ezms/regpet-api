package com.regpet.api.repositories;

import com.regpet.api.models.Ngo;

import javax.enterprise.context.RequestScoped;
import java.util.UUID;

@RequestScoped
public class NgoRepository extends BaseRepository<Ngo, UUID> {
    @Override
    public Class<Ngo> getEntityClass() {
        return Ngo.class;
    }
}
