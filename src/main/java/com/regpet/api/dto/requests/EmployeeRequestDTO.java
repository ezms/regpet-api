package com.regpet.api.dto.requests;

import com.regpet.api.enums.WorkStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequestDTO {

    @NotBlank(message = "Missing 'name' field.")
    private String name;

    @NotBlank(message = "Missing 'status' field.")
    private WorkStatus status;
}
