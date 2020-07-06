package tinycc.implementation.type;

public class Pointer extends Type {

    private final Type pointsTo;

    public Pointer(Type pointsTo) {
        this.pointsTo = pointsTo;
    }

    public Type getPointsTo() {
        return pointsTo;
    }

    @Override
    public String toString() {
        return pointsTo.toString() + "*";
    }
}
