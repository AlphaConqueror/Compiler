package tinycc.implementation.expression;

import prog2.tests.FatalCompilerError;
import tinycc.implementation.external.GlobalVariable;
import tinycc.implementation.external.function.Function;
import tinycc.implementation.external.function.FunctionDeclaration;
import tinycc.implementation.statement.Declaration;
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
            if(environmentalDeclaration.getIdentifier().equals(left.toString())) {
                if(environmentalDeclaration instanceof Function || environmentalDeclaration instanceof FunctionDeclaration)
                    throw new FatalCompilerError(this.getLocatable(), "A function can not be redeclared.");
            }
        }

        if(right.isWrongCalledFunction())
            throw new FatalCompilerError(right.getLocatable(), "Assign: The call '" + right.toString() + "' is not a correct function call.");

        if(!left.isIdentifier())
            throw new FatalCompilerError(this.getLocatable(), "The expression '" + left.toString() + "' is not assignable.");

        if(!left.getType().equals(right.getType()))
            throw new FatalCompilerError(this.getLocatable(),
                    left.toString() + "(" + left.getType().toString() + ")" + " != " + right.toString() + "(" + right.getType().toString() + ")");
    }

    @Override
    public Type getType() {
        return left.getType();
    }

    @Override
    public Type eval() {
        for(EnvironmentalDeclaration environmentalDeclaration : this.getEnvironmentalDeclarations()) {
            if(environmentalDeclaration.getIdentifier().equals(left.toString())) {
                if(environmentalDeclaration instanceof GlobalVariable)
                    ((GlobalVariable) environmentalDeclaration).setExpression(right);
                else
                    ((Declaration) environmentalDeclaration).setExpression(right);
            }
        }

        return right.eval();
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
