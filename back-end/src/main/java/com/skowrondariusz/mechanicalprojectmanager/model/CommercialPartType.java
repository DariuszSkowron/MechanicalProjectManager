package com.skowrondariusz.mechanicalprojectmanager.model;

public enum CommercialPartType {
    TURNING,
    MILLING,
    UNMARKED,
    OTHER;


    @Override
    public String toString() {
        return super.toString();

    }

    public static <T extends Enum<T>> T valueOf(Class<T> enumType, String name) {
        return Enum.valueOf(enumType, name);
    }
}
