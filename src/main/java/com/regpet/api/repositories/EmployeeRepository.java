package com.regpet.api.repositories;

import com.regpet.api.models.Employee;

import javax.enterprise.context.RequestScoped;
import java.util.UUID;

@RequestScoped
public class EmployeeRepository extends BaseRepository<Employee, UUID> {
    @Override
    public Class<Employee> getEntityClass() {
        return Employee.class;
    }
}
