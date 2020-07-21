package tinycc.implementation.external.function;

import prog2.tests.FatalCompilerError;
import tinycc.implementation.external.ExternalDeclaration;
import tinycc.implementation.type.Type;
import tinycc.implementation.utils.EnvironmentalDeclaration;
import tinycc.implementation.utils.Identifier;

public class FunctionDeclaration extends ExternalDeclaration implements EnvironmentalDeclaration {

    private final Type returnType;
    private final Identifier identifier;
    private ParameterList parameterList;

    public FunctionDeclaration(Type returnType, Identifier identifier) {
        this.returnType = returnType;
        this.identifier = identifier;

        addEnvironmentalDeclaration(this);
    }

    public FunctionDeclaration(Type returnType, Identifier identifier, ParameterList parameterList) {
        this.returnType = returnType;
        this.identifier = identifier;
        this.parameterList = parameterList;

        addEnvironmentalDeclaration(this);
    }

    private boolean isDuplicate(Identifier identifier) {
        int useCounter = 0;

        for(EnvironmentalDeclaration environmentalDeclaration : getEnvironmentalDeclarations()) {
            if(environmentalDeclaration.getIdentifier().equals(identifier)) {
                useCounter++;

                if(useCounter == 2)
                    return true;
            }
        }

        return false;
    }

    public Type getReturnType() {
        return returnType;
    }

    @Override
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
    public void checkSemantics() {
        if(isDuplicate(identifier))
            throw new FatalCompilerError(this.getLocatable(), "Identifier '" + identifier.toString() + "' already in use.");
    }

    @Override
    public String toString() {
        return returnType.toString() + " " + identifier.toString() + "(" + (hasParameterList() ? parameterList.toString() : "") + ");";
    }

    @Override
    public Type getType() {
        return returnType;
    }
}
