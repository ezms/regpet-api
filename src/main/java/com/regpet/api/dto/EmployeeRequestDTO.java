package com.regpet.api.dto;

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
}
