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

    /**
     * Adds a {@link ExternalDeclaration} to the {@link List} of external declarations.
     *
     * @param externalDeclaration The external declaration to be added.
     *
     * @return This instance.
     */
    public TranslationUnit addExternalDeclaration(ExternalDeclaration externalDeclaration) {
        externalDeclarations.add(externalDeclaration);

        return this;
    }

    public List<ExternalDeclaration> getExternalDeclarations() {
        return externalDeclarations;
    }

    /**
     * Gets a {@link List} of {@link EnvironmentalDeclaration}s of the last added {@link ExternalDeclaration}
     * or an empty list if not available.
     *
     * @return A list of environmental declarations of the last added external declaration.
     */
    public List<EnvironmentalDeclaration> getLastEnvironmentalDeclarations() {
        return externalDeclarations.size() > 0 ? externalDeclarations.get(externalDeclarations.size() - 1).getEnvironmentalDeclarations() : new ArrayList<>();
    }

    /**
     * Checks the semantics recursively for all {@link ExternalDeclaration}s.
     */
    public void checkSemantics() {
        for(ExternalDeclaration externalDeclaration : externalDeclarations)
            externalDeclaration.checkSemantics();
    }
}
