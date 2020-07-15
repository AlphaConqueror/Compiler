package tinycc.implementation.statement;

import prog2.tests.FatalCompilerError;
import tinycc.implementation.expression.Expression;
import tinycc.implementation.type.Integer;
import tinycc.implementation.type.Type;
import tinycc.implementation.utils.EnvironmentalDeclaration;
import tinycc.implementation.utils.ReturnType;

import java.util.Collection;

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
    public void updateEnvironment(Collection<EnvironmentalDeclaration> environmentalDeclarations) {
        condition.addEnvironmentalDeclarations(environmentalDeclarations);
        consequence.addEnvironmentalDeclarations(environmentalDeclarations);

        if(alternative != null)
            alternative.addEnvironmentalDeclarations(environmentalDeclarations);
    }

    @Override
    public void checkSemantics() {
        condition.checkSemantics();
        consequence.checkSemantics();

        if(hasAlternative())
            alternative.checkSemantics();

        Type conditionType = condition.getType();

        if(conditionType.getClass() != Integer.class)
            throw new FatalCompilerError(condition.getLocatable(), "The condition has the wrong type. Right type = Integer, got "
                    + conditionType.getClass().toString() + ".");
    }

    @Override
    public ReturnType getReturnType(Type type) {
        ReturnType consequenceReturnType = consequence.getReturnType(type),
                   alternativeReturnType = alternative.getReturnType(type);

        if(consequenceReturnType == ReturnType.NO_RETURN || alternativeReturnType == ReturnType.NO_RETURN)
            return ReturnType.NO_RETURN;

        return consequenceReturnType == alternativeReturnType ? ReturnType.TRUE : ReturnType.FALSE;
    }

    @Override
    public String toString() {
        return "if(" + condition.toString() + ")\n" + consequence.toString() + (hasAlternative() ? "\nelse\n" + alternative.toString() : "");
    }
}
