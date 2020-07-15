package tinycc.implementation.external;

import tinycc.implementation.utils.EnvironmentalDeclaration;

import java.util.ArrayList;
import java.util.List;

public class TranslationUnit {

    private final List<ExternalDeclaration> externalDeclarations = new ArrayList<>();

    public TranslationUnit() {}

    public TranslationUnit(ExternalDeclaration externalDeclaration) {
        addExternalDeclaration(externalDeclaration);
    }

    public TranslationUnit addExternalDeclaration(ExternalDeclaration externalDeclaration) {
        externalDeclarations.add(externalDeclaration);

        return this;
    }

    public List<ExternalDeclaration> getExternalDeclarations() {
        return externalDeclarations;
    }

    public List<EnvironmentalDeclaration> getLastEnvironmentalDeclarations() {
        return externalDeclarations.size() > 0 ? externalDeclarations.get(externalDeclarations.size() - 1).getEnvironmentalDeclarations() : new ArrayList<>();
    }

    public void checkSemantics() {
        for(ExternalDeclaration externalDeclaration : externalDeclarations)
            externalDeclaration.checkSemantics();
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
