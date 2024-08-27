package com.duongprj.logistic_service.enums;

import lombok.Getter;

@Getter
public enum TrackingCode {
    F000("Manifested"),
    F098("Drop off done"),
    F100("Picked up"),
    F440("Enter DEP hub"),
    F450("Left DEP hub"),
    F510("Enter Sorting center"),
    F540("Left Sorting center"),
    F599("Enter ARR hub"),
    F600("Out for delivery"),
    F980("Delivered"),
    F981("Failed delivery attempt"),
    ;

    TrackingCode(String description) {

        this.description = description;
    }

    private final String description;

    public String getDescription() {
        return description;
    }
}
