package tinycc.implementation.statement;

import tinycc.implementation.expression.Expression;

public class ReturnStatement extends Statement {

    private Expression result; //Optional

    public ReturnStatement() {}

    public ReturnStatement(Expression result) {
        this.result = result;

        this.result.addEnvironmentalDeclarations(this.getEnvironmentalDeclarations());
    }

    public boolean hasResult() {
        return result != null;
    }

    public Expression getResult() {
        return result;
    }

    @Override
    public void checkSemantics() {}

    @Override
    public String toString() {
        return "return" + (hasResult() ? " " + result.toString() : "") + ";";
    }
}
