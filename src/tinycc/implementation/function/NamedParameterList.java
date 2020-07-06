package tinycc.implementation.function;

import java.util.LinkedList;
import java.util.List;

public class NamedParameterList {

    private final List<NamedParameter> namedParameters;

    public NamedParameterList(NamedParameter namedParameter) {
        namedParameters = new LinkedList<>();
        addNamedParameter(namedParameter);
    }

    public NamedParameterList(LinkedList<NamedParameter> namedParameterList) {
        this.namedParameters = namedParameterList;
    }

    public NamedParameterList addNamedParameter(NamedParameter namedParameter) {
        namedParameters.add(namedParameter);

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
