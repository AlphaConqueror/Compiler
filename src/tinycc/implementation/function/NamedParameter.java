package tinycc.implementation.function;

import tinycc.implementation.type.Type;
import tinycc.implementation.utils.Identifier;

public class NamedParameter {

    private final Type type;
    private final Identifier identifier;

    public NamedParameter(Type type, Identifier identifier) {
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
        return type.toString() + " " + identifier.toString();
    }
}
