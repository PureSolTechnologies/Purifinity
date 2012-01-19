package com.puresol.coding.lang.test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.AbstractProgrammingLanguage;
import com.puresol.coding.analysis.Analyzer;
import com.puresol.utils.PersistenceException;

/**
 * This is a test programming languages which is used as a mock up for real
 * languages.
 * 
 * This language is strictly for testing purposes only!
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TestLanguage extends AbstractProgrammingLanguage {

    private static final Logger logger = LoggerFactory
	    .getLogger(TestLanguage.class);

    private static final String[] FILE_SUFFIXES = { ".d" };

    private static TestLanguage instance = null;

    public static TestLanguage getInstance() {
	if (instance == null) {
	    createInstance();
	}
	return instance;
    }

    private static synchronized void createInstance() {
	if (instance == null) {
	    instance = new TestLanguage();
	}
    }

    public TestLanguage() {
	super("TestLanguage");
    }

    @Override
    protected String[] getValidFileSuffixes() {
	return FILE_SUFFIXES;
    }

    @Override
    public Analyzer restoreAnalyzer(File file) throws PersistenceException {
	throw new PersistenceException(
		"Persistence not implemented in TestProgrammingLanguage!");
    }

    @Override
    public Analyzer createAnalyser(File file) {
	return new TestLanguageAnalyser(file);
    }

    @Override
    public <T> T getImplementation(Class<T> clazz) {
	try {
	    URL url = getClass().getResource("/config/registry");
	    Properties properties = new Properties();
	    properties.load(url.openStream());
	    String className = (String) properties.get(clazz.getName());
	    Class<?> clazzz = Class.forName(className);
	    Constructor<?> constructor = clazzz.getConstructor();
	    @SuppressWarnings("unchecked")
	    T t = (T) constructor.newInstance();
	    return t;
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	    return null;
	} catch (IllegalArgumentException e) {
	    logger.error(e.getMessage(), e);
	    return null;
	} catch (SecurityException e) {
	    logger.error(e.getMessage(), e);
	    return null;
	} catch (InstantiationException e) {
	    logger.error(e.getMessage(), e);
	    return null;
	} catch (IllegalAccessException e) {
	    logger.error(e.getMessage(), e);
	    return null;
	} catch (InvocationTargetException e) {
	    logger.error(e.getMessage(), e);
	    return null;
	} catch (NoSuchMethodException e) {
	    logger.error(e.getMessage(), e);
	    return null;
	} catch (ClassNotFoundException e) {
	    logger.error(e.getMessage(), e);
	    return null;
	}
    }
}
