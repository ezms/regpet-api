package com.regpet.api.validators;

import com.regpet.api.exceptions.WrongFieldException;

public class PatternValidator {
    public static void validate(String regex, String sentence, String message) throws WrongFieldException {
        if (!sentence.matches(regex))
            throw new WrongFieldException(String.format("Invalid %s.", message));
    }
}
