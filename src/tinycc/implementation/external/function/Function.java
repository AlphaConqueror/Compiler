package tinycc.implementation.external.function;

import prog2.tests.FatalCompilerError;
import tinycc.implementation.external.ExternalDeclaration;
import tinycc.implementation.statement.Block;
import tinycc.implementation.statement.Declaration;
import tinycc.implementation.type.Type;
import tinycc.implementation.type.Void;
import tinycc.implementation.utils.EnvironmentalDeclaration;
import tinycc.implementation.utils.Identifier;
import tinycc.implementation.utils.ReturnType;

import java.util.Collection;

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

        for(NamedParameter namedParameter : this.namedParameterList.getNamedParameters())
            addEnvironmentalDeclaration(new Declaration(namedParameter.getType(), namedParameter.getIdentifier()));
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
    public void updateEnvironment(Collection<EnvironmentalDeclaration> environmentalDeclarations) {
        block.addEnvironmentalDeclarations(environmentalDeclarations);
    }

    @Override
    public void checkSemantics() {
        block.checkSemantics();

        ReturnType hasReturnType = block.getReturnType(returnType);

        if(returnType.toString().equals((new Void()).toString())) {
            if(hasReturnType == ReturnType.TRUE)
                throw new FatalCompilerError(block.getLocatable(), "Detected return statement in void type function.");
        } else if(hasReturnType == ReturnType.FALSE)
            throw new FatalCompilerError(block.getLocatable(), "No return statement detected.");
        else if(hasReturnType == ReturnType.NO_RETURN)
            throw new FatalCompilerError(block.getLocatable(), "Detected return statement without value.");
    }

    @Override
    public String toString() {
        return returnType.toString() + " " + identifier.toString() + "(" + (hasNamedParameterList() ? namedParameterList.toString() : "") + ") " + block.toString() + "\n";
    }

    @Override
    public Type getType() {
        return returnType;
    }
}
