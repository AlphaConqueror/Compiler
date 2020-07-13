package tinycc.implementation.statement;

import tinycc.implementation.expression.Expression;
import tinycc.implementation.utils.EnvironmentalDeclaration;

import java.util.Collection;

public class ExpressionStatement extends Statement {

    private final Expression expression;

    public ExpressionStatement(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public void updateEnvironment(Collection<EnvironmentalDeclaration> environmentalDeclarations) {
        expression.addEnvironmentalDeclarations(environmentalDeclarations);
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
