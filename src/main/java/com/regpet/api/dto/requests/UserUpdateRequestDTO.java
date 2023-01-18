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
public class UserUpdateRequestDTO {

    @NotBlank(message = "Field 'name' cannot be blank")
    private String name;

    @NotBlank(message = "Field 'username' cannot be blank")
    private String username;

    @NotBlank(message = "Field 'email' cannot be blank")
    private String email;

    @NotBlank(message = "Field 'phoneNumber' cannot be blank")
    private String phoneNumber;

    private String bio;
    private String profilePhoto;
}
