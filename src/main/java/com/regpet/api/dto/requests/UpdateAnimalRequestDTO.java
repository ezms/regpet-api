package com.regpet.api.dto.requests;

import com.regpet.api.enums.AnimalStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateAnimalRequestDTO {
    private String name;
    private String description;
    private AnimalStatus animalStatus;
}
