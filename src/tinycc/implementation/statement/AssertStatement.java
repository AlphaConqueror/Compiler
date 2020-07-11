package tinycc.implementation.statement;

import tinycc.implementation.expression.Expression;

public class AssertStatement extends Statement {

    private final Expression condition;

    public AssertStatement(Expression condition) {
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
        return "_Assert(" + condition.toString() + ")";
    }
}
