package com.regpet.api.validators;

public class CNPJValidator {
    public static boolean validate(String cnpj) {
        final String PATTERN = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})\n";
        return cnpj.matches(PATTERN);
    }
}
