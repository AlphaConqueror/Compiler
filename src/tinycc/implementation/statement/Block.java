package tinycc.implementation.statement;

import tinycc.implementation.type.Type;
import tinycc.implementation.utils.EnvironmentalDeclaration;
import tinycc.implementation.utils.ReturnType;

import java.util.ArrayList;
import java.util.Collection;
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
    public ReturnType getReturnType(Type type) {
        for(Statement statement : statements) {
            ReturnType returnType = statement.getReturnType(type);

            if(returnType == ReturnType.TRUE || returnType == ReturnType.NO_RETURN)
                return returnType;
        }

        return ReturnType.FALSE;
    }

    @Override
    public String toString() {
        String block = "{\n";

        for(Statement statement : statements)
            block += statement.toString() + "\n";

        return block + "}";
    }
}
