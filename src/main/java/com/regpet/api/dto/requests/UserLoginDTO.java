package com.regpet.api.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {

    @NotBlank(message = "Missing 'email' field.")
    private String email;

    @NotBlank(message = "Missing 'password' field.")
    private String password;

    @NotBlank(message = "Missing 'roles' field.")
    private List<String> roles;
}
