package com.regpet.api.dto.animals;

import com.regpet.api.enums.Gender;
import com.regpet.api.enums.AnimalStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class AnimalResponseDTO {
    private UUID id;
    private String species;
    private String breed;
    private String name;
    private LocalDateTime foundOn;
    private Gender gender;
    private String description;
    private AnimalStatus animalStatus;
}
