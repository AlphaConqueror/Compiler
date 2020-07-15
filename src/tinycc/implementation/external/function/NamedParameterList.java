package tinycc.implementation.external.function;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NamedParameterList {

    private final List<NamedParameter> namedParameters = new ArrayList<>();

    public NamedParameterList() {}

    public NamedParameterList(Collection<NamedParameter> namedParameterList) {
        addNamedParameters(namedParameterList);
    }

    public NamedParameterList addNamedParameter(NamedParameter namedParameter) {
        namedParameters.add(namedParameter);

        return this;
    }

    public NamedParameterList addNamedParameters(Collection<NamedParameter> namedParameters) {
        for(NamedParameter namedParameter : namedParameters)
            addNamedParameter(namedParameter);

        return this;
    }

    public List<NamedParameter> getNamedParameters() {
        return namedParameters;
    }

    @Override
    public String toString() {
        String out = "";

        for(int i = 0; i < namedParameters.size(); i++) {
            out += namedParameters.get(i).toString();

            if (i < namedParameters.size() - 1)
                out += ", ";
        }

        return out;
    }
}
