package tinycc.implementation.statement;

import prog2.tests.FatalCompilerError;
import tinycc.implementation.expression.Expression;
import tinycc.implementation.type.Integer;
import tinycc.implementation.type.Type;

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

        this.condition.addEnvironmentalDeclarations(this.getEnvironmentalDeclarations());
        this.consequence.addEnvironmentalDeclarations(this.condition.getEnvironmentalDeclarations());
        this.alternative.addEnvironmentalDeclarations(this.condition.getEnvironmentalDeclarations());
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
    public void checkSemantics() {
        Type conditionType = condition.getType();

        if(conditionType.getClass() != Integer.class)
            throw new FatalCompilerError(condition.getLocatable(), "The condition has the wrong type. Right type = Integer, got "
                    + conditionType.getClass().toString() + ".");
        else if(((Integer) conditionType).getInteger() < 0 || ((Integer) conditionType).getInteger() > 1)
            throw new FatalCompilerError(condition.getLocatable(), "Condition index is out of bounds [0,1]. Got " + ((Integer) conditionType).getInteger() + ".");
    }

    @Override
    public String toString() {
        return "if(" + condition.toString() + ")\n" + consequence.toString() + (hasAlternative() ? "\nelse\n" + alternative.toString() : "");
    }
}
