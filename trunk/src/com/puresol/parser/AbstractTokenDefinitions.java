package com.puresol.parser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public abstract class AbstractTokenDefinitions implements TokenDefinitions {

    private static final Logger logger =
	    Logger.getLogger(AbstractTokenDefinition.class);

    public ArrayList<TokenDefinition> definitions =
	    new ArrayList<TokenDefinition>();

    public AbstractTokenDefinitions() {
	initialize();
    }

    protected abstract void initialize();

    @Override
    public void addKeyword(Class<? extends TokenDefinition> keyword) {
	try {
	    Constructor<?> constructor;
	    constructor = keyword.getConstructor();
	    definitions.add((TokenDefinition) constructor.newInstance());
	} catch (SecurityException e) {
	    logger.error(e.getMessage(), e);
	} catch (NoSuchMethodException e) {
	    logger.error(e.getMessage(), e);
	} catch (IllegalArgumentException e) {
	    logger.error(e.getMessage(), e);
	} catch (InstantiationException e) {
	    logger.error(e.getMessage(), e);
	} catch (IllegalAccessException e) {
	    logger.error(e.getMessage(), e);
	} catch (InvocationTargetException e) {
	    logger.error(e.getMessage(), e);
	}
    }

    @Override
    public void addKeyword(TokenDefinition keyword) {
	definitions.add(keyword);
    }

    @Override
    public void addKeywords(Class<? extends TokenDefinitions> definitions) {
	try {
	    Constructor<?> constructor = definitions.getConstructor();
	    TokenDefinitions definitionsInstance =
		    (TokenDefinitions) constructor.newInstance();
	    this.definitions.addAll(definitionsInstance.getKeywords());
	} catch (SecurityException e) {
	    logger.error(e.getMessage());
	} catch (NoSuchMethodException e) {
	    logger.error(e.getMessage());
	} catch (IllegalArgumentException e) {
	    logger.error(e.getMessage());
	} catch (InstantiationException e) {
	    logger.error(e.getMessage());
	} catch (IllegalAccessException e) {
	    logger.error(e.getMessage());
	} catch (InvocationTargetException e) {
	    logger.error(e.getMessage());
	}
    }

    @Override
    public void addKeywords(TokenDefinitions definitions) {
	this.definitions.addAll(definitions.getKeywords());
    }

    @Override
    public ArrayList<TokenDefinition> getKeywords() {
	return definitions;
    }
}
