package tinycc.implementation.statement;

import tinycc.implementation.expression.Expression;
import tinycc.implementation.type.Type;
import tinycc.implementation.type.Void;
import tinycc.implementation.utils.EnvironmentalDeclaration;
import tinycc.implementation.utils.ReturnInfo;

import java.util.Collection;

public class ReturnStatement extends Statement {

    private Expression result; //Optional

    public ReturnStatement() {}

    public ReturnStatement(Expression result) {
        this.result = result;
    }

    public boolean hasResult() {
        return result != null;
    }

    public Expression getResult() {
        return result;
    }

    @Override
    public void updateEnvironment(Collection<EnvironmentalDeclaration> environmentalDeclarations) {
        if(hasResult())
            result.addEnvironmentalDeclarations(environmentalDeclarations);
    }

    @Override
    public void checkSemantics() {
        if(hasResult())
            result.checkSemantics();
    }



    @Override
    public ReturnInfo getReturnInfo(Type type) {
        ReturnInfo returnInfo;

        if(hasResult()) {
            returnInfo = result.getType().equals(type) ? new ReturnInfo(ReturnInfo.ReturnType.TRUE) : new ReturnInfo(ReturnInfo.ReturnType.FALSE_TYPE);

            return returnInfo.setLocatable(this.getLocatable());
        }

        if(type.getClass() == Void.class)
            return new ReturnInfo(ReturnInfo.ReturnType.NO_VALUE).setLocatable(this.getLocatable());

        return new ReturnInfo(ReturnInfo.ReturnType.FALSE_TYPE).setLocatable(this.getLocatable());
    }

    @Override
    public String toString() {
        return "return" + (hasResult() ? " " + result.toString() : "") + ";";
    }
}
