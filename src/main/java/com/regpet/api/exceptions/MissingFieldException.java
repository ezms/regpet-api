package com.regpet.api.exceptions;

import com.regpet.api.dto.exceptions.InvalidFieldDTO;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class MissingFieldException extends Exception {
    public static final int UNPROCESSABLE_ENTITY = 422;
    @NonNull
    private String message;
    @NonNull
    private Collection<InvalidFieldDTO> errors;

    public int getStatusCode() {
        return MissingFieldException.UNPROCESSABLE_ENTITY;
    }
}
