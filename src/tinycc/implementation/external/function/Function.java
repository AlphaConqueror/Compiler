package tinycc.implementation.external.function;

import tinycc.implementation.external.ExternalDeclaration;
import tinycc.implementation.statement.Block;
import tinycc.implementation.type.Type;
import tinycc.implementation.utils.EnvironmentalDeclaration;
import tinycc.implementation.utils.Identifier;

public class Function extends ExternalDeclaration implements EnvironmentalDeclaration {

    private final Type returnType;
    private final Identifier identifier;
    private NamedParameterList namedParameterList;
    private final Block block;

    public Function(Type returnType, Identifier identifier, Block block) {
        this.returnType = returnType;
        this.identifier = identifier;
        this.block = block;

        checkForDuplicate(this.identifier);

        addEnvironmentalDeclaration(this);
    }

    public Function(Type returnType, Identifier identifier, NamedParameterList namedParameterList, Block block) {
        this.returnType = returnType;
        this.identifier = identifier;
        this.namedParameterList = namedParameterList;
        this.block = block;

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

    public boolean hasNamedParameterList() {
        return namedParameterList != null;
    }

    public NamedParameterList getNamedParameterList() {
        return namedParameterList;
    }

    public Block getBlock() {
        return block;
    }

    @Override
    public void checkSemantics() {
        block.checkSemantics();
    }

    @Override
    public String toString() {
        return returnType.toString() + " " + identifier.toString() + "(" + (hasNamedParameterList() ? namedParameterList.toString() : "") + ") " + block.toString() + "\n";
    }
}
