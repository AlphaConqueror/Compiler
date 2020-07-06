package tinycc.implementation.utils;

import tinycc.implementation.expression.Expression;
import tinycc.implementation.type.Type;

public class Declaration {

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
        return "Declaration(Type(" + type.toString() + ") Identifier(" + identifier + ")"
                + (hasExpression() ? " = Expression(" + expression.toString() + ")" : "") + ")";
    }
}
