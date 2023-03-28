package com.regpet.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AnimalStatus {
    ADOPTED("Adopted"),
    REDEEMED("Redeemed"),
    ABANDONED("Abandoned"),
    MISTREATMENT("Mistreatment");

    private final String description;
}
