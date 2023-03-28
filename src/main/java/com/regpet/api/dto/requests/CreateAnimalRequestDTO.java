package com.regpet.api.dto.requests;

import com.regpet.api.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAnimalRequestDTO {

    @NotBlank(message = "Missing 'species' field value.")
    private String species;

    @NotBlank(message = "Missing 'breed' field value.")
    private String breed;

    @NotBlank(message = "Missing 'name' field value.")
    private String name;

    private Gender gender;
    private LocalDateTime foundOn;
    private String description;
}
