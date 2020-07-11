package tinycc.implementation.expression;

import tinycc.implementation.type.Type;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class CallExpression extends Expression {

    private final Expression functionReference;
    private final List<Expression> arguments;

    public CallExpression(Expression functionReference, Expression argument) {
        this.functionReference = functionReference;
        this.arguments = new LinkedList<>();

        this.functionReference.addEnvironmentalDeclarations(this.getEnvironmentalDeclarations());
        addArgument(argument);
    }

    public CallExpression(Expression functionReference, Collection<Expression> arguments) {
        this.functionReference = functionReference;
        this.arguments = new LinkedList<>();

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
    public void checkSemantics() {}

    @Override
    public Type getType() {
        return functionReference.getType();
    }

    @Override
    public Type eval() {
        return null;
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
