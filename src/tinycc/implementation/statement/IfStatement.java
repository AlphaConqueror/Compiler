package tinycc.implementation.statement;

import prog2.tests.FatalCompilerError;
import tinycc.implementation.expression.Expression;
import tinycc.implementation.type.Integer;
import tinycc.implementation.type.Type;
import tinycc.implementation.type.Void;
import tinycc.implementation.utils.EnvironmentalDeclaration;
import tinycc.implementation.utils.ReturnInfo;

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
    public ReturnInfo getReturnInfo(Type type) {
        ReturnInfo consequenceReturnInfo = consequence.getReturnInfo(type),
                   alternativeReturnInfo = alternative.getReturnInfo(type);

        if(consequenceReturnInfo.getReturnType() == ReturnInfo.ReturnType.FALSE_TYPE)
            return consequenceReturnInfo;
        if(alternativeReturnInfo.getReturnType() == ReturnInfo.ReturnType.FALSE_TYPE)
            return alternativeReturnInfo;

        if(consequenceReturnInfo.getReturnType() == alternativeReturnInfo.getReturnType())
            return consequenceReturnInfo;

        if(type.toString().equals((new Void()).toString())) {
            if(consequenceReturnInfo.getReturnType() == ReturnInfo.ReturnType.NO_VALUE || alternativeReturnInfo.getReturnType() == ReturnInfo.ReturnType.NO_VALUE) {
                if(consequenceReturnInfo.getReturnType() == ReturnInfo.ReturnType.NO_RETURN || consequenceReturnInfo.getReturnType() == ReturnInfo.ReturnType.NO_RETURN)
                    return consequenceReturnInfo.getReturnType() == ReturnInfo.ReturnType.NO_RETURN ? consequenceReturnInfo : alternativeReturnInfo;
                if(consequenceReturnInfo.getReturnType() == ReturnInfo.ReturnType.TRUE || consequenceReturnInfo.getReturnType() == ReturnInfo.ReturnType.TRUE)
                    return consequenceReturnInfo.getReturnType() == ReturnInfo.ReturnType.NO_VALUE ? consequenceReturnInfo : alternativeReturnInfo;
            }

            if(consequenceReturnInfo.getReturnType() == ReturnInfo.ReturnType.TRUE || alternativeReturnInfo.getReturnType() == ReturnInfo.ReturnType.TRUE) {
                if(consequenceReturnInfo.getReturnType() == ReturnInfo.ReturnType.NO_RETURN || consequenceReturnInfo.getReturnType() == ReturnInfo.ReturnType.NO_RETURN)
                    return consequenceReturnInfo.getReturnType() == ReturnInfo.ReturnType.NO_RETURN ? consequenceReturnInfo : alternativeReturnInfo;
            }
        }

        return new ReturnInfo(ReturnInfo.ReturnType.NO_RETURN);
    }

    @Override
    public String toString() {
        return "if(" + condition.toString() + ")\n" + consequence.toString() + (hasAlternative() ? "\nelse\n" + alternative.toString() : "");
    }
}
