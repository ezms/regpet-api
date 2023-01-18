package com.regpet.api.utils;

import com.regpet.api.dto.requests.CreateUserRequestDTO;
import com.regpet.api.exceptions.MissingFieldException;
import com.regpet.api.utils.scenarios.RequestScenery;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.validation.Validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
public class RequestUtilsTest {

    @Inject
    Validator validator;

    @Test
    @DisplayName("Should able to validate request body")
    public void testValidateRequestBodyPass() {
        CreateUserRequestDTO requestDTO = new RequestScenery().getUserRequestDTO();
        assertDoesNotThrow(() ->
                RequestUtils.validateRequestBody(requestDTO, validator));
    }

    @Test
    @DisplayName("Shouldn't able to validate request body")
    public void testValidateRequestBodyFail() {
        CreateUserRequestDTO requestDTO = new RequestScenery().getUserMissingFieldRequestDTO();
        assertThrows(MissingFieldException.class, () ->
                RequestUtils.validateRequestBody(requestDTO, validator));
    }
}
