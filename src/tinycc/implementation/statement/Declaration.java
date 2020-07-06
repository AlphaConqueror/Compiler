package tinycc.implementation.statement;

import tinycc.implementation.expression.Expression;
import tinycc.implementation.type.Type;
import tinycc.implementation.utils.Identifier;

public class Declaration extends Statement {

    private final Type type;
    private final Identifier identifier;
    private Expression expression; //optional

    public Declaration(Type type, Identifier identifier) {
        this.type = type;
        this.identifier = identifier;
    }

    public Declaration(Type type, Identifier identifier, Expression expression) {
        this.type = type;
        this.identifier = identifier;
        this.expression = expression;
    }

    public Type getType() {
        return type;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public boolean hasExpression() {
        return expression != null;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return type.toString() + " " + identifier.toString()
                + (hasExpression() ? " = " + expression.toString() : "") + ";";
    }
}
