package tinycc.implementation.statement;

import tinycc.implementation.expression.Expression;
import tinycc.implementation.type.Type;
import tinycc.implementation.utils.EnvironmentalDeclaration;
import tinycc.implementation.utils.ReturnType;

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
    public ReturnType getReturnType(Type type) {
        if(hasResult())
            return result.getType().toString().equals(type.toString()) ? ReturnType.TRUE : ReturnType.FALSE;

        return ReturnType.NO_RETURN;
    }

    @Override
    public String toString() {
        return "return" + (hasResult() ? " " + result.toString() : "") + ";";
    }
}
