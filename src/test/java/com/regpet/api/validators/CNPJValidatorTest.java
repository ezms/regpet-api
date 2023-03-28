package com.regpet.api.validators;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class CNPJValidatorTest {

    @Test
    @DisplayName("Should return a true value if get a valid CNPJ")
    public void testPass() {
        final String cnpj = "42631749000187";
        boolean assertion = CNPJValidator.validate(cnpj);
        assertTrue(assertion, "When 'cnpj' matches pattern return true");
    }

    @Test
    @DisplayName("Should return a false value if get a invalid CNPJ")
    public void testFail() {
        final String cnpj = "invalid_cnpj";
        boolean assertion = CNPJValidator.validate(cnpj);
        assertFalse(assertion, "When 'cnpj' do not matches pattern return false");
    }
}
