package com.regpet.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    ADOPTED("Adopted"), REDEEMED("Redeemed"), ABANDONED("Abandoned");

    private final String description;
}
