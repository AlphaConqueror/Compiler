package tinycc.implementation.statement;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Block extends Statement {

    private final List<Statement> statements;

    public Block() {
        this.statements = new LinkedList<>();
    }

    public Block addStatement(Statement statement) {
        statements.add(statement);

        return this;
    }

    public Block addStatements(Collection<Statement> statements) {
        for(Statement statement : statements)
            addStatement(statement);

        return this;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    @Override
    public String toString() {
        String block = "{\n";

        for(Statement statement : statements)
            block += statement.toString() + "\n";

        return block + "}";
    }
}
