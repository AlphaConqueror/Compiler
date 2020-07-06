package tinycc.implementation.statement;

public class EmptyStatement extends Statement {

    private final char keyword = ';';

    public EmptyStatement() {}

    @Override
    public String toString() {
        return keyword + "";
    }
}
