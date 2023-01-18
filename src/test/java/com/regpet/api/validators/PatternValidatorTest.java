package com.regpet.api.validators;

import com.regpet.api.exceptions.WrongFieldException;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class PatternValidatorTest {

    @Test
    @DisplayName("Should get an exception message if validation fail.")
    public void testValidateMessageFunctionFail() {
        final String wrongSentence = "this is a sample string!";
        final String exampleRegex = "[0-9]";
        WrongFieldException exception = assertThrows(WrongFieldException.class, () ->
                PatternValidator.validate(exampleRegex, wrongSentence, "number value"));
        assertEquals("Invalid number value.", exception.getMessage());
    }

    @Test
    @DisplayName("Should get a wrong field exception if the text sent does not match the given regex.")
    public void testValidateFail() {
        final String wrongSentence = "this is a sample string!";
        final String exampleRegex = "[0-9]";
        assertThrows(WrongFieldException.class, () ->
                PatternValidator.validate(exampleRegex, wrongSentence, "number value"));
    }

    @Test
    @DisplayName("Should not launch the exception when the text matches the given regex.")
    public void testValidatePass() {
        final String wrongSentence = "5";
        final String exampleRegex = "[0-9]";
        assertDoesNotThrow(() ->
                PatternValidator.validate(exampleRegex, wrongSentence, "number value"));
    }
}
