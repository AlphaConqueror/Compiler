package tinycc.implementation.type;

public class Integer extends Type {

    private final java.lang.Integer integer;

    public Integer(java.lang.Integer integer) {
        this.integer = integer;
    }

    public java.lang.Integer getInteger() {
        return integer;
    }

    @Override
    public String toString() {
        if(integer == null)
            return "int";

        return integer + "";
    }
}
