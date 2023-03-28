package com.regpet.api.repositories;

import com.regpet.api.models.GuestContact;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

@RequestScoped
public class GuestContactRepository extends BaseRepository<GuestContact, UUID> {
    @Override
    public Class<GuestContact> getEntityClass() {
        return GuestContact.class;
    }

    public GuestContact findByGuest(UUID guestId) {
        Query query = entityManager.createQuery("FROM GuestContact gc WHERE gc.guest.id = :guestId");
        query.setParameter("guestId", guestId);
        List<GuestContact> results = query.getResultList();
        return (results != null && !results.isEmpty()) ? results.get(0) : null;
    }
}
