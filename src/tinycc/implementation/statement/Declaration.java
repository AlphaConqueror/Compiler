package tinycc.implementation.statement;

import prog2.tests.FatalCompilerError;
import tinycc.implementation.expression.Expression;
import tinycc.implementation.type.Type;
import tinycc.implementation.type.Void;
import tinycc.implementation.utils.EnvironmentalDeclaration;
import tinycc.implementation.utils.Identifier;

import java.util.ArrayList;
import java.util.List;

public class Declaration extends Statement implements EnvironmentalDeclaration {

    private final Type type;
    private final Identifier identifier;
    private Expression expression; //optional

    public Declaration(Type type, Identifier identifier) {
        this.type = type;
        this.identifier = identifier;

        checkForDuplicate(this.identifier);

        this.addEnvironmentalDeclaration(this);
    }

    public Declaration(Type type, Identifier identifier, Expression expression) {
        this.type = type;
        this.identifier = identifier;
        this.expression = expression;

        this.addEnvironmentalDeclaration(this);

        if(this.expression != null)
            this.expression.addEnvironmentalDeclarations(this.getEnvironmentalDeclarations());
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
    }

    public void checkSemantics() {
        if(type instanceof Void)
            throw new FatalCompilerError(getLocatable(), "The declaration type is void.");

        if(hasExpression())
            expression.checkSemantics();
    }

    @Override
    public String toString() {
        return type.toString() + " " + identifier.toString()
                + (hasExpression() ? " = " + expression.toString() : "") + ";";
    }
}
