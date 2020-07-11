package tinycc.implementation.type;

public class Pointer extends Scalar {

    private final Type pointsTo;

    public Pointer() {
        this.pointsTo = null;
    }

    public Pointer(Type pointsTo) {
        this.pointsTo = pointsTo;
    }

    public Type getPointsTo() {
        return pointsTo;
    }

    public Type getInnerType() {
        if(pointsTo instanceof Pointer)
            return ((Pointer) pointsTo).getInnerType();

        return pointsTo;
    }

    @Override
    public String toString() {
        return pointsTo.toString() + "*";
    }
}
