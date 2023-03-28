package com.regpet.api.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GuestContactRequestDTO {
    private String instagramUrl;
    private String facebookUrl;
    private String twitterUrl;

    @NotBlank(message = "Missing 'cellphone' filed.")
    private String cellphoneNumber;
}
