package tinycc.implementation.statement;

import tinycc.implementation.utils.EnvironmentalDeclaration;

import java.util.Collection;

public class EmptyStatement extends Statement {

    private final char keyword = ';';

    public EmptyStatement() {}

    @Override
    public void updateEnvironment(Collection<EnvironmentalDeclaration> environmentalDeclarations) {}

    @Override
    public void checkSemantics() {}

    @Override
    public String toString() {
        return keyword + "";
    }
}
