package tinycc.implementation.type;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Integer i = (Integer) obj;

        return i.equals(integer);
    }
}
