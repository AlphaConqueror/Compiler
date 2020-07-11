package tinycc.implementation.statement;

import tinycc.implementation.expression.Expression;

public class AssumeStatement extends Statement {

    private final Expression condition;

    public AssumeStatement(Expression condition) {
        this.condition = condition;

        this.condition.addEnvironmentalDeclarations(this.getEnvironmentalDeclarations());
    }

    public Expression getCondition() {
        return condition;
    }

    @Override
    public void checkSemantics() {
        condition.checkSemantics();
    }

    @Override
    public String toString() {
        return "_Assume(" + condition.toString() + ")";
    }
}
