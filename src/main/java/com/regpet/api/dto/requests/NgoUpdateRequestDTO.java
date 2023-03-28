package com.regpet.api.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NgoUpdateRequestDTO {

    @NotBlank(message = "Missing 'companyName' field value.")
    private String companyName;

    @NotBlank(message = "Missing 'companyName' field value.")
    private String tradingName;
}
