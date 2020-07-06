package tinycc.implementation.statement;

import tinycc.implementation.expression.Expression;

public class IfStatement extends Statement {

    private final Expression condition;
    private final Statement consequence;
    private Statement alternative; //optional

    public IfStatement(Expression condition, Statement consequence) {
        this.condition = condition;
        this.consequence = consequence;
    }

    public IfStatement(Expression condition, Statement consequence, Statement alternative) {
        this.condition = condition;
        this.consequence = consequence;
        this.alternative = alternative;
    }

    public Expression getCondition() {
        return condition;
    }

    public Statement getConsequence() {
        return consequence;
    }

    public boolean hasAlternative() {
        return alternative != null;
    }

    public Statement getAlternative() {
        return alternative;
    }

    @Override
    public String toString() {
        return "if ( " + condition.toString() + " )\n" + consequence.toString() + (hasAlternative() ? "\nelse\n" + alternative.toString() : "");
    }
}
