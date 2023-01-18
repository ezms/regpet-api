package com.regpet.api.dto.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InvalidFieldDTO {
    private String message;
    private String field;
}
