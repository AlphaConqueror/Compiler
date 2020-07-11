package tinycc.implementation.type;

public class Character extends WholeNumber {

    private final char character;

    public Character() {
        this.character = '\u0000';
    }

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
