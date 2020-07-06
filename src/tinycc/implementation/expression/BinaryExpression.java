package tinycc.implementation.expression;

import tinycc.logic.BinaryOperator;

public class BinaryExpression extends Expression {

    private final Expression firstExpression, secondExpression;
    private final BinaryOperator binaryOperator;

    public BinaryExpression(Expression firstExpression, Expression secondExpression, BinaryOperator binaryOperator) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.binaryOperator = binaryOperator;
    }

    public Expression getFirstExpression() {
        return firstExpression;
    }

    public Expression getSecondExpression() {
        return secondExpression;
    }

    public BinaryOperator getBinaryOperator() {
        return binaryOperator;
    }

    @Override
    public String toString() {
        return "BinaryExpression(" + firstExpression.toString() + " " + binaryOperator.toString() + " " + secondExpression.toString() + ")";
    }
}
