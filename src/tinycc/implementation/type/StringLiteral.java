package tinycc.implementation.type;

public class StringLiteral extends Type {

    private final String string;

    public StringLiteral() {
        this.string = null;
    }

    public StringLiteral(String string) {
        this.string = string;
    }

    public boolean hasString() {
        return string != null;
    }

    public String getString() {
        return string;
    }

    @Override
    public String toString() {
        if(hasString())
            return "\"" + string + "\"";

        return (new Pointer<>(new Character())).toString();
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
