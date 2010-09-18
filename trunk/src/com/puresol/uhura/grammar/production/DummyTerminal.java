package com.puresol.uhura.grammar.production;

public class DummyTerminal {

	public static final String EMPTY_CONSTRUCTION_NAME = "_DUMMY_";

	private static Construction instance = null;

	public static Construction getInstance() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			instance = new Terminal(EMPTY_CONSTRUCTION_NAME);
		}
	}

}
