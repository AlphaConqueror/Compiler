package tinycc.implementation.expression;

import tinycc.diagnostic.Locatable;
import tinycc.implementation.type.Type;
import tinycc.implementation.utils.EnvironmentalDeclaration;

import java.util.ArrayList;
import java.util.Collection;
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

	public Locatable getLocatable() {
		return locatable;
	}

	public void setLocatable(Locatable locatable) {
		this.locatable = locatable;
	}

	public List<EnvironmentalDeclaration> getEnvironmentalDeclarations() {
		return environmentalDeclarations;
	}

	public void addEnvironmentalDeclaration(EnvironmentalDeclaration environmentalDeclaration) {
		environmentalDeclarations.add(environmentalDeclaration);
	}

	public void addEnvironmentalDeclarations(Collection<EnvironmentalDeclaration> environmentalDeclarations) {
		for(EnvironmentalDeclaration environmentalDeclaration : environmentalDeclarations)
			addEnvironmentalDeclaration(environmentalDeclaration);
	}

	public abstract void checkSemantics();

	public abstract Type getType();

	public abstract Type eval();

	/**
	 * Creates a string representation of this expression.
	 *
	 * @remarks See project documentation.
	 * @see StringBuilder
	 */
	@Override
	public abstract String toString();

}
