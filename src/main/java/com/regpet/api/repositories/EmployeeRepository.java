package com.regpet.api.repositories;

import com.regpet.api.dto.employees.EmployeeResponseDTO;
import com.regpet.api.models.Employee;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequestScoped
public class EmployeeRepository extends BaseRepository<Employee, UUID> {
    @Override
    public Class<Employee> getEntityClass() {
        return Employee.class;
    }

    public List<EmployeeResponseDTO> findByNGO(UUID ngoId) {
        TypedQuery<Tuple> query = entityManager.createQuery("SELECT e.id, e.name FROM Employee e " +
                    "WHERE e.ngo.id = :ngoId", Tuple.class);
        query.setParameter("ngoId", ngoId);
        query.setMaxResults(DEFAULT_MAX_RESULTS);
        query.setFirstResult(DEFAULT_OFFSET_RESULTS);
        List<EmployeeResponseDTO> employees = new ArrayList<>();
        query.getResultList().forEach(employee ->
                employees.add(new EmployeeResponseDTO((UUID) employee.get(0), (String) employee.get(1))));
        return !employees.isEmpty() ? employees : null;
    }
}
