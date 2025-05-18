package com.carlosamaral.dass21.enums;

public enum ScaleEnum {
    NO_SYMPTOM("0 - No symptoms"),
    MILD("1 - Mild symptoms"),
    MODERATE("2 - Moderate symptoms"),
    SEVERE("3 - Severe symptoms"),
    VERY_SEVERE("4 - Very severe symptoms");

    private final String description;

    ScaleEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
