package com.regpet.api.dto.employees;

import com.regpet.api.enums.WorkStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class EmployeeResponseDTO {
    private UUID id;
    private String name;

    private WorkStatus status;

    public EmployeeResponseDTO(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
