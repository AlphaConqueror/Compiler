package tinycc.implementation.expression;

import prog2.tests.FatalCompilerError;
import tinycc.implementation.external.function.Function;
import tinycc.implementation.external.function.FunctionDeclaration;
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
    public void checkSemantics() {
        boolean hasBeenDeclared = false;

        for(EnvironmentalDeclaration environmentalDeclaration : getEnvironmentalDeclarations()) {
            if(environmentalDeclaration.getIdentifier().toString().equals(functionReference.toString())) {
                hasBeenDeclared = true;

                if (environmentalDeclaration instanceof Function) {
                    Function function = (Function) environmentalDeclaration;

                    if (function.getNamedParameterList().getNamedParameters().size() < arguments.size())
                        throw new FatalCompilerError(functionReference.getLocatable(), "Called function with too few arguments."
                                + " Right amount = " + function.getNamedParameterList().getNamedParameters().size() + " args, got " + arguments.size() + " args.");
                    if (function.getNamedParameterList().getNamedParameters().size() > arguments.size())
                        throw new FatalCompilerError(functionReference.getLocatable(), "Called function with too many arguments."
                                + " Right amount = " + function.getNamedParameterList().getNamedParameters().size() + " args, got " + arguments.size() + " args.");

                    for(int i = 0; i < function.getNamedParameterList().getNamedParameters().size(); i++) {
                        if(!function.getNamedParameterList().getNamedParameters().get(i).getType().toString().equals(arguments.get(i).getType().toString()))
                            throw new FatalCompilerError(functionReference.getLocatable(), "Argument " + i + "'s type does not match the declared arguments type."
                                    + "Right type = " + function.getNamedParameterList().getNamedParameters().get(i).getType().toString()
                                    + ", got type " + arguments.get(i).getType().toString() + ".");
                    }
                } else if (environmentalDeclaration instanceof FunctionDeclaration) {
                    FunctionDeclaration functionDeclaration = (FunctionDeclaration) environmentalDeclaration;

                    if (functionDeclaration.getParameterList().getParameters().size() < arguments.size())
                        throw new FatalCompilerError(functionReference.getLocatable(), "Called function with too few arguments."
                                + " Right amount = " + functionDeclaration.getParameterList().getParameters().size() + " args, got " + arguments.size() + " args.");
                    if (functionDeclaration.getParameterList().getParameters().size() > arguments.size())
                        throw new FatalCompilerError(functionReference.getLocatable(), "Called function with too many arguments."
                                + " Right amount = " + functionDeclaration.getParameterList().getParameters().size() + " args, got " + arguments.size() + " args.");

                    for(int i = 0; i < functionDeclaration.getParameterList().getParameters().size(); i++) {
                        if(!functionDeclaration.getParameterList().getParameters().get(i).getType().toString().equals(arguments.get(i).getType().toString()))
                            throw new FatalCompilerError(functionReference.getLocatable(), "Argument " + i + "'s type does not match the declared arguments type."
                                    + "Right type = " + functionDeclaration.getParameterList().getParameters().get(i).getType().toString()
                                    + ", got type " + arguments.get(i).getType().toString() + ".");
                    }
                } else
                    throw new FatalCompilerError(functionReference.getLocatable(), "The declaration '" + functionReference.toString() + "' is not a function.");
            }
        }

        if(!hasBeenDeclared)
            throw new FatalCompilerError(functionReference.getLocatable(), "The function '" + functionReference.toString() + "' has not been declared.");
    }

    @Override
    public Expression clone() {
        List<Expression> argClone = new ArrayList<>();

        for (Expression arg : arguments)
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
