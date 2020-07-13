package tinycc.implementation.utils;

import tinycc.implementation.type.Type;

public interface EnvironmentalDeclaration {

    public Identifier getIdentifier();

    public Type getType();
}
