package tinycc.implementation.statement;

import tinycc.diagnostic.Locatable;
import tinycc.implementation.utils.EnvironmentalDeclaration;

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
	private final List<EnvironmentalDeclaration> environmentalDeclarations = new ArrayList<>();

	public boolean hasLocatable() {
		return locatable != null;
	}

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

	public abstract void updateEnvironment(Collection<EnvironmentalDeclaration> environmentalDeclarations);

	public abstract void checkSemantics();

	/**
	 * Creates a string representation of this statement.
	 *
	 * @remarks See project documentation.
	 * @see StringBuilder
	 */
	@Override
	public abstract String toString();
}
