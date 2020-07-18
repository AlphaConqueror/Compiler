package tinycc.implementation.expression;

import prog2.tests.FatalCompilerError;
import tinycc.implementation.external.function.Function;
import tinycc.implementation.external.function.FunctionDeclaration;
import tinycc.implementation.type.Character;
import tinycc.implementation.type.Integer;
import tinycc.implementation.type.StringLiteral;
import tinycc.implementation.type.Type;
import tinycc.implementation.utils.EnvironmentalDeclaration;
import tinycc.implementation.utils.Identifier;

import java.util.Collection;

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

    public boolean hasCharacterConstant() {
        return characterConstant != null;
    }

    public Character getCharacterConstant() {
        return characterConstant;
    }

    public boolean hasIdentifier() {
        return identifier != null;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public boolean hasIntegerConstant() {
        return integerConstant != null;
    }

    public Integer getIntegerConstant() {
        return integerConstant;
    }

    public boolean hasStringLiteral() {
        return stringLiteral != null;
    }

    public StringLiteral getStringLiteral() {
        return stringLiteral;
    }

    public boolean hasExpression() {
        return expression != null;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public void updateEnvironment(Collection<EnvironmentalDeclaration> environmentalDeclarations) {
        if(hasExpression())
            expression.addEnvironmentalDeclarations(environmentalDeclarations);
    }

    @Override
    public void checkSemantics() {
        if(hasExpression())
            expression.checkSemantics();
        else if(hasIdentifier()) {
            boolean identifierExists = false;

            for(EnvironmentalDeclaration environmentalDeclaration : this.getEnvironmentalDeclarations()) {
                if(environmentalDeclaration.getIdentifier().toString().equals(identifier.toString())) {
                    identifierExists = true;

                    if(environmentalDeclaration instanceof Function || environmentalDeclaration instanceof FunctionDeclaration)
                        throw new FatalCompilerError(this.getLocatable(), "The used identifier in the expression is not a correct function call.");
                }
            }

            if(!identifierExists)
                throw new FatalCompilerError(this.getLocatable(), "The identifier '" + identifier.toString() + "' does not exists.");
        }
    }

    @Override
    public boolean isIdentifier() {
        return hasIdentifier();
    }

    @Override
    public Type getType() {
        if(hasCharacterConstant())
            return new Character();
        if(hasIdentifier()) {
            Type declarationType = getDeclarationByIdentifier(identifier);

            if(declarationType != null)
                return declarationType;
            else
                return new StringLiteral();
        }
        if(hasStringLiteral())
            return new StringLiteral();
        if(hasIntegerConstant())
            return new Integer();
        if(hasExpression())
            return expression.getType();

        return null;
    }

    private Type getDeclarationByIdentifier(Identifier identifier) {
        for(EnvironmentalDeclaration environmentalDeclaration : this.getEnvironmentalDeclarations()) {
            if(environmentalDeclaration.getIdentifier().toString().equals(identifier.getIdentifier()))
                return environmentalDeclaration.getType();
        }

        return null;
    }

    @Override
    public Expression clone() {
        PrimaryExpression primaryExpression = null;

        if(hasCharacterConstant())
            primaryExpression = new PrimaryExpression(characterConstant);
        else if(hasIdentifier())
            primaryExpression = new PrimaryExpression(identifier);
        else if(hasIntegerConstant())
            primaryExpression = new PrimaryExpression(integerConstant);
        else if(hasStringLiteral())
            primaryExpression = new PrimaryExpression(stringLiteral);
        else if(hasExpression())
            primaryExpression = new PrimaryExpression(expression.clone());

        primaryExpression.setLocatable(this.getLocatable());
        primaryExpression.addEnvironmentalDeclarations(this.getEnvironmentalDeclarations());

        return primaryExpression;
    }

    @Override
    public String toString() {
        String out = null;

        if(hasCharacterConstant())
            out = characterConstant.toString();
        else if(hasIdentifier())
            out = identifier.toString();
        else if(hasIntegerConstant())
            out = integerConstant.toString();
        else if(hasStringLiteral())
            out = stringLiteral.toString();
        else if(hasExpression())
            out = expression.toString();

        return out;
    }
}
