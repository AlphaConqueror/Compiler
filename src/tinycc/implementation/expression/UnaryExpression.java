package tinycc.implementation.expression;

import tinycc.implementation.utils.UnaryOperator;

public class UnaryExpression extends Expression {

    private final UnaryOperator unaryOperator;
    private final boolean isPostfix;
    private final Expression expression;

    public UnaryExpression(UnaryOperator unaryOperator, boolean isPostfix, Expression expression) {
        this.unaryOperator = unaryOperator;
        this.isPostfix = isPostfix;
        this.expression = expression;
    }

    public UnaryOperator getUnaryOperator() {
        return unaryOperator;
    }

    public boolean isPostfix() {
        return isPostfix;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        if(isPostfix())
            return "(" + expression.toString() + " " + unaryOperator.toString() + ")";

        return "(" + unaryOperator.toString() + " " + expression.toString() + ")";
    }
}
