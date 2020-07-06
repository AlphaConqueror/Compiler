package tinycc.implementation.expression;

import tinycc.implementation.utils.BinaryOperator;

public class BinaryExpression extends Expression {

    private final BinaryOperator binaryOperator;
    private final Expression firstExpression, secondExpression;

    public BinaryExpression(BinaryOperator binaryOperator, Expression firstExpression, Expression secondExpression) {
        this.binaryOperator = binaryOperator;
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }

    public BinaryOperator getBinaryOperator() {
        return binaryOperator;
    }

    public Expression getFirstExpression() {
        return firstExpression;
    }

    public Expression getSecondExpression() {
        return secondExpression;
    }

    @Override
    public String toString() {
        return "(" + firstExpression.toString() + " " + binaryOperator.toString() + " " + secondExpression.toString() + ")";
    }
}
