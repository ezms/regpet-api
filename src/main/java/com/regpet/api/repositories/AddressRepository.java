package com.regpet.api.repositories;

import com.regpet.api.models.Address;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestScoped
public class AddressRepository extends BaseRepository<Address, UUID> {

    @Override
    public Class<Address> getEntityClass() {
        return Address.class;
    }

    public List<Address> getByUserId(UUID id) {
        List<Address> allAddresses;
        Query query = entityManager.createNativeQuery("""
                select
                    cast(a.address_id as varchar),
                    a.zip_code,
                    a.state,
                    a.city,
                    a.district,
                    a.number,
                    a.complement,
                    a.public_place
                from public.addresses a
                	join public.users_addresses ua on ua.address_id = a.address_id
                	where ua.user_id = '""" + id + "'", Address.class);
        allAddresses = query.getResultList();
        return (allAddresses != null && !allAddresses.isEmpty()) ? allAddresses : new ArrayList<>();
    }

    public List<Tuple> getByZipCode(String zipCode) {
        List<Tuple> result;
        TypedQuery<Tuple> query = entityManager.createQuery("""
            SELECT
                a.zipCode,
                a.state,
                a.city,
                a.district,
                a.publicPlace
            FROM Address a
            WHERE a.zipCode LIKE :zipCode""", Tuple.class);
        query.setParameter("zipCode", zipCode);
        result = query.getResultList();
        return (result != null && !result.isEmpty()) ? result : null;
    }
}
