package tinycc.implementation.statement;

public class ErrorStatement extends Statement {

    @Override
    public void checkSemantics() {}

    @Override
    public String toString() {
        throw new RuntimeException("Unable to print error message.");
    }
}
