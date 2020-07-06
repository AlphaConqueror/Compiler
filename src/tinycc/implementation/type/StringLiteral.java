package tinycc.implementation.type;

public class StringLiteral extends Type {

    private final String string;

    public StringLiteral(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    @Override
    public String toString() {
        return "\"" + string + "\"";
    }
}
