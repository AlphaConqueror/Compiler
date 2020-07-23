package tinycc.implementation.statement;

import tinycc.diagnostic.Locatable;
import tinycc.implementation.type.Type;
import tinycc.implementation.utils.EnvironmentalDeclaration;
import tinycc.implementation.utils.ReturnInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * The main statement class (see project description)
 *
 * You can change this class but the given name of the class must not be
 * modified.
 */
public abstract class Statement {

	private Locatable locatable;
	private List<EnvironmentalDeclaration> environmentalDeclarations = new ArrayList<>();

	/**
	 * Gets the locatable of the statement.
	 *
	 * @return The locatable of the statement.
	 */
	public Locatable getLocatable() {
		return locatable;
	}

	/**
	 * Sets the location of the statement.
	 *
	 * @param locatable The locatable to be set.
	 */
	public void setLocatable(Locatable locatable) {
		this.locatable = locatable;
	}

	/**
	 * Gets a {@link List} of the environmental declarations provided.
	 *
	 * @return A list of the environmental declarations provided.
	 */
	public List<EnvironmentalDeclaration> getEnvironmentalDeclarations() {
		return environmentalDeclarations;
	}

	/**
	 * Adds an environmental declaration to the environment.
	 * Updates the environment of all sub statements after adding.
	 *
	 * @param environmentalDeclaration The environmental declaration to be added.
	 */
	public void addEnvironmentalDeclaration(EnvironmentalDeclaration environmentalDeclaration) {
		environmentalDeclarations.add(environmentalDeclaration);

		updateEnvironment(Collections.singleton(environmentalDeclaration));
	}

	/**
	 * Adds all environmental declarations in the {@link Collection} to the environment.
	 * Updates the environment of all sub statements after adding.
	 *
	 * @param environmentalDeclarations The collection of environmental declarations to be added.
	 */
	public void addEnvironmentalDeclarations(Collection<EnvironmentalDeclaration> environmentalDeclarations) {
		for(EnvironmentalDeclaration environmentalDeclaration : environmentalDeclarations)
			this.environmentalDeclarations.add(environmentalDeclaration);

		updateEnvironment(environmentalDeclarations);
	}

	/**
	 * Updates the environment of this and all sub statements,
	 * by adding all environmental declarations in the {@link Collection} to the environment.
	 *
	 * @param environmentalDeclarations The collection of environmental declarations used
	 *                                  to update the environment and all sub environments.
	 */
	public void updateEnvironment(Collection<EnvironmentalDeclaration> environmentalDeclarations) {}

	/**
	 * Checks the semantics of the statement.
	 *
	 * @note Has to be called recursively for every sub statement.
	 */
	public void checkSemantics() {}

	/**
	 * Gets the {@link ReturnInfo} of this statement.
	 *
	 * @param type The type to be compared.
	 *
	 * @return The return info.
	 * @see ReturnStatement
	 */
	public ReturnInfo getReturnInfo(Type type) {
		return new ReturnInfo(ReturnInfo.ReturnType.NO_RETURN);
	}

	/**
	 * Creates a string representation of this statement.
	 *
	 * @remarks See project documentation.
	 * @see StringBuilder
	 */
	@Override
	public abstract String toString();
}
