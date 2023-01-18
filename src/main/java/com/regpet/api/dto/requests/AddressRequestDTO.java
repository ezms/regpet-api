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
public class AddressRequestDTO {

    @NotBlank(message = "Missing 'zipCode' field.")
    private String zipCode;

    @NotBlank(message = "Missing 'state' field.")
    private String state;

    @NotBlank(message = "Missing 'city' field.")
    private String city;

    @NotBlank(message = "Missing 'district' field.")
    private String district;

    @NotBlank(message = "Missing 'publicPlace' field.")
    private String publicPlace;

    @NotBlank(message = "Missing 'complement' field.")
    private String complement;

    @NotBlank(message = "Missing 'number' field.")
    private String number;
}
