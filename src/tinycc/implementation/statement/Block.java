package tinycc.implementation.statement;

import tinycc.implementation.utils.EnvironmentalDeclaration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Block extends Statement {

    private final List<Statement> statements = new ArrayList<>();

    public Block() {}

    public Block addStatement(Statement statement) {
        statement.addEnvironmentalDeclarations(statements.size() == 0 ? this.getEnvironmentalDeclarations() : statements.get(statements.size() - 1).getEnvironmentalDeclarations());
        statements.add(statement);

        return this;
    }

    public Block addStatements(Collection<Statement> statements) {
        for(Statement statement : statements) {
            addStatement(statement);
        }

        return this;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    @Override
    public void updateEnvironment(Collection<EnvironmentalDeclaration> environmentalDeclarations) {
        for(Statement statement : statements)
            statement.addEnvironmentalDeclarations(environmentalDeclarations);
    }

    @Override
    public void checkSemantics() {
        for(Statement statement : statements)
            statement.checkSemantics();
    }

    @Override
    public String toString() {
        String block = "\n" + this.getPrintedEnvironment() + "{\n";

        for(Statement statement : statements)
            block += statement.toString() + "\n";

        return block + "}";
    }
}
