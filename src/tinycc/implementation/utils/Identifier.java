package tinycc.implementation.utils;

public class Identifier {

    private final String identifier;

    public Identifier(char[] identifier) {
        this.identifier = identifier.toString();
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return "Identifier(" + identifier + ")";
    }
}
