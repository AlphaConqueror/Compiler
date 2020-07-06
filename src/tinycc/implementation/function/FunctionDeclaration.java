package tinycc.implementation.function;

import tinycc.implementation.type.Type;
import tinycc.implementation.utils.Identifier;

public class FunctionDeclaration {

    private final Type type;
    private final Identifier identifier;
    private ParameterList parameterList;

    public FunctionDeclaration(Type type, Identifier identifier) {
        this.type = type;
        this.identifier = identifier;
    }

    public FunctionDeclaration(Type type, Identifier identifier, ParameterList parameterList) {
        this.type = type;
        this.identifier = identifier;
        this.parameterList = parameterList;
    }

    public Type getType() {
        return type;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public boolean hasParameterList() {
        return parameterList != null;
    }

    public ParameterList getParameterList() {
        return parameterList;
    }

    @Override
    public String toString() {
        return type.toString() + " " + identifier.toString() + " ( " + (hasParameterList() ? parameterList.toString() : "") + " );";
    }
}
