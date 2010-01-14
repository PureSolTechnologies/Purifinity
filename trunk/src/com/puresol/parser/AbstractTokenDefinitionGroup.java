package com.puresol.parser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public abstract class AbstractTokenDefinitionGroup implements TokenDefinitionGroup {

    private static final Logger logger =
	    Logger.getLogger(AbstractTokenDefinition.class);

    public ArrayList<TokenDefinition> definitions =
	    new ArrayList<TokenDefinition>();

    public AbstractTokenDefinitionGroup() {
	initialize();
    }

    protected abstract void initialize();

    @Override
    public void addTokenDefinition(Class<? extends TokenDefinition> keyword) {
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
    public void addTokenDefinition(TokenDefinition keyword) {
	definitions.add(keyword);
    }

    @Override
    public void addTokenDefinitions(Class<? extends TokenDefinitionGroup> definitions) {
	try {
	    Constructor<?> constructor = definitions.getConstructor();
	    TokenDefinitionGroup definitionsInstance =
		    (TokenDefinitionGroup) constructor.newInstance();
	    this.definitions.addAll(definitionsInstance.getTokenDefinitions());
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
    public void addTokenDefinitions(TokenDefinitionGroup definitions) {
	this.definitions.addAll(definitions.getTokenDefinitions());
    }

    @Override
    public ArrayList<TokenDefinition> getTokenDefinitions() {
	return definitions;
    }
}
