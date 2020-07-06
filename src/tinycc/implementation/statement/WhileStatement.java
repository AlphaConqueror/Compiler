package tinycc.implementation.statement;

import tinycc.implementation.expression.Expression;

public class WhileStatement extends Statement {

    private final Expression condition;
    private final Statement statement;

    public WhileStatement(Expression condition, Statement statement) {
        this.condition = condition;
        this.statement = statement;
    }

    public Expression getCondition() {
        return condition;
    }

    public Statement getStatement() {
        return statement;
    }

    @Override
    public String toString() {
        return "while(" + condition.toString() + ") \n" + statement.toString();
    }
}
