package com.puresol.coding.evaluator;

import java.io.IOException;
import java.util.Properties;

public class CodeEvaluationProperties extends Properties {

	private static final long serialVersionUID = 5354088286642883581L;

	private static CodeEvaluationProperties instance;

	public static CodeEvaluationProperties getInstance() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			instance = new CodeEvaluationProperties();
		}
	}

	public static String getPropertyValue(String key) {
		return getInstance().getProperty(key);
	}

	private CodeEvaluationProperties() {
		super();
		load();
	}

	private void load() {
		try {
			load(getClass().getResourceAsStream(
					"/config/CodeEvaluation.properties"));
		} catch (IOException e) {
		}
	}
}
