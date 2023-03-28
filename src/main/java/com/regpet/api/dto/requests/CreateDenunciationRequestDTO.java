package com.regpet.api.dto.requests;

import com.regpet.api.models.Address;
import com.regpet.api.models.Animal;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CreateDenunciationRequestDTO {

    @NotNull(message = "Missing 'denunciationDate' field value.")
    @Past(message = "The date cannot be greater than the current time")
    private LocalDateTime denunciationDate;

    @NotBlank(message = "Missing 'details' field value.")
    private String details;

    @NotNull(message = "Missing 'address' field value.")
    private Address address;

    @NotNull(message = "Missing 'animals' field value.")
    private List<Animal> animals;
}
