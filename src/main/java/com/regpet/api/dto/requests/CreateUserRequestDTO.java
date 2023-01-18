package com.regpet.api.dto.requests;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequestDTO {

    @NotBlank(message = "Missing 'name' field.")
    private String name;

    @NotBlank(message = "Missing 'username' field.")
    private String username;

    @NotBlank(message = "Missing 'phoneNumber' field")
    private String phoneNumber;

    @NotBlank(message = "Missing 'email' field.")
    private String email;

    @NotBlank(message = "Missing 'password' field.")
    private String password;
}
