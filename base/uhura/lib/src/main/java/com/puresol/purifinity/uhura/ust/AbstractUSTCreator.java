package com.puresol.purifinity.uhura.ust;

public abstract class AbstractUSTCreator implements USTCreator {

	private final USTCreator creator;

	public AbstractUSTCreator(USTCreator creator) {
		this.creator = creator;
	}

	protected final USTCreator getParentCreator() {
		return creator;
	}

	protected void assertEquals(String message, int expected, int actual) {
		if (expected != actual) {
			throw new UniversalSyntaxTreeCreatorException(message
					+ "(expected: " + expected + "; actual: '" + actual + "')");
		}
	}

	protected void fail(String message) {
		throw new UniversalSyntaxTreeCreatorException(message);
	}
}
