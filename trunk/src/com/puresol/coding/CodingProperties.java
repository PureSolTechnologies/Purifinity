package com.puresol.coding;

import java.io.IOException;

import com.puresol.utils.PropertyHandler;

public class CodingProperties extends PropertyHandler {

    private static final long serialVersionUID = 5354088286642883581L;

    private static CodingProperties instance;

    public static CodingProperties getInstance() {
	if (instance == null) {
	    createInstance();
	}
	return instance;
    }

    private static synchronized void createInstance() {
	if (instance == null) {
	    instance = new CodingProperties();
	}
    }

    public static String getPropertyValue(String key) {
	return getInstance().getProperty(key);
    }

    private CodingProperties() {
	super();
	load();
    }

    private void load() {
	try {
	    load(getClass().getResourceAsStream("/config/Coding.properties"));
	} catch (IOException e) {
	}
    }
}
