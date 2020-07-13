package tinycc.implementation.statement;

import tinycc.implementation.expression.Expression;
import tinycc.implementation.utils.EnvironmentalDeclaration;

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
        result.addEnvironmentalDeclarations(environmentalDeclarations);
    }

    @Override
    public void checkSemantics() {
        if(hasResult())
            result.checkSemantics();
    }

    @Override
    public String toString() {
        return "return" + (hasResult() ? " " + result.toString() : "") + ";";
    }
}
