package tinycc.implementation.external;

import tinycc.implementation.type.Type;
import tinycc.implementation.utils.EnvironmentalDeclaration;
import tinycc.implementation.utils.Identifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class ExternalDeclaration implements EnvironmentalDeclaration {

    private final List<EnvironmentalDeclaration> environmentalDeclarations = new ArrayList<>();

    public List<EnvironmentalDeclaration> getEnvironmentalDeclarations() {
        return environmentalDeclarations;
    }

    public void addEnvironmentalDeclaration(EnvironmentalDeclaration environmentalDeclaration) {
        environmentalDeclarations.add(environmentalDeclaration);

        updateEnvironment(Collections.singleton(environmentalDeclaration));
    }

    public void addEnvironmentalDeclarations(Collection<EnvironmentalDeclaration> environmentalDeclarations) {
        for(EnvironmentalDeclaration environmentalDeclaration : environmentalDeclarations)
            this.environmentalDeclarations.add(environmentalDeclaration);

        updateEnvironment(environmentalDeclarations);
    }

    public String printEnv() {
        String out = "[";

        for(int i = 0; i < environmentalDeclarations.size(); i++) {
            out += environmentalDeclarations.get(i).getIdentifier();

            if(i < environmentalDeclarations.size() - 1)
                out += ",";
        }

        return out + "]";
    }

    public void updateEnvironment(Collection<EnvironmentalDeclaration> environmentalDeclarations) {}

    public void checkSemantics() {}

    /**
     * Creates a string representation of this external declaration.
     *
     * @remarks See project documentation.
     * @see StringBuilder
     */
    @Override
    public abstract String toString();

    @Override
    public abstract Type getType();

    @Override
    public abstract Identifier getIdentifier();
}
