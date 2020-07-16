package tinycc.implementation.external.function;

import prog2.tests.FatalCompilerError;
import tinycc.diagnostic.Location;
import tinycc.implementation.external.ExternalDeclaration;
import tinycc.implementation.statement.Block;
import tinycc.implementation.statement.Declaration;
import tinycc.implementation.type.Type;
import tinycc.implementation.type.Void;
import tinycc.implementation.utils.EnvironmentalDeclaration;
import tinycc.implementation.utils.Identifier;
import tinycc.implementation.utils.ReturnInfo;

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

        addEnvironmentalDeclaration(this);
    }

    public Function(Type returnType, Identifier identifier, NamedParameterList namedParameterList, Block block) {
        this.returnType = returnType;
        this.identifier = identifier;
        this.namedParameterList = namedParameterList;
        this.block = block;

        addEnvironmentalDeclaration(this);

        for(NamedParameter namedParameter : this.namedParameterList.getNamedParameters())
            this.block.addEnvironmentalDeclaration(new Declaration(namedParameter.getType(), namedParameter.getIdentifier()));
    }

    private boolean isDuplicate(Identifier identifier) {
        int useCounter = 0;

        for(EnvironmentalDeclaration environmentalDeclaration : getEnvironmentalDeclarations()) {
            if(!(environmentalDeclaration instanceof FunctionDeclaration) && environmentalDeclaration.getIdentifier().toString().equals(identifier.toString())) {
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

        if(isDuplicate(identifier))
            throw new RuntimeException("Identifier '" + identifier.toString() + "' is already in use.");

        ReturnInfo returnInfo = block.getReturnInfo(returnType);

        if(returnType.toString().equals((new Void()).toString())  && returnInfo.getReturnType() == ReturnInfo.ReturnType.TRUE)
            throw new FatalCompilerError(returnInfo.getLocatable(), "Detected return statement in void type function.");
        if(returnInfo.getReturnType() == ReturnInfo.ReturnType.FALSE_TYPE)
            throw new FatalCompilerError(returnInfo.getLocatable(), "Detected return statement with the wrong type.");
        if(!returnType.toString().equals((new Void()).toString()) && returnInfo.getReturnType() == ReturnInfo.ReturnType.NO_VALUE)
            throw new FatalCompilerError(returnInfo.getLocatable(), "Detected return statement without value.");
        if(!returnType.toString().equals((new Void()).toString()) && returnInfo.getReturnType() == ReturnInfo.ReturnType.NO_RETURN)
            throw new FatalCompilerError(block.getLocatable(), "No return statement detected.");
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
