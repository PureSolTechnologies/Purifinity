package com.puresol.uhura.grammar.production;

/**
 * This singleton class represents a dummy terminal for calculation purposes
 * like look ahead in LALR(1).
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DummyTerminal {

	public static final String EMPTY_CONSTRUCTION_NAME = "_DUMMY_";

	private static volatile Construction instance = null;

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
