package com.regpet.api.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserResponseDTO {
    private UUID id;
    private String name;
    private String username;
    private String email;
    private String bio;
    private String profilePhoto;
}
