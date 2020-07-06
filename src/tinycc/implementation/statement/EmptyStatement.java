package tinycc.implementation.statement;

public class EmptyStatement extends Statement {

    private final char keyword = ';';

    @Override
    public String toString() {
        return String.valueOf(keyword);
    }
}
