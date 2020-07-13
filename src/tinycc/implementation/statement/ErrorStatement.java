package tinycc.implementation.statement;

import tinycc.implementation.utils.EnvironmentalDeclaration;

import java.util.Collection;

public class ErrorStatement extends Statement {

    @Override
    public void updateEnvironment(Collection<EnvironmentalDeclaration> environmentalDeclarations) {}

    @Override
    public void checkSemantics() {}

    @Override
    public String toString() {
        throw new RuntimeException("Unable to print error message.");
    }
}
