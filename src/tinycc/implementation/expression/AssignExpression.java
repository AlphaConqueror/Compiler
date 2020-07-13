package tinycc.implementation.expression;

import prog2.tests.FatalCompilerError;
import tinycc.implementation.external.GlobalVariable;
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

    private boolean assignEnvironmentalDeclarations(Expression expression) {
        boolean declarationExists = false;

        for(EnvironmentalDeclaration environmentalDeclaration : getEnvironmentalDeclarations()) {
            if(environmentalDeclaration.getIdentifier().toString().equals(((PrimaryExpression) left).getIdentifier().toString())) {
                declarationExists = true;

                if(environmentalDeclaration.getType().getClass() != expression.getType().getClass())
                    throw new FatalCompilerError(expression.getLocatable(), "The declaration to be assigned and the expression do not have the same type. "
                            + "Right type = " + environmentalDeclaration.getType().toString() + ", got type " + expression.getType().toString() + ".");

                if(environmentalDeclaration instanceof Declaration)
                    ((Declaration) environmentalDeclaration).setExpression(expression);
                else if(environmentalDeclaration instanceof GlobalVariable)
                    ((GlobalVariable) environmentalDeclaration).setExpression(expression);
                else
                    throw new FatalCompilerError(expression.getLocatable(), "This environmental declaration is not assignable.");
            }
        }

        return declarationExists;
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

        //TODO: Make pointer assignments possible (*b = ...)

        System.out.println(left.getClass().toString() + " --> " + left.toString());

        if(!(left instanceof PrimaryExpression) || !((PrimaryExpression) left).hasIdentifier())
            throw new FatalCompilerError(left.getLocatable(), "The declaration is not assignable.");

        if(!assignEnvironmentalDeclarations(right))
            throw new FatalCompilerError(left.getLocatable(), "The declaration to be assigned does not exist. " + left.getLocatable().getLine());
    }

    @Override
    public Type getType() {
        return left.getType();
    }

    @Override
    public Type eval() {
        return left.getType();
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " = " + right.toString() + ")";
    }
}
