package tinycc.implementation.statement;

import prog2.tests.FatalCompilerError;
import tinycc.implementation.Compiler;
import tinycc.implementation.expression.AssignExpression;
import tinycc.implementation.expression.BinaryExpression;
import tinycc.implementation.expression.Expression;
import tinycc.implementation.utils.BinaryOperator;
import tinycc.implementation.utils.EnvironmentalDeclaration;

import java.util.Collection;

public class AssumeStatement extends Statement {

    private final Expression condition;

    public AssumeStatement(Expression condition) {
        this.condition = condition;
    }

    public Expression getCondition() {
        return condition;
    }

    @Override
    public void updateEnvironment(Collection<EnvironmentalDeclaration> environmentalDeclarations) {
        condition.addEnvironmentalDeclarations(environmentalDeclarations);
    }

    @Override
    public void checkSemantics() {
        condition.checkSemantics();

        if(!(condition instanceof BinaryExpression))
            throw new FatalCompilerError(this.getLocatable(), "Expression is not a binary expression.");

        BinaryExpression binaryExpression = (BinaryExpression) condition;

        if(binaryExpression.getBinaryOperator() != BinaryOperator.EQ)
            throw new FatalCompilerError(binaryExpression.getLocatable(), "Wrong operator. Right operator = '" + BinaryOperator.EQ.toString()
                    + "', got '" + binaryExpression.getBinaryOperator().toString() + "'.");

        AssignExpression assignExpression = new AssignExpression(binaryExpression.getFirstExpression(), binaryExpression.getSecondExpression());

        assignExpression.addEnvironmentalDeclarations(this.getEnvironmentalDeclarations());
        assignExpression.setLocatable(binaryExpression.getLocatable());

        this.setEnvironmentalDeclarations(assignExpression.getEnvironmentalDeclarations());
    }

    @Override
    public String toString() {
        return "_Assume(" + condition.toString() + ");";
    }
}
