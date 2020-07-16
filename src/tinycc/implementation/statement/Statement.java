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

	public void updateEnvironment(Collection<EnvironmentalDeclaration> environmentalDeclarations) {}

	public void checkSemantics() {}

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
