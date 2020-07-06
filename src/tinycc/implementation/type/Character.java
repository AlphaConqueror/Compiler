package tinycc.implementation.type;

public class Character extends Type {

    private final char character;

    public Character(char character) {
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }

    @Override
    public String toString() {
        if(character == '\u0000')
            return "char";

        return "'" + character + "'";
    }
}
