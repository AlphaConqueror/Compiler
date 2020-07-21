package tinycc.implementation.statement;

import tinycc.implementation.expression.Expression;
import tinycc.implementation.utils.EnvironmentalDeclaration;

import java.util.Collection;

public class AssumeStatement extends Statement {

    private final Expression condition;

    public AssumeStatement(Expression condition) {
        this.condition = condition;
    }

    public Expression getCondition() {
        return condition;
    }

    @Override
    public void updateEnvironment(Collection<EnvironmentalDeclaration> environmentalDeclarations) {
        condition.addEnvironmentalDeclarations(environmentalDeclarations);
    }

    @Override
    public void checkSemantics() {
        condition.checkSemantics();
    }

    @Override
    public String toString() {
        return "_Assume(" + condition.toString() + ");";
    }
}
