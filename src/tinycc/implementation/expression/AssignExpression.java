package tinycc.implementation.expression;

import prog2.tests.FatalCompilerError;
import tinycc.implementation.external.function.Function;
import tinycc.implementation.external.function.FunctionDeclaration;
import tinycc.implementation.type.Type;
import tinycc.implementation.utils.EnvironmentalDeclaration;

import java.util.Collection;

public class AssignExpression extends Expression {

    private final Expression left, right;

    public AssignExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public void updateEnvironment(Collection<EnvironmentalDeclaration> environmentalDeclarations) {
        left.addEnvironmentalDeclarations(environmentalDeclarations);
        right.addEnvironmentalDeclarations(environmentalDeclarations);
    }

    @Override
    public void checkSemantics() {
        left.checkSemantics();
        right.checkSemantics();

        for(EnvironmentalDeclaration environmentalDeclaration : this.getEnvironmentalDeclarations()) {
            if(environmentalDeclaration.getIdentifier().toString().equals(left.toString())) {
                if(environmentalDeclaration instanceof Function || environmentalDeclaration instanceof FunctionDeclaration)
                    throw new FatalCompilerError(this.getLocatable(), "A function can not be redeclared.");
            }
        }

        if(!left.isIdentifier())
            throw new FatalCompilerError(this.getLocatable(), "The expression '" + left.toString() + "' is not assignable.");

        if(!left.getType().toString().equals(right.getType().toString()))
            throw new FatalCompilerError(this.getLocatable(),
                    left.toString() + "(" + left.getType().toString() + ")" + " != " + right.toString() + "(" + right.getType().toString() + ")");
    }

    @Override
    public Type getType() {
        return left.getType();
    }

    @Override
    public Expression clone() {
        AssignExpression assignExpression = new AssignExpression(left.clone(), right.clone());

        assignExpression.setLocatable(this.getLocatable());
        assignExpression.addEnvironmentalDeclarations(this.getEnvironmentalDeclarations());

        return assignExpression;
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " = " + right.toString() + ")";
    }
}
