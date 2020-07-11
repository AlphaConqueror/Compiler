package tinycc.implementation.statement;

import prog2.tests.FatalCompilerError;
import tinycc.implementation.expression.Expression;
import tinycc.implementation.type.Integer;
import tinycc.implementation.type.Type;

public class WhileStatement extends Statement {

    private final Expression condition, invariant, term;
    private final Statement statement;

    public WhileStatement(Expression condition, Statement statement, Expression invariant, Expression term) {
        this.condition = condition;
        this.statement = statement;
        this.invariant = invariant;
        this.term = term;

        //TODO: Bonus

        this.condition.addEnvironmentalDeclarations(this.getEnvironmentalDeclarations());
        this.statement.addEnvironmentalDeclarations(this.condition.getEnvironmentalDeclarations());
    }

    public Expression getCondition() {
        return condition;
    }

    public Statement getStatement() {
        return statement;
    }

    public boolean hasInvariant() {
        return invariant != null;
    }

    public Expression getInvariant() {
        return invariant;
    }

    public boolean hasTerm() {
        return term != null;
    }

    public Expression getTerm() {
        return term;
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
        return "while(" + condition.toString() + ")\n" + statement.toString();
    }
}
