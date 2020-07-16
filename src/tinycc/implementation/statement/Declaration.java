package tinycc.implementation.statement;

import prog2.tests.FatalCompilerError;
import tinycc.implementation.expression.Expression;
import tinycc.implementation.type.Type;
import tinycc.implementation.type.Void;
import tinycc.implementation.utils.EnvironmentalDeclaration;
import tinycc.implementation.utils.Identifier;

import java.util.Collection;

public class Declaration extends Statement implements EnvironmentalDeclaration {

    private final Type type;
    private final Identifier identifier;
    private Expression expression; //optional

    public Declaration(Type type, Identifier identifier) {
        this.type = type;
        this.identifier = identifier;

        this.addEnvironmentalDeclaration(this);
    }

    public Declaration(Type type, Identifier identifier, Expression expression) {
        this.type = type;
        this.identifier = identifier;
        this.expression = expression;

        this.addEnvironmentalDeclaration(this);
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

        this.addEnvironmentalDeclarations(this.getEnvironmentalDeclarations());
        checkSemantics();
    }

    @Override
    public void updateEnvironment(Collection<EnvironmentalDeclaration> environmentalDeclarations) {
        if(hasExpression())
            expression.addEnvironmentalDeclarations(environmentalDeclarations);
    }

    public void checkSemantics() {
        if(type instanceof Void)
            throw new FatalCompilerError(getLocatable(), "The declaration type is void.");

        if(isDuplicate(identifier))
            throw new RuntimeException("Identifier '" + identifier.toString() + "' is already in use.");

        if(hasExpression())
            expression.checkSemantics();
    }

    @Override
    public String toString() {
        return type.toString() + " " + identifier.toString() + (hasExpression() ? " = " + expression.toString() : "") + ";";
    }
}
