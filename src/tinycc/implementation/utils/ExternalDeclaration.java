package tinycc.implementation.utils;

import tinycc.implementation.function.Function;
import tinycc.implementation.function.FunctionDeclaration;

public class ExternalDeclaration {

    private final Object object;

    public ExternalDeclaration(Function function) {
        this.object = function;
    }

    public ExternalDeclaration(FunctionDeclaration functionDeclaration) {
        this.object = functionDeclaration;
    }

    public ExternalDeclaration(GlobalVariable globalVariable) {
        this.object = globalVariable;
    }

    public Object getObject() {
        return object;
    }

    @Override
    public String toString() {
        return "ExternalDeclaration(" + object.toString() + ")";
    }
}
