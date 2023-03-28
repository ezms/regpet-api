package com.regpet.api.validators;

public class PhoneValidator {
    public static boolean validate(String phone) {
        final String PATTERN = "^[\\\\+]?[(]?[0-9]{3}[)]?[-\\s\\\\.]?[0-9]{3}[-\\s\\\\.]?[0-9]{4,7}$";
        return phone.matches(PATTERN);
    }
}
