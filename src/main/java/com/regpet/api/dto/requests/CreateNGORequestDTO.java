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
public class CreateNGORequestDTO {

    @NotBlank(message = "Missing 'cnpj' field.")
    private String cnpj;

    @NotBlank(message = "Missing 'companyName' field.")
    private String companyName;

    @NotBlank(message = "Missing 'tradingName' field.")
    private String tradingName;
}
