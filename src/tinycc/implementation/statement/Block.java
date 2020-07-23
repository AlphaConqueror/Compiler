package tinycc.implementation.statement;

import tinycc.implementation.type.Type;
import tinycc.implementation.utils.EnvironmentalDeclaration;
import tinycc.implementation.utils.ReturnInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Block extends Statement {

    private final List<Statement> statements = new ArrayList<>();

    public Block() {}

    /**
     * Adds a {@link Statement} to the {@link List} of statements in the block.
     *
     * @param statement The statement to be added.
     *
     * @return This instance.
     */
    public Block addStatement(Statement statement) {
        statement.addEnvironmentalDeclarations(statements.size() == 0 ? this.getEnvironmentalDeclarations() : statements.get(statements.size() - 1).getEnvironmentalDeclarations());
        statements.add(statement);

        return this;
    }

    /**
     * Adds all {@link Statement}s in the {@link Collection} to the {@link List} of statements in the block.
     *
     * @param statements The collection of statements to be added.
     *
     * @return This instance.
     */
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
    public ReturnInfo getReturnInfo(Type type) {
        for(Statement statement : statements) {
            ReturnInfo returnInfo = statement.getReturnInfo(type);

            if(returnInfo.getReturnType() != ReturnInfo.ReturnType.NO_RETURN)
                return returnInfo;
        }

        return new ReturnInfo(ReturnInfo.ReturnType.NO_RETURN);
    }

    @Override
    public String toString() {
        String block = "{\n";

        for(Statement statement : statements)
            block += statement.toString() + "\n";

        return block + "}";
    }
}
