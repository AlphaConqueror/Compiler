package tinycc.implementation.external.function;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ParameterList {

    private final List<Parameter> parameters;

    public ParameterList(Parameter namedParameter) {
        parameters = new LinkedList<>();
        addParameter(namedParameter);
    }

    public ParameterList(Collection<Parameter> parameters) {
        this.parameters = new LinkedList<>();
        addParameters(parameters);
    }

    public ParameterList addParameter(Parameter parameter) {
        parameters.add(parameter);

        return this;
    }

    public ParameterList addParameters(Collection<Parameter> parameters) {
        for(Parameter parameter : parameters)
            addParameter(parameter);

        return null;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        String out = "";

        for(int i = 0; i < parameters.size(); i++) {
            out += parameters.get(i).toString();

            if (i < parameters.size() - 1)
                out += ", ";
        }

        return out;
    }
}
