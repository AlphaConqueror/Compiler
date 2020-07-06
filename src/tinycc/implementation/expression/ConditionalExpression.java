package tinycc.implementation.expression;

public class ConditionalExpression extends Expression {

    private final Expression condition, consequence, alternative;

    public ConditionalExpression(Expression condition, Expression consequence, Expression alternative) {
        this.condition = condition;
        this.consequence = consequence;
        this.alternative = alternative;
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
    public String toString() {
        return condition.toString() + " ? " + consequence.toString() + " : " + alternative.toString();
    }
}
