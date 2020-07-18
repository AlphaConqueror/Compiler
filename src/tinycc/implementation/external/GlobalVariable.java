package tinycc.implementation.external;

import prog2.tests.FatalCompilerError;
import tinycc.implementation.expression.Expression;
import tinycc.implementation.external.function.Function;
import tinycc.implementation.external.function.FunctionDeclaration;
import tinycc.implementation.type.Type;
import tinycc.implementation.utils.EnvironmentalDeclaration;
import tinycc.implementation.utils.Identifier;

import java.util.Collection;

public class GlobalVariable extends ExternalDeclaration implements EnvironmentalDeclaration {

    private final Type type;
    private final Identifier identifier;
    private Expression expression;

    public GlobalVariable(Type type, Identifier identifier) {
        this.type = type;
        this.identifier = identifier;

        checkForDuplicate(this.identifier);

        addEnvironmentalDeclaration(this);
    }

    private void checkForDuplicate(Identifier identifier) {
        boolean isUsed = false;

        for(EnvironmentalDeclaration environmentalDeclaration : getEnvironmentalDeclarations()) {
            if(environmentalDeclaration.getIdentifier().equals(identifier)) {
                isUsed = true;
                break;
            }
        }

        if(isUsed)
            throw new RuntimeException("Identifier '" + identifier.toString() + "' is already in use.");
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public Identifier getIdentifier() {
        return identifier;
    }

    public boolean hasExpression() {
        return expression != null;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;

        checkSemantics();
    }

    private boolean isDuplicate(Identifier identifier) {
        int useCounter = 0;

        for(EnvironmentalDeclaration environmentalDeclaration : getEnvironmentalDeclarations()) {
            if(environmentalDeclaration.getIdentifier().toString().equals(identifier.toString())) {
                useCounter++;

                if(useCounter == 2)
                    return true;
            }
        }

        return false;
    }

    @Override
    public void updateEnvironment(Collection<EnvironmentalDeclaration> environmentalDeclarations) {
        if(hasExpression())
            expression.addEnvironmentalDeclarations(environmentalDeclarations);
    }

    @Override
    public void checkSemantics() {
        if(isDuplicate(identifier))
            throw new RuntimeException("Identifier '" + identifier.toString() + "' already in use.");

        if(hasExpression()) {
            expression.checkSemantics();

            if(type.getClass() != expression.getType().getClass())
                throw new FatalCompilerError(expression.getLocatable(), "The declaration type and the expression type are not equal.");
        }
    }

    @Override
    public String toString() {
            return type.toString() + " " + identifier.toString() + (hasExpression() ? " = " + expression.toString() : "") + ";";
    }
}
