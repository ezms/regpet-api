package com.regpet.api.dto.address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDTO {
    private UUID id;
    private String zipCode;
    private String state;
    private String city;
    private String district;
    private String publicPlace;
    private String number;
    private String complement;
}
