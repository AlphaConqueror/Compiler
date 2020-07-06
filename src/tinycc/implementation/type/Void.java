package tinycc.implementation.type;

public class Void extends Type {

    public Void(int pointerGrade) {
        setPointerGrade(pointerGrade);
    }

    @Override
    public String toString() {
        return "Void()";
    }
}
