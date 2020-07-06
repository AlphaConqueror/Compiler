package tinycc.implementation.statement;

import tinycc.implementation.expression.Expression;

public class WhileStatement extends Statement {

    private final Expression condition, invariant, term;
    private final Statement statement;

    public WhileStatement(Expression condition, Statement statement, Expression invariant, Expression term) {
        this.condition = condition;
        this.statement = statement;
        this.invariant = invariant;
        this.term = term;
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
    public String toString() {
        return "while ( " + condition.toString() + " )\n" + statement.toString();
    }
}
