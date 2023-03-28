package com.regpet.api.repositories;

import com.regpet.api.dto.volunteers.VolunteerResponseDTO;
import com.regpet.api.models.Volunteer;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestScoped
public class VolunteerRepository extends BaseRepository<Volunteer, UUID> {

    @Override
    public Class<Volunteer> getEntityClass() {
        return Volunteer.class;
    }

    public List<VolunteerResponseDTO> findByNGO(UUID ngoId) {
        TypedQuery<Tuple> query = entityManager.createQuery("SELECT e.id, e.name FROM Employee e " +
                "WHERE e.ngo.id = :ngoId", Tuple.class);
        query.setParameter("ngoId", ngoId);
        query.setMaxResults(DEFAULT_MAX_RESULTS);
        query.setFirstResult(DEFAULT_OFFSET_RESULTS);
        List<VolunteerResponseDTO> employees = new ArrayList<>();
        query.getResultList().forEach(employee ->
                employees.add(new VolunteerResponseDTO((UUID) employee.get(0), (String) employee.get(1))));
        return !employees.isEmpty() ? employees : null;
    }
}
