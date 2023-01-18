package com.regpet.api.utils;

import com.regpet.api.dto.exceptions.InvalidFieldDTO;
import com.regpet.api.exceptions.MissingFieldException;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class RequestUtils {

    public static <T> void validateRequestBody(T requestDTO, Validator validator) throws MissingFieldException {
        Set<ConstraintViolation<T>> violations = validator.validate(requestDTO);
        if (!violations.isEmpty())
            createMessage(violations);
    }

    private static <T> void createMessage(Set<ConstraintViolation<T>> violations) throws MissingFieldException {
        Collection<InvalidFieldDTO> fieldErrors = violations.stream().map(error -> new InvalidFieldDTO(error.getMessage(),
                error.getPropertyPath().toString())).collect(Collectors.toList());
        throw new MissingFieldException("Failed to save fields.", fieldErrors);
    }
}
