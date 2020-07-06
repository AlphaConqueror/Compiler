package tinycc.implementation.type;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class FunctionType extends Type {

    private final Type returnType;
    private final List<Type> parameters;

    public FunctionType(Type returnType, Type parameter) {
        this.returnType = returnType;
        this.parameters = new LinkedList<>();

        addParameter(parameter);
    }

    public FunctionType(Type returnType, LinkedList<Type> parameters) {
        this.returnType = returnType;
        this.parameters = parameters;
    }

    public FunctionType addParameter(Type parameter) {
        parameters.add(parameter);

        return this;
    }

    public FunctionType addParameters(Collection<Type> parameters) {
        for(Type parameter : parameters)
            addParameter(parameter);

        return this;
    }

    public Type getReturnType() {
        return returnType;
    }

    public List<Type> getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        String out = "";

        for(int i = 0; i < parameters.size(); i++) {
            out += parameters.get(i).toString();

            if(i < parameters.size() - 1)
                out += ",";
        }

        return returnType.toString() + "("  + out + ")";
    }
}
