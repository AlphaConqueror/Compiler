package tinycc.implementation.utils;

public enum BinaryOperator {
    ADD("+"),
    SUB("-"),
    MUL("*"),
    DIV("/"),
    LT("<"),
    LE("≤"),
    GT(">"),
    GE("≥"),
    EQ("=="),
    ASSIGN("="),
    BANG_EQ("!=");

    private final String symbol;

    BinaryOperator(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return this.symbol;
    }
}
