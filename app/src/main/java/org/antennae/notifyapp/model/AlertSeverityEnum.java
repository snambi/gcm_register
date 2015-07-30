package org.antennae.notifyapp.model;

import android.graphics.Color;

public enum AlertSeverityEnum {
    NORMAL, UNDER_WATCH, ELEVATED, HIGH, SEVERE;

    public int getColor() {
        switch(this) {
            case NORMAL: return Color.parseColor("#01A46D");
            case UNDER_WATCH: return Color.parseColor("#377FC7");
            case ELEVATED: return Color.parseColor("#F5D800");
            case HIGH: return Color.parseColor("#FF9B2B");
            case SEVERE: return Color.parseColor("#F50800");
            default: return Color.BLACK;
        }
    }

    public static boolean shouldCall(Alert alert) {
        return alert.getSeverity().equalsIgnoreCase(AlertSeverityEnum.HIGH.toString())
                || alert.getSeverity().equalsIgnoreCase(AlertSeverityEnum.ELEVATED.toString())
                || alert.getSeverity().equalsIgnoreCase(AlertSeverityEnum.SEVERE.toString());
    }
}
