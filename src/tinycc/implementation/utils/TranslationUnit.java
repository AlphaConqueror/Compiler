package tinycc.implementation.utils;

import java.util.LinkedList;
import java.util.List;

public class TranslationUnit {

    private final List<ExternalDeclaration> externalDeclarations;

    public TranslationUnit() {
        externalDeclarations = new LinkedList<>();
    }

    public TranslationUnit(ExternalDeclaration externalDeclaration) {
        externalDeclarations = new LinkedList<>();
        addExternalDeclaration(externalDeclaration);
    }

    public TranslationUnit addExternalDeclaration(ExternalDeclaration externalDeclaration) {
        externalDeclarations.add(externalDeclaration);

        return this;
    }

    public List<ExternalDeclaration> getExternalDeclarations() {
        return externalDeclarations;
    }

    @Override
    public String toString() {
        String out = "";

        for(int i = 0; i < externalDeclarations.size(); i++) {
            out += externalDeclarations.get(i);

            if(i < externalDeclarations.size() - 1)
                out += ", ";
        }

        return "TranslationUnit(" + out + ")";
    }
}
