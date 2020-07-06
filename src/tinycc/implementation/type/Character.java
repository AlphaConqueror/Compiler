package tinycc.implementation.type;

public class Character extends Type {

    private final char character;

    public Character(char character, int pointerGrade) {
        this.character = character;
        setPointerGrade(pointerGrade);
    }

    public char getCharacter() {
        return character;
    }

    @Override
    public String toString() {
        return "Character('" + character + "')";
    }
}
