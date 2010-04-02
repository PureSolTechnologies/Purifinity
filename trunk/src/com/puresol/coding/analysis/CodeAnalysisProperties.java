package com.puresol.coding.analysis;

import java.io.IOException;

import com.puresol.utils.PropertyHandler;

public class CodeAnalysisProperties extends PropertyHandler {

	private static final long serialVersionUID = 5354088286642883581L;

	private static CodeAnalysisProperties instance;

	public static CodeAnalysisProperties getInstance() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			instance = new CodeAnalysisProperties();
		}
	}

	public static String getPropertyValue(String key) {
		return getInstance().getProperty(key);
	}

	private CodeAnalysisProperties() {
		super();
		load();
	}

	private void load() {
		try {
			load(getClass().getResourceAsStream(
					"/config/CodeAnalysis.properties"));
		} catch (IOException e) {
		}
	}
}
