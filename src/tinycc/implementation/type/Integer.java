package tinycc.implementation.type;

public class Integer extends WholeNumber {

    private final java.lang.Integer integer;

    public Integer() {
        this.integer = null;
    }

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
