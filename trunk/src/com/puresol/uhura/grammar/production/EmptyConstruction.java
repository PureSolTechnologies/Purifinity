package com.puresol.uhura.grammar.production;

public class EmptyConstruction {

	public static final String EMPTY_CONSTRUCTION_NAME = "_EMTPTY_";

	private static Construction instance = null;

	public static Construction getInstance() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			instance = new TokenConstruction(EMPTY_CONSTRUCTION_NAME);
		}
	}

}
