package com.regpet.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotFoundException extends Exception {
    private String error;
}
