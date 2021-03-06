package tinycc.implementation.utils;

public enum UnaryOperator {

    POINT_TO("*"),
    ADDRESS_OF("&"),
    SIZE_OF("sizeof"),
    NOT("!");

    private final String identifier;

    UnaryOperator(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return identifier;
    }
}
