package tinycc.implementation.expression;

import tinycc.implementation.type.Character;
import tinycc.implementation.type.Integer;
import tinycc.implementation.type.StringLiteral;
import tinycc.implementation.utils.Identifier;

public class PrimaryExpression extends Expression {

    private Character characterConstant;
    private Identifier identifier;
    private Integer integerConstant;
    private StringLiteral stringLiteral;
    private Expression expression;

    public PrimaryExpression(Character characterConstant) {
        this.characterConstant = characterConstant;
    }

    public PrimaryExpression(Identifier identifier) {
        this.identifier = identifier;
    }

    public PrimaryExpression(Integer integerConstant) {
        this.integerConstant = integerConstant;
    }

    public PrimaryExpression(StringLiteral stringLiteral) {
        this.stringLiteral = stringLiteral;
    }

    public PrimaryExpression(Expression expression) {
        this.expression = expression;
    }

    public Character getCharacterConstant() {
        return characterConstant;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public Integer getIntegerConstant() {
        return integerConstant;
    }

    public StringLiteral getStringLiteral() {
        return stringLiteral;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        String out = null;

        if(characterConstant != null)
            out = characterConstant + "";
        else if(identifier != null)
            out = identifier.toString();
        else if(integerConstant != null)
            out = integerConstant + "";
        else if(stringLiteral != null)
            out = stringLiteral.toString();
        else if(expression != null)
            out = expression.toString();

        return out;
    }
}
