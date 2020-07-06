package tinycc.implementation.statement;

import tinycc.implementation.expression.Expression;

public class IfStatement extends Statement {

    private final Expression condition;
    private final Statement trueStatement;
    private Statement falseStatement; //optional

    public IfStatement(Expression condition, Statement trueStatement) {
        this.condition = condition;
        this.trueStatement = trueStatement;
    }

    public IfStatement(Expression condition, Statement trueStatement, Statement falseStatement) {
        this.condition = condition;
        this.trueStatement = trueStatement;
        this.falseStatement = falseStatement;
    }

    public Expression getCondition() {
        return condition;
    }

    public Statement getTrueStatement() {
        return trueStatement;
    }

    public boolean hasFalseStatement() {
        return falseStatement != null;
    }

    public Statement getFalseStatement() {
        return falseStatement;
    }

    @Override
    public String toString() {
        return "if(" + condition.toString() + ")\n" + trueStatement.toString() + (hasFalseStatement() ? "\nelse\n" + falseStatement.toString() : "");
    }
}
