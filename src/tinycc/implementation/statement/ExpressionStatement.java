package tinycc.implementation.statement;

import tinycc.implementation.expression.Expression;

public class ExpressionStatement extends Statement {

    private final Expression expression;

    public ExpressionStatement(Expression expression) {
        this.expression = expression;

        this.expression.addEnvironmentalDeclarations(this.getEnvironmentalDeclarations());
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public void checkSemantics() {
        expression.checkSemantics();
    }

    @Override
    public String toString() {
        return expression.toString() + ";";
    }
}
