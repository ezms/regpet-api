package com.regpet.api.services;

import com.regpet.api.dto.EmployeeRequestDTO;
import com.regpet.api.models.Employee;
import com.regpet.api.repositories.EmployeeRepository;
import com.regpet.api.utils.TextUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class EmployeeService {

    @Inject
    EmployeeRepository employeeRepository;

    public void saveEmployee(EmployeeRequestDTO request) {
        Employee employee = new Employee();
        employee.setName(TextUtils.capitalizeName(request.getName()));

        employeeRepository.add(employee);
    }
}
