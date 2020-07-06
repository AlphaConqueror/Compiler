package tinycc.implementation.expression;

import tinycc.implementation.utils.UnaryOperator;

public class UnaryExpression extends Expression {

    private final UnaryOperator unaryOperator;
    private final Expression expression;

    public UnaryExpression(UnaryOperator unaryOperator, Expression expression) {
        this.unaryOperator = unaryOperator;
        this.expression = expression;
    }

    public UnaryOperator getUnaryOperator() {
        return unaryOperator;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return "UnaryExpression(" + unaryOperator.toString() + " " + expression.toString() + ")";
    }
}
