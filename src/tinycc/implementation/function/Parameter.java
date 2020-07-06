package tinycc.implementation.function;

import tinycc.implementation.type.Type;
import tinycc.implementation.utils.Identifier;

public class Parameter {

    private final Type type;
    private Identifier identifier;

    public Parameter(Type type) {
        this.type = type;
    }

    public Parameter(Type type, Identifier identifier) {
        this.type = type;
        this.identifier = identifier;
    }

    public Type getType() {
        return type;
    }

    public boolean hasIdentifier() {
        return identifier != null;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return type.toString() + (hasIdentifier() ? " " + identifier.toString() : "");
    }
}
