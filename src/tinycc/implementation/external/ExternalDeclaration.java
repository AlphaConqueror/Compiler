package tinycc.implementation.external;

import tinycc.implementation.utils.EnvironmentalDeclaration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class ExternalDeclaration {

    private final List<EnvironmentalDeclaration> environmentalDeclarations = new ArrayList<>();

    public List<EnvironmentalDeclaration> getEnvironmentalDeclarations() {
        return environmentalDeclarations;
    }

    public void addEnvironmentalDeclaration(EnvironmentalDeclaration environmentalDeclaration) {
        environmentalDeclarations.add(environmentalDeclaration);
    }

    public void addEnvironmentalDeclarations(Collection<EnvironmentalDeclaration> environmentalDeclarations) {
        for(EnvironmentalDeclaration environmentalDeclaration : environmentalDeclarations)
            addEnvironmentalDeclaration(environmentalDeclaration);
    }

    public abstract void checkSemantics();

    /**
     * Creates a string representation of this external declaration.
     *
     * @remarks See project documentation.
     * @see StringBuilder
     */
    @Override
    public abstract String toString();
}
