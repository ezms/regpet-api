package com.regpet.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WorkStatus {
    ACTIVE("Active"), INACTIVE("Inactive");
    private final String description;
}
