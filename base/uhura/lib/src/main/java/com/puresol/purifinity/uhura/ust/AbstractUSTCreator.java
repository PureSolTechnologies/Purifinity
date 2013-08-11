package com.puresol.purifinity.uhura.ust;

public abstract class AbstractUSTCreator implements USTCreator {

	private final USTCreator creator;

	public AbstractUSTCreator(USTCreator creator) {
		this.creator = creator;
	}

	protected final USTCreator getParentCreator() {
		return creator;
	}
}
