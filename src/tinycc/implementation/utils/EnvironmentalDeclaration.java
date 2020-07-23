package tinycc.implementation.utils;

import tinycc.implementation.type.Type;

public interface EnvironmentalDeclaration {

    /**
     * Gets the identifier of the environmental declaration.
     *
     * @return The identifier of the environmental declaration.
     */
    public Identifier getIdentifier();

    /**
     * Gets the {@link Type} of the environmental declaration.
     *
     * @return The type of the environmental declaration.
     */
    public Type getType();
}
