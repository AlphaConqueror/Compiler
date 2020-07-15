package tinycc.implementation.statement;

import prog2.tests.FatalCompilerError;
import tinycc.implementation.expression.Expression;
import tinycc.implementation.type.Integer;
import tinycc.implementation.type.Type;
import tinycc.implementation.utils.EnvironmentalDeclaration;
import tinycc.implementation.utils.ReturnType;

import java.util.Collection;

public class WhileStatement extends Statement {

    private final Expression condition;
    private Expression invariant, term; //optional
    private final Statement statement;

    public WhileStatement(Expression condition, Statement statement) {
        this.condition = condition;
        this.statement = statement;
    }

    public WhileStatement(Expression condition, Statement statement, Expression invariant) {
        this.condition = condition;
        this.statement = statement;
        this.invariant = invariant;
    }

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
    public void updateEnvironment(Collection<EnvironmentalDeclaration> environmentalDeclarations) {
        condition.addEnvironmentalDeclarations(environmentalDeclarations);

        if(hasInvariant())
            invariant.addEnvironmentalDeclarations(environmentalDeclarations);
        if(hasTerm())
            term.addEnvironmentalDeclarations(environmentalDeclarations);

        statement.addEnvironmentalDeclarations(environmentalDeclarations);
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
    public ReturnType getReturnType(Type type) {
        return statement.getReturnType(type);
    }

    @Override
    public String toString() {
        return "while(" + condition.toString() + ")\n"
                + (hasInvariant() ? "_Invariant(" + invariant.toString() + ")\n" : "")
                + (hasTerm() ? "_Term(" + term.toString() + ")\n" : "")
                + statement.toString();
    }
}
