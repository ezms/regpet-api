package com.regpet.api.repositories;

import com.regpet.api.models.AnimalImage;

import javax.enterprise.context.RequestScoped;
import java.util.UUID;

@RequestScoped
public class AnimalImageRepository extends BaseRepository<AnimalImage, UUID> {
    @Override
    public Class<AnimalImage> getEntityClass() {
        return AnimalImage.class;
    }
}
