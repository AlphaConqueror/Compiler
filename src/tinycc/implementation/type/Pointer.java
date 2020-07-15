package tinycc.implementation.type;

public class Pointer<T extends Type> extends Scalar {

    private final T type;
    private T[] array;

    public Pointer(T type) {
        this.type = type;
        this.array = (T[]) new Type[1];
    }

    public Pointer(T type, int size) {
        this.type = type;
        this.array = (T[]) new Type[size];
    }

    public Type getType() {
        return type;
    }

    public Type getInnerType() {
        if(type instanceof Pointer)
            return ((Pointer) type).getInnerType();

        return type;
    }

    public T[] getArray() {
        return array;
    }

    public void setArray(T[] array) {
        this.array = array;
    }

    @Override
    public String toString() {
        return type.toString() + "*";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        System.out.println(getClass().toString() + " ~ " + obj.getClass().toString());

        if (obj == null || getClass() != obj.getClass())
            return false;

        Pointer p = (Pointer) obj;

        System.out.println(toString() + " ~ " + p.toString());

        return p.getType().getClass() == type.getClass();
    }
}
