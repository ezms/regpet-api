package com.regpet.api.validators;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class PhoneValidatorTest {

    @Test
    @DisplayName("Should return true if pass a valid phone")
    public void testPass() {
        final String phone = "11999554786";
        boolean validation = PhoneValidator.validate(phone);
        assertTrue(validation, "When 'phone' matches pattern return true");
    }

    @Test
    @DisplayName("Should return false if pass an invalid phone")
    public void testFail() {
        final String phone = "the_invalid_phone";
        boolean validation = PhoneValidator.validate(phone);
        assertFalse(validation, "When 'phone' matches pattern return true");
    }
}
