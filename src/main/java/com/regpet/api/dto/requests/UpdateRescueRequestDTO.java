package com.regpet.api.dto.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UpdateRescueRequestDTO {

    @NotBlank(message = "Missing 'story' field value.")
    private String story;
}
