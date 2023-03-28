package com.regpet.api.dto.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UpdateDenunciationRequestDTO {

    @NotBlank(message = "Missing 'details' field value.")
    private String details;
}
