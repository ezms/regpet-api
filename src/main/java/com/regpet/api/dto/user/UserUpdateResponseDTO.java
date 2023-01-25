package com.regpet.api.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserUpdateResponseDTO {
    private UUID id;
    private String name;
    private String username;
    private String bio;
    private String email;
    private String phoneNumber;
    private byte[] profilePhoto;
}
