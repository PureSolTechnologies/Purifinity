package com.puresol.coding;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class ProgrammingLanguages {

    private final ArrayList<ProgrammingLanguage> languages = new ArrayList<ProgrammingLanguage>();

    private static final Logger logger = Logger
	    .getLogger(ProgrammingLanguages.class);

    private static ProgrammingLanguages instance = null;

    public static ProgrammingLanguages getInstance() {
	if (instance == null) {
	    createInstance();
	}
	return instance;
    }

    private static synchronized void createInstance() {
	if (instance == null) {
	    instance = new ProgrammingLanguages();
	}
    }

    private ProgrammingLanguages() {
	init();
    }

    private void init() {
	try {
	    for (String analyser : CodingProperties.getPropertyValue(
		    "coding.languages").split(",")) {
		Class<? extends ProgrammingLanguage> clazz = getLanguageForName(analyser);
		if (clazz != null) {
		    registerLanguage((ProgrammingLanguage) clazz.getMethod(
			    "getInstance").invoke(null));
		}
	    }
	} catch (IllegalArgumentException e) {
	    logger.error(e.getMessage(), e);
	} catch (SecurityException e) {
	    logger.error(e.getMessage(), e);
	} catch (IllegalAccessException e) {
	    logger.error(e.getMessage(), e);
	} catch (InvocationTargetException e) {
	    logger.error(e.getMessage(), e);
	} catch (NoSuchMethodException e) {
	    logger.error(e.getMessage(), e);
	}
    }

    private Class<? extends ProgrammingLanguage> getLanguageForName(
	    String metric) {
	try {
	    @SuppressWarnings("unchecked")
	    Class<? extends ProgrammingLanguage> clazz = (Class<? extends ProgrammingLanguage>) Class
		    .forName(metric);
	    return clazz;
	} catch (ClassNotFoundException e) {
	    logger.error("Class '" + metric
		    + "' was not found, but set in CodeAnalysis.properties!");
	}
	return null;
    }

    public ArrayList<ProgrammingLanguage> getLanguages() {
	return languages;
    }

    public void registerLanguage(ProgrammingLanguage language) {
	languages.add(language);
    }
}
