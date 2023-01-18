package com.regpet.api.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WrongFieldException extends Exception {
    private String message;
}
