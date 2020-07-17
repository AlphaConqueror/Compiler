package tinycc.implementation.expression;

import tinycc.diagnostic.Locatable;
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

		updateEnvironment(Collections.singleton(environmentalDeclaration));
	}

	public void addEnvironmentalDeclarations(Collection<EnvironmentalDeclaration> environmentalDeclarations) {
		for(EnvironmentalDeclaration environmentalDeclaration : environmentalDeclarations)
			this.environmentalDeclarations.add(environmentalDeclaration);

		updateEnvironment(environmentalDeclarations);
	}

	//DEBUG
	public String getPrintedEnvironment() {
		String out = "";

		for(int i = 0; i < environmentalDeclarations.size(); i++) {
			out += environmentalDeclarations.get(i).getIdentifier();

			if(i < environmentalDeclarations.size() - 1)
				out += ",";
		}

		return "[" + out + "]";
	}

	public void updateEnvironment(Collection<EnvironmentalDeclaration> environmentalDeclarations) {}

	public boolean isIdentifier() {
		return false;
	}

	public void checkSemantics() {}

	public abstract Type getType();

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
