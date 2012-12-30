package com.puresol.uhura.grammar;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;

import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.grammar.production.Terminal;
import com.puresol.uhura.grammar.token.TokenDefinition;
import com.puresol.uhura.grammar.token.TokenDefinitionSet;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.preprocessor.Preprocessor;

/**
 * This class keeps the complete information about a single grammar including
 * all options, token definitions and productions.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Grammar implements Serializable {

    private static final long serialVersionUID = 8296461694750760942L;

    private final String name;
    private final Properties options;
    private final TokenDefinitionSet tokenDefinitions;
    private final ProductionSet productions;
    private final Class<? extends Preprocessor> preProcessorClass;
    private final Class<? extends Lexer> lexerClass;
    private final Class<? extends Parser> parserClass;
    private final boolean usesPreProcessor;
    private final boolean ignoreCase;

    public Grammar(Properties options, TokenDefinitionSet tokenDefinitions,
	    ProductionSet productions) throws GrammarException {
	super();
	this.name = options.getProperty("grammar.name");
	this.options = options;
	this.tokenDefinitions = tokenDefinitions;
	this.productions = productions;
	this.ignoreCase = Boolean.valueOf(options
		.getProperty("grammar.ignore-case"));

	usesPreProcessor = Boolean.valueOf(options
		.getProperty("preprocessor.use"));
	if (usesPreProcessor) {
	    this.preProcessorClass = getClass(Preprocessor.class,
		    "preprocessor");
	} else {
	    this.preProcessorClass = null;
	}
	this.lexerClass = getClass(Lexer.class, "lexer");
	this.parserClass = getClass(Parser.class, "parser");

	checkConsistencyIfConfigured();
    }

    private <T> Class<? extends T> getClass(Class<T> ifce, String propertyName)
	    throws GrammarException {
	String className = options.getProperty(propertyName);
	if (className == null) {
	    throw new GrammarException("No class name for property '"
		    + propertyName + "' was specified!");
	}
	try {
	    @SuppressWarnings("unchecked")
	    Class<? extends T> clazz = (Class<? extends T>) Class
		    .forName(className);
	    return clazz;
	} catch (ClassNotFoundException e) {
	    throw new GrammarException("Cannot instantiate '" + className
		    + "' for '" + propertyName + "'!", e);
	}
    }

    private void checkConsistencyIfConfigured() throws GrammarException {
	if (!Boolean.valueOf(options.getProperty("grammar.checks"))) {
	    return;
	}
	for (Production production : productions.getList()) {
	    for (Construction construction : production.getConstructions()) {
		if (construction.isTerminal()) {
		    if (!construction.getClass().equals(Terminal.class)) {
			continue;
		    }
		    if (tokenDefinitions.getDefinition(construction.getName()) == null) {
			throw new GrammarException("Token definition '"
				+ construction.getName()
				+ "' used in production '" + production
				+ "'is not defined ");
		    }
		} else {
		    if (productions.get(construction.getName()) == null) {
			throw new GrammarException("Production '"
				+ construction.getName()
				+ "' used in production '" + production
				+ "'is not defined ");
		    }
		}
	    }
	}
    }

    public final String getName() {
	return name;
    }

    /**
     * @return the options
     */
    public final Properties getOptions() {
	return options;
    }

    /**
     * @return the tokenDefinitions
     */
    public final TokenDefinitionSet getTokenDefinitions() {
	return tokenDefinitions;
    }

    /**
     * @return the productions
     */
    public final ProductionSet getProductions() {
	return productions;
    }

    public final Production getProduction(int productionId) {
	return productions.get(productionId);
    }

    public final boolean isIgnoreCase() {
	return ignoreCase;
    }

    public Preprocessor createPreprocessor() throws GrammarException {
	try {
	    return preProcessorClass.newInstance();
	} catch (InstantiationException e) {
	    throw new GrammarException(
		    "Cannot instantiate preprocessor with class'"
			    + preProcessorClass.getName() + "'!", e);
	} catch (IllegalAccessException e) {
	    throw new GrammarException(
		    "Cannot instantiate preprocessor with class'"
			    + preProcessorClass.getName() + "'!", e);
	}
    }

    public Lexer createLexer() throws GrammarException {
	try {
	    return lexerClass.getConstructor(Grammar.class).newInstance(this);
	} catch (InstantiationException e) {
	    throw new GrammarException("Cannot instantiate lexer with class'"
		    + lexerClass.getName() + "'!", e);
	} catch (IllegalAccessException e) {
	    throw new GrammarException("Cannot instantiate lexer with class'"
		    + lexerClass.getName() + "'!", e);
	} catch (IllegalArgumentException e) {
	    throw new GrammarException("Cannot instantiate lexer with class'"
		    + lexerClass.getName() + "'!", e);
	} catch (SecurityException e) {
	    throw new GrammarException("Cannot instantiate lexer with class'"
		    + lexerClass.getName() + "'!", e);
	} catch (InvocationTargetException e) {
	    throw new GrammarException("Cannot instantiate lexer with class'"
		    + lexerClass.getName() + "'!", e);
	} catch (NoSuchMethodException e) {
	    throw new GrammarException("Cannot instantiate lexer with class'"
		    + lexerClass.getName() + "'!", e);
	}
    }

    public Parser createParser() throws GrammarException {
	try {
	    return parserClass.getConstructor(Grammar.class).newInstance(this);
	} catch (InstantiationException e) {
	    throw new GrammarException("Cannot instantiate parser with class'"
		    + parserClass.getName() + "'!", e);
	} catch (IllegalAccessException e) {
	    throw new GrammarException("Cannot instantiate parser with class'"
		    + parserClass.getName() + "'!", e);
	} catch (IllegalArgumentException e) {
	    throw new GrammarException("Cannot instantiate parser with class'"
		    + parserClass.getName() + "'!", e);
	} catch (SecurityException e) {
	    throw new GrammarException("Cannot instantiate parser with class'"
		    + parserClass.getName() + "'!", e);
	} catch (InvocationTargetException e) {
	    throw new GrammarException("Cannot instantiate parser with class'"
		    + parserClass.getName() + "'!", e);
	} catch (NoSuchMethodException e) {
	    throw new GrammarException("Cannot instantiate parser with class'"
		    + parserClass.getName() + "'!", e);
	}
    }

    public boolean usesPreProcessor() {
	return usesPreProcessor;
    }

    @Override
    public final String toString() {
	StringBuilder buffer = new StringBuilder();
	buffer.append("=========\n");
	buffer.append(" Grammar\n");
	buffer.append("=========\n");
	buffer.append("\n");
	buffer.append("Options:\n");
	buffer.append("--------\n");
	buffer.append(toOptionsString());
	buffer.append("\n");
	buffer.append("Tokens:\n");
	buffer.append("-------\n");
	buffer.append(toTokenDefinitionsString());
	buffer.append("\n");
	buffer.append("Productions:\n");
	buffer.append("------------\n");
	buffer.append(toProductionsString());
	buffer.append("\n");
	return buffer.toString();
    }

    private StringBuilder toOptionsString() {
	StringBuilder buffer = new StringBuilder();
	for (Object key : options.keySet()) {
	    buffer.append(key + " : " + options.getProperty((String) key)
		    + "\n");
	}
	return buffer;
    }

    private StringBuilder toTokenDefinitionsString() {
	StringBuilder buffer = new StringBuilder();
	for (TokenDefinition definition : tokenDefinitions.getDefinitions()) {
	    buffer.append(definition + "\n");
	}
	return buffer;
    }

    public final StringBuilder toProductionsString() {
	StringBuilder buffer = new StringBuilder();
	List<Production> productionsList = productions.getList();
	for (Production production : productionsList) {
	    buffer.append(production.toShortString(-1) + "\n");
	}
	return buffer;
    }

    public final Grammar createWithNewStartProduction(String string)
	    throws GrammarException {
	return new Grammar(getOptions(), getTokenDefinitions(),
		getProductions().setNewStartProduction(string));
    }

    @Override
    public final int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (ignoreCase ? 1231 : 1237);
	result = prime * result + ((options == null) ? 0 : options.hashCode());
	result = prime * result
		+ ((productions == null) ? 0 : productions.hashCode());
	result = prime
		* result
		+ ((tokenDefinitions == null) ? 0 : tokenDefinitions.hashCode());
	return result;
    }

    @Override
    public final boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Grammar other = (Grammar) obj;
	if (ignoreCase != other.ignoreCase)
	    return false;
	if (options == null) {
	    if (other.options != null)
		return false;
	} else if (!options.equals(other.options))
	    return false;
	if (productions == null) {
	    if (other.productions != null)
		return false;
	} else if (!productions.equals(other.productions))
	    return false;
	if (tokenDefinitions == null) {
	    if (other.tokenDefinitions != null)
		return false;
	} else if (!tokenDefinitions.equals(other.tokenDefinitions))
	    return false;
	return true;
    }
}
