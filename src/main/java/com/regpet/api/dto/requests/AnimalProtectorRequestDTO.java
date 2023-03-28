package com.regpet.api.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalProtectorRequestDTO {

    @NotBlank(message = "Missing 'organizationName' field value")
    private String organizationName;
}
