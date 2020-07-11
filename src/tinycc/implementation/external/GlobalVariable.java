package tinycc.implementation.external;

import tinycc.implementation.type.Type;
import tinycc.implementation.utils.EnvironmentalDeclaration;
import tinycc.implementation.utils.Identifier;

public class GlobalVariable extends ExternalDeclaration implements EnvironmentalDeclaration {

    private final Type type;
    private final Identifier identifier;

    public GlobalVariable(Type type, Identifier identifier) {
        this.type = type;
        this.identifier = identifier;

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

    public Type getType() {
        return type;
    }

    @Override
    public Identifier getIdentifier() {
        return identifier;
    }

    @Override
    public void checkSemantics() {}

    @Override
    public String toString() {
        return type.toString() + " " + identifier.toString() + ";";
    }
}
