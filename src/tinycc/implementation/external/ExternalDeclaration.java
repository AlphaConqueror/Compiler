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

    /**
     * Gets the locatable of the external declaration.
     *
     * @return The locatable of the external declaration.
     */
    public Locatable getLocatable() {
        return locatable;
    }

    /**
     * Sets the location of the external declaration.
     *
     * @param locatable The locatable to be set.
     */
    public void setLocatable(Locatable locatable) {
        this.locatable = locatable;
    }

    /**
     * Gets a {@link List} of the environmental declarations provided.
     *
     * @return A list of the environmental declarations provided.
     */
    public List<EnvironmentalDeclaration> getEnvironmentalDeclarations() {
        return environmentalDeclarations;
    }

    /**
     * Adds an environmental declaration to the environment.
     * Updates the environment of all sub external declarations after adding.
     *
     * @param environmentalDeclaration The environmental declaration to be added.
     */
    public void addEnvironmentalDeclaration(EnvironmentalDeclaration environmentalDeclaration) {
        environmentalDeclarations.add(environmentalDeclaration);

        updateEnvironment(Collections.singleton(environmentalDeclaration));
    }

    /**
     * Adds all environmental declarations in the {@link Collection} to the environment.
     * Updates the environment of all sub external declarations after adding.
     *
     * @param environmentalDeclarations The collection of environmental declarations to be added.
     */
    public void addEnvironmentalDeclarations(Collection<EnvironmentalDeclaration> environmentalDeclarations) {
        for(EnvironmentalDeclaration environmentalDeclaration : environmentalDeclarations)
            this.environmentalDeclarations.add(environmentalDeclaration);

        updateEnvironment(environmentalDeclarations);
    }

    /**
     * Updates the environment of this and all sub external declarations,
     * by adding all environmental declarations in the {@link Collection} to the environment.
     *
     * @param environmentalDeclarations The collection of environmental declarations used
     *                                  to update the environment and all sub environments.
     */
    public void updateEnvironment(Collection<EnvironmentalDeclaration> environmentalDeclarations) {}

    /**
     * Checks the semantics of the external declaration.
     *
     * @note Has to be called recursively for every sub external declaration.
     */
    public void checkSemantics() {}

    @Override
    public abstract Type getType();

    @Override
    public abstract Identifier getIdentifier();

    /**
     * Creates a string representation of this external declaration.
     *
     * @remarks See project documentation.
     * @see StringBuilder
     */
    @Override
    public abstract String toString();
}
