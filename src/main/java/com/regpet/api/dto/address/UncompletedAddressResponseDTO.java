package com.regpet.api.dto.address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UncompletedAddressResponseDTO {
    private String zipCode;
    private String state;
    private String city;
    private String district;
    private String public_place;
}
