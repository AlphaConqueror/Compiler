package tinycc.implementation.type;

public class Void extends ObjectType {

    @Override
    public String toString() {
        return "void";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        return false;
    }
}
