package com.regpet.api.services;

import com.regpet.api.dto.employees.EmployeeResponseDTO;
import com.regpet.api.dto.requests.EmployeeRequestDTO;
import com.regpet.api.exceptions.NotFoundException;
import com.regpet.api.models.Employee;
import com.regpet.api.models.Ngo;
import com.regpet.api.repositories.EmployeeRepository;
import com.regpet.api.repositories.NgoRepository;
import com.regpet.api.utils.TextUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequestScoped
public class EmployeeService {

    @Inject
    EmployeeRepository employeeRepository;

    @Inject
    NgoRepository ngoRepository;

    public Employee saveEmployee(EmployeeRequestDTO request, UUID ngoId) throws NotFoundException {
        Ngo ngo = ngoRepository.getById(ngoId);
        if (Objects.isNull(ngo)) throw new NotFoundException("No NGO found.");
        Employee employee = new Employee();
        employee.setName(TextUtils.capitalizeName(request.getName()));
        employee.setNgo(ngoRepository.getById(ngoId));
        employee.setStatus(request.getStatus());
        return employeeRepository.add(employee);
    }

    public List<EmployeeResponseDTO> retrieveEmployees(UUID ngoId) throws NotFoundException {
        Ngo ngo = ngoRepository.getById(ngoId);
        if (Objects.isNull(ngo)) throw new NotFoundException("No NGO found.");
        List<EmployeeResponseDTO> results = employeeRepository.findByNGO(ngo.getId());
        return (Objects.nonNull(results) && !results.isEmpty()) ? results : new ArrayList<>();
    }

    public Employee getById(UUID id) throws NotFoundException {
        Employee employee = employeeRepository.getById(id);
        if (Objects.isNull(employee)) throw new NotFoundException("No data found.");
        return employee;
    }

    public Employee update(UUID id, EmployeeRequestDTO requestDTO) throws NotFoundException {
        Employee employee = employeeRepository.getById(id);
        if (Objects.isNull(employee)) throw new NotFoundException("No data found.");
        employee.setName(TextUtils.capitalizeName(requestDTO.getName()));
        employee.setStatus(requestDTO.getStatus());
        return employeeRepository.update(id, employee);
    }

    public void delete(UUID id) throws NotFoundException {
        Employee employee = employeeRepository.getById(id);
        if (Objects.isNull(employee)) throw new NotFoundException("No data found.");
        employeeRepository.delete(employee.getId());
    }
}
