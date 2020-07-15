package tinycc.implementation.expression;

import tinycc.implementation.type.Type;
import tinycc.implementation.utils.EnvironmentalDeclaration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CallExpression extends Expression {

    private final Expression functionReference;
    private final List<Expression> arguments = new ArrayList<>();

    public CallExpression(Expression functionReference, Expression argument) {
        this.functionReference = functionReference;

        this.functionReference.addEnvironmentalDeclarations(this.getEnvironmentalDeclarations());
        addArgument(argument);
    }

    public CallExpression(Expression functionReference, Collection<Expression> arguments) {
        this.functionReference = functionReference;

        this.functionReference.addEnvironmentalDeclarations(this.getEnvironmentalDeclarations());
        addArguments(arguments);
    }

    public CallExpression addArgument(Expression argument) {
        argument.addEnvironmentalDeclarations(arguments.size() == 0 ? this.getEnvironmentalDeclarations() : arguments.get(arguments.size() - 1).getEnvironmentalDeclarations());
        arguments.add(argument);

        return this;
    }

    public CallExpression addArguments(Collection<Expression> arguments) {
        for(Expression expression : arguments)
            addArgument(expression);

        return this;
    }

    public Expression getFunctionReference() {
        return functionReference;
    }

    public List<Expression> getArguments() {
        return arguments;
    }

    @Override
    public void updateEnvironment(Collection<EnvironmentalDeclaration> environmentalDeclarations) {
        functionReference.addEnvironmentalDeclarations(environmentalDeclarations);

        for(Expression argument : arguments)
            argument.addEnvironmentalDeclarations(environmentalDeclarations);
    }

    @Override
    public Type getType() {
        return functionReference.getType();
    }

    @Override
    public Expression clone() {
        List<Expression> argClone = new ArrayList<>();

        for(Expression arg : arguments)
            argClone.add(arg.clone());

        CallExpression callExpression = new CallExpression(functionReference.clone(), argClone);

        callExpression.setLocatable(this.getLocatable());
        callExpression.addEnvironmentalDeclarations(this.getEnvironmentalDeclarations());

        return callExpression;
    }

    @Override
    public String toString() {
        String out = "";

        for(int i = 0; i < arguments.size(); i++) {
            out += arguments.get(i).toString();

            if(i < arguments.size() - 1)
                out += ", ";
        }

        return functionReference.toString() + "(" + out + ")";
    }
}
