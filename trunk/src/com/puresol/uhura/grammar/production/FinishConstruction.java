package com.puresol.uhura.grammar.production;

public class FinishConstruction {

	public static final String FINISH_CONSTRUCTION_NAME = "_FINISH_";

	private static Construction instance = null;

	public static Construction getInstance() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			instance = new TokenConstruction(FINISH_CONSTRUCTION_NAME);
		}
	}

}
