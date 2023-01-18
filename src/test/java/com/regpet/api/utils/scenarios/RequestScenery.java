package com.regpet.api.utils.scenarios;

import com.regpet.api.dto.requests.CreateUserRequestDTO;

public class RequestScenery {
    public CreateUserRequestDTO getUserRequestDTO() {
        return new CreateUserRequestDTO(
                "Jake Peralta", "jake-peralta", "00000000000",
                "jake@b99.com", "santiago");
    }

    public CreateUserRequestDTO getUserMissingFieldRequestDTO() {
        CreateUserRequestDTO dto = new CreateUserRequestDTO();
        dto.setName("Michael Hitchcock ");
        dto.setUsername("mike-hitchcock");
        dto.setEmail("hitchcock@b99.com");
        dto.setPassword("getWokeScully");
        return dto;
    }
}
