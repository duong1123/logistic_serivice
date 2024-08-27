package com.duongprj.logistic_service.enums;

import lombok.Getter;

@Getter
public enum ServiceType {
    STANDARD("STD"),
    EXPRESS("EXP"),
    OVERNIGHT("OVN");

    private final String prefix;

    ServiceType(String prefix) {
        this.prefix = prefix;
    }
}