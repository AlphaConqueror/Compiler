package tinycc.implementation.expression;

import prog2.tests.FatalCompilerError;
import tinycc.implementation.type.Integer;
import tinycc.implementation.type.Type;

public class ConditionalExpression extends Expression {

    private final Expression condition, consequence, alternative;

    public ConditionalExpression(Expression condition, Expression consequence, Expression alternative) {
        this.condition = condition;
        this.consequence = consequence;
        this.alternative = alternative;

        this.condition.addEnvironmentalDeclarations(this.getEnvironmentalDeclarations());
        this.consequence.addEnvironmentalDeclarations(this.condition.getEnvironmentalDeclarations());
        this.alternative.addEnvironmentalDeclarations(this.condition.getEnvironmentalDeclarations());
    }

    public Expression getCondition() {
        return condition;
    }

    public Expression getConsequence() {
        return consequence;
    }

    public Expression getAlternative() {
        return alternative;
    }

    @Override
    public void checkSemantics() {
        Type conditionType = condition.getType();

        if(conditionType.getClass() != Integer.class)
            throw new FatalCompilerError(condition.getLocatable(), "The condition has the wrong type. Right type = Integer, got "
                    + conditionType.getClass().toString() + ".");
        else if(((Integer) conditionType).getInteger() < 0 || ((Integer) conditionType).getInteger() > 1)
            throw new FatalCompilerError(condition.getLocatable(), "Condition index is out of bounds [0,1]. Got " + ((Integer) conditionType).getInteger() + ".");

        if(!consequence.getType().equals(alternative.getType()))
            throw new FatalCompilerError(consequence.getLocatable(), "Consequence and alternative have different types.");
    }

    @Override
    public Type getType() {
        return consequence.getType();
    }

    @Override
    public Type eval() {
        return null;
    }

    @Override
    public String toString() {
        return condition.toString() + " ? " + consequence.toString() + " : " + alternative.toString();
    }
}
