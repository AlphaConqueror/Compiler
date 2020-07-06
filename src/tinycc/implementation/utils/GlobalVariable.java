package tinycc.implementation.utils;

import tinycc.implementation.type.Type;

public class GlobalVariable {

    private final Type type;
    private final Identifier identifier;

    public GlobalVariable(Type type, Identifier identifier) {
        this.type = type;
        this.identifier = identifier;
    }

    public Type getType() {
        return type;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return "GlobalVariable(" + type.toString() + " " + identifier.toString() + ")";
    }
}
