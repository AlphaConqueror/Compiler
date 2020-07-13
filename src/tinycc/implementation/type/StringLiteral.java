package tinycc.implementation.type;

public class StringLiteral extends Type {

    private final String string;

    public StringLiteral() {
        this.string = null;
    }

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        StringLiteral s = (StringLiteral) obj;

        return s.getString().equals(string);
    }
}
