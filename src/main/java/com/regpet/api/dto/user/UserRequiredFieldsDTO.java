package com.regpet.api.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRequiredFieldsDTO {
    private String name;
    private String email;
    private String phoneNumber;
}
