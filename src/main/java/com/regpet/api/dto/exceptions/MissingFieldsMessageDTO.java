package com.regpet.api.dto.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MissingFieldsMessageDTO {
    private String error;
    private List<InvalidFieldDTO> invalidFields;
}

