package tinycc.implementation.external;

import tinycc.diagnostic.Locatable;
import tinycc.implementation.type.Type;
import tinycc.implementation.utils.EnvironmentalDeclaration;
import tinycc.implementation.utils.Identifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class ExternalDeclaration implements EnvironmentalDeclaration {

    private Locatable locatable;
    private final List<EnvironmentalDeclaration> environmentalDeclarations = new ArrayList<>();

    public Locatable getLocatable() {
        return locatable;
    }

    public void setLocatable(Locatable locatable) {
        this.locatable = locatable;
    }

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
