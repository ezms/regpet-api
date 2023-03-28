package com.regpet.api.dto.requests;

import com.regpet.api.enums.WorkStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class VolunteerRequestDTO {

    @NotBlank(message = "Missing 'name' field.")
    private String name;

    @NotBlank(message = "Missing 'status' field.")
    private WorkStatus status;
}
