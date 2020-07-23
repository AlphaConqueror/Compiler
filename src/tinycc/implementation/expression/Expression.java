package tinycc.implementation.expression;

import tinycc.diagnostic.Locatable;
import tinycc.implementation.external.function.Function;
import tinycc.implementation.external.function.FunctionDeclaration;
import tinycc.implementation.type.Type;
import tinycc.implementation.utils.EnvironmentalDeclaration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * The main expression class (see project description)
 *
 * You can change this class but the given name of the class must not be
 * modified.
 */
public abstract class Expression {

	private Locatable locatable;
	private final List<EnvironmentalDeclaration> environmentalDeclarations = new ArrayList<>();

	/**
	 * Gets the locatable of the expression.
	 *
	 * @return The locatable of the expression.
	 */
	public Locatable getLocatable() {
		return locatable;
	}

	/**
	 * Sets the location of the expression.
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
	 * Updates the environment of all sub expressions after adding.
	 *
	 * @param environmentalDeclaration The environmental declaration to be added.
	 */
	public void addEnvironmentalDeclaration(EnvironmentalDeclaration environmentalDeclaration) {
		environmentalDeclarations.add(environmentalDeclaration);

		updateEnvironment(Collections.singleton(environmentalDeclaration));
	}

	/**
	 * Adds all environmental declarations in the {@link Collection} to the environment.
	 * Updates the environment of all sub expressions after adding.
	 *
	 * @param environmentalDeclarations The collection of environmental declarations to be added.
	 */
	public void addEnvironmentalDeclarations(Collection<EnvironmentalDeclaration> environmentalDeclarations) {
		for(EnvironmentalDeclaration environmentalDeclaration : environmentalDeclarations)
			this.environmentalDeclarations.add(environmentalDeclaration);

		updateEnvironment(environmentalDeclarations);
	}

	/**
	 * Updates the environment of this and all sub expressions,
	 * by adding all environmental declarations in the {@link Collection} to the environment.
	 *
	 * @param environmentalDeclarations The collection of environmental declarations used
	 *                                  to update the environment and all sub environments.
	 */
	public void updateEnvironment(Collection<EnvironmentalDeclaration> environmentalDeclarations) {}

	/**
	 * Checks if the expression is an identifier.
	 *
	 * @return true, if the expression is an identifier, false, if otherwise and by default.
	 */
	public boolean isIdentifier() {
		return false;
	}

	/**
	 * Used for semantic checks.
	 * Checks if the expression is a wrong called function.
	 *
	 * Example: int foo() {return 0;}
	 * 			int a = foo; <-- is a wrong called function
	 *
	 * @return true, if the expression is a wrong called function, false, if otherwise.
	 */
	public boolean isWrongCalledFunction() {
		for(EnvironmentalDeclaration environmentalDeclaration : environmentalDeclarations) {
			if(this instanceof PrimaryExpression) {
				if(environmentalDeclaration.getIdentifier().equals(toString())) {
					if(environmentalDeclaration instanceof Function || environmentalDeclaration instanceof FunctionDeclaration)
						return true;
				}
			}
		}

		return false;
	}

	/**
	 * Checks the semantics of the expression.
	 *
	 * @note Has to be called recursively for every sub expression.
	 */
	public void checkSemantics() {}

	/**
	 * Gets the {@link Type} of the expression.
	 *
	 * @return The type of the expression.
	 */
	public abstract Type getType();

	/**
	 * Evaluates the expression.
	 *
	 * @return The evaluated type of the expression.
	 *
	 * @note Not fully implemented yet.
	 */
	public abstract Type eval();

	/**
	 * Clones the expression.
	 *
	 * @return The clone of the expression.
	 */
	public abstract Expression clone();

	/**
	 * Creates a string representation of this expression.
	 *
	 * @remarks See project documentation.
	 * @see StringBuilder
	 */
	@Override
	public abstract String toString();

}
