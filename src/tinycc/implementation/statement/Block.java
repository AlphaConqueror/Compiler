package tinycc.implementation.statement;

import tinycc.implementation.utils.Declaration;

import java.util.LinkedList;
import java.util.List;

public class Block extends Statement {

    //TODO: Check if {@link Declaration} is a Statement or not.

    private final List<Object> objects;

    public Block() {
        this.objects = new LinkedList<>();
    }

    public Block addStatement(Declaration statement) {
        objects.add(statement);

        return this;
    }

    public Block addDeclaration(Declaration declaration) {
        objects.add(declaration);

        return this;
    }

    public List<Object> getObjects() {
        return objects;
    }

    @Override
    public String toString() {
        String block = "{\n";

        for(Object objects : objects)
            block += objects.toString() + "\n";

        return block + "}";
    }
}
