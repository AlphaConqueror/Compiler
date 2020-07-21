package tinycc.implementation.utils;

import java.util.Objects;

public class Identifier {

    private final String identifier;

    public Identifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return identifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Identifier that = (Identifier) o;

        return Objects.equals(identifier, that.identifier);
    }


    public boolean equals(String identifier) {
        if (identifier == null)
            return false;

        return this.identifier.equals(identifier);
    }
}
