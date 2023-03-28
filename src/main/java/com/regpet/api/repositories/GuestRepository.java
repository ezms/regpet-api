package com.regpet.api.repositories;

import com.regpet.api.models.Guest;

import javax.enterprise.context.RequestScoped;
import java.util.UUID;

@RequestScoped
public class GuestRepository extends BaseRepository<Guest, UUID> {
    @Override
    public Class<Guest> getEntityClass() {
        return Guest.class;
    }
}
