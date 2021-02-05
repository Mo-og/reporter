package com.practice3.reporter;

public enum EnumRole {
    ROLE_DISPATCHER("Диспетчер"), ROLE_SUPERUSER("Суперпользователь");
    private final String displayValue;

    EnumRole(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
