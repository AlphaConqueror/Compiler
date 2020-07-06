package tinycc.implementation.expression;

import tinycc.diagnostic.Locatable;

/**
 * The main expression class (see project description)
 *
 * You can change this class but the given name of the class must not be
 * modified.
 */
public abstract class Expression {

	private Locatable locatable;

	public Locatable getLocatable() {
		return locatable;
	}

	public void setLocatable(Locatable locatable) {
		this.locatable = locatable;
	}

	/**
	 * Creates a string representation of this expression.
	 *
	 * @remarks See project documentation.
	 * @see StringBuilder
	 */
	@Override
	public abstract String toString();

}
