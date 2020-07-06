package tinycc.implementation.type;

public class Integer extends Type {

    private final int integer;

    public Integer(int integer, int pointerGrade) {
        this.integer = integer;
        setPointerGrade(pointerGrade);
    }

    public int getInteger() {
        return integer;
    }

    @Override
    public String toString() {
        return "Integer(" + integer + ")";
    }
}
