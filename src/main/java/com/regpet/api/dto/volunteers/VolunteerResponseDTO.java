package com.regpet.api.dto.volunteers;

import com.regpet.api.enums.WorkStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class VolunteerResponseDTO {
    private UUID id;
    private String name;

    private WorkStatus status;

    public VolunteerResponseDTO(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
