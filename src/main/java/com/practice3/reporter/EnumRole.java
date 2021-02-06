package com.practice3.reporter;

public enum EnumRole {
    DISPATCHER("Диспетчер", "DISPATCHER"), SUPERUSER("Суперпользователь", "SUPERUSER");
    private final String displayValue, roleValue;

    EnumRole(String displayValue, String roleValue) {
        this.displayValue = displayValue;
        this.roleValue = roleValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public String getRoleValue() {
        return roleValue;
    }
}
