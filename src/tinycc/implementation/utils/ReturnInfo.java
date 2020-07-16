package tinycc.implementation.utils;

import tinycc.diagnostic.Locatable;

public class ReturnInfo {

    private final ReturnType returnType;
    private Locatable locatable;

    public ReturnInfo(ReturnType returnType) {
        this.returnType = returnType;
    }

    public ReturnType getReturnType() {
        return returnType;
    }

    public Locatable getLocatable() {
        return locatable;
    }

    public ReturnInfo setLocatable(Locatable locatable) {
        this.locatable = locatable;

        return this;
    }

    public enum ReturnType {
        TRUE, FALSE_TYPE, NO_VALUE, NO_RETURN
    }
}
