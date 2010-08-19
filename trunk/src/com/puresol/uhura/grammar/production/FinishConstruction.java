package com.puresol.uhura.grammar.production;

public class FinishConstruction {

	private static Construction instance = null;

	public static Construction getInstance() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			instance = new TokenConstruction("_FINISH_");
		}
	}

}
