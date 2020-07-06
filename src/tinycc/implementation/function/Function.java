package tinycc.implementation.function;

import tinycc.implementation.statement.Block;
import tinycc.implementation.type.Type;
import tinycc.implementation.utils.Identifier;

public class Function {

    private final Type type;
    private final Identifier identifier;
    private NamedParameterList namedParameterList;
    private final Block block;

    public Function(Type type, Identifier identifier, Block block) {
        this.type = type;
        this.identifier = identifier;
        this.block = block;
    }

    public Function(Type type, Identifier identifier, NamedParameterList namedParameterList, Block block) {
        this.type = type;
        this.identifier = identifier;
        this.namedParameterList = namedParameterList;
        this.block = block;
    }

    public Type getType() {
        return type;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public boolean hasNamedParameterList() {
        return namedParameterList != null;
    }

    public NamedParameterList getNamedParameterList() {
        return namedParameterList;
    }

    public Block getBlock() {
        return block;
    }

    @Override
    public String toString() {
        return type.toString() + " " + identifier.toString() + " ( " + (hasNamedParameterList() ? namedParameterList.toString() : "") + " ) "
                + block.toString();
    }
}
