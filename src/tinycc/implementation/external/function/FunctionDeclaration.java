package tinycc.implementation.external.function;

import tinycc.implementation.external.ExternalDeclaration;
import tinycc.implementation.type.Type;
import tinycc.implementation.utils.EnvironmentalDeclaration;
import tinycc.implementation.utils.Identifier;

import java.util.Collection;

public class FunctionDeclaration extends ExternalDeclaration implements EnvironmentalDeclaration {

    private final Type returnType;
    private final Identifier identifier;
    private ParameterList parameterList;

    public FunctionDeclaration(Type returnType, Identifier identifier) {
        this.returnType = returnType;
        this.identifier = identifier;

        checkForDuplicate(this.identifier);

        addEnvironmentalDeclaration(this);
    }

    public FunctionDeclaration(Type returnType, Identifier identifier, ParameterList parameterList) {
        this.returnType = returnType;
        this.identifier = identifier;
        this.parameterList = parameterList;

        checkForDuplicate(this.identifier);

        addEnvironmentalDeclaration(this);
    }

    private void checkForDuplicate(Identifier identifier) {
        boolean isUsed = false;

        for(EnvironmentalDeclaration environmentalDeclaration : getEnvironmentalDeclarations()) {
            if(environmentalDeclaration.getIdentifier().equals(identifier)) {
                isUsed = true;
                break;
            }
        }

        if(isUsed)
            throw new RuntimeException("Identifier '" + identifier.toString() + "' is already in use.");
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
    public void updateEnvironment(Collection<EnvironmentalDeclaration> environmentalDeclarations) {}

    @Override
    public void checkSemantics() {}

    @Override
    public String toString() {
        return returnType.toString() + " " + identifier.toString() + "(" + (hasParameterList() ? parameterList.toString() : "") + ");";
    }

    @Override
    public Type getType() {
        return returnType;
    }
}
