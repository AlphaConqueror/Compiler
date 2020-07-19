package tinycc.implementation.type;

public abstract class WholeNumber extends Scalar {

    public Integer add(WholeNumber wholeNumber) {
        int a = (this instanceof Character) ? java.lang.Character.getNumericValue(((Character) this).getCharacter()) : ((Integer) this).getInteger(),
            b = (wholeNumber instanceof Character) ? java.lang.Character.getNumericValue(((Character) wholeNumber).getCharacter()) : ((Integer) wholeNumber).getInteger();

        return new Integer(a + b);
    }

    public Integer sub(WholeNumber wholeNumber) {
        int a = (this instanceof Character) ? java.lang.Character.getNumericValue(((Character) this).getCharacter()) : ((Integer) this).getInteger(),
            b = (wholeNumber instanceof Character) ? java.lang.Character.getNumericValue(((Character) wholeNumber).getCharacter()) : ((Integer) wholeNumber).getInteger();

        return new Integer(a - b);
    }

    public Integer mul(WholeNumber wholeNumber) {
        int a = (this instanceof Character) ? java.lang.Character.getNumericValue(((Character) this).getCharacter()) : ((Integer) this).getInteger(),
            b = (wholeNumber instanceof Character) ? java.lang.Character.getNumericValue(((Character) wholeNumber).getCharacter()) : ((Integer) wholeNumber).getInteger();

        return new Integer(a * b);
    }

    public Integer div(WholeNumber wholeNumber) {
        int a = (this instanceof Character) ? java.lang.Character.getNumericValue(((Character) this).getCharacter()) : ((Integer) this).getInteger(),
            b = (wholeNumber instanceof Character) ? java.lang.Character.getNumericValue(((Character) wholeNumber).getCharacter()) : ((Integer) wholeNumber).getInteger();

        return new Integer(Math.floorDiv(a, b));
    }

    public Integer eq(WholeNumber wholeNumber) {
        int a = (this instanceof Character) ? java.lang.Character.getNumericValue(((Character) this).getCharacter()) : ((Integer) this).getInteger(),
            b = (wholeNumber instanceof Character) ? java.lang.Character.getNumericValue(((Character) wholeNumber).getCharacter()) : ((Integer) wholeNumber).getInteger();

        return new Integer(a == b ? 1 : 0);
    }

    public Integer notEq(WholeNumber wholeNumber) {
        return new Integer(eq(wholeNumber).getInteger() == 0 ? 1 : 0);
    }

    public Integer smaller(WholeNumber wholeNumber) {
        int a = (this instanceof Character) ? java.lang.Character.getNumericValue(((Character) this).getCharacter()) : ((Integer) this).getInteger(),
            b = (wholeNumber instanceof Character) ? java.lang.Character.getNumericValue(((Character) wholeNumber).getCharacter()) : ((Integer) wholeNumber).getInteger();

        return new Integer(a < b ? 1 : 0);
    }

    public Integer greater(WholeNumber wholeNumber) {
        int a = (this instanceof Character) ? java.lang.Character.getNumericValue(((Character) this).getCharacter()) : ((Integer) this).getInteger(),
            b = (wholeNumber instanceof Character) ? java.lang.Character.getNumericValue(((Character) wholeNumber).getCharacter()) : ((Integer) wholeNumber).getInteger();

        return new Integer(a > b ? 1 : 0);
    }

    public Integer smallerEq(WholeNumber wholeNumber) {
        int a = (this instanceof Character) ? java.lang.Character.getNumericValue(((Character) this).getCharacter()) : ((Integer) this).getInteger(),
            b = (wholeNumber instanceof Character) ? java.lang.Character.getNumericValue(((Character) wholeNumber).getCharacter()) : ((Integer) wholeNumber).getInteger();

        return new Integer(a <= b ? 1 : 0);
    }

    public Integer greaterEq(WholeNumber wholeNumber) {
        int a = (this instanceof Character) ? java.lang.Character.getNumericValue(((Character) this).getCharacter()) : ((Integer) this).getInteger(),
            b = (wholeNumber instanceof Character) ? java.lang.Character.getNumericValue(((Character) wholeNumber).getCharacter()) : ((Integer) wholeNumber).getInteger();

        return new Integer(a >= b ? 1 : 0);
    }
}
