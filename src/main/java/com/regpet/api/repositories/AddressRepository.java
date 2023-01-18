package com.regpet.api.repositories;

import com.regpet.api.models.Address;

import javax.enterprise.context.RequestScoped;
import java.util.UUID;

@RequestScoped
public class AddressRepository extends BaseRepository<Address, UUID> {

    @Override
    public Class<Address> getEntityClass() {
        return Address.class;
    }
}
