package tinycc.implementation.expression;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class CallExpression extends Expression {

    private final Expression functionReference;
    private final List<Expression> arguments;

    public CallExpression(Expression functionReference, Expression argument) {
        this.functionReference = functionReference;
        this.arguments = new LinkedList<>();

        addArgument(argument);
    }

    public CallExpression(Expression functionReference, Collection<Expression> arguments) {
        this.functionReference = functionReference;
        this.arguments = new LinkedList<>();

        addArguments(arguments);
    }

    public CallExpression addArgument(Expression argument) {
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
    public String toString() {
        String out = "";

        for(int i = 0; i < arguments.size(); i++) {
            out += arguments.get(i).toString();

            if(i < arguments.size() - 1)
                out += ", ";
        }

        return functionReference.toString() + " ( " + out + " )";
    }
}
