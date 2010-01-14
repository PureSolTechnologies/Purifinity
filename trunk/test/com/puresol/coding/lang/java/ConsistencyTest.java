package com.puresol.coding.lang.java;

import java.util.ArrayList;

import javax.swingx.config.ExtentedPackage;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.puresol.coding.lang.java.source.tokengroups.JavaKeywords;
import com.puresol.coding.lang.java.source.tokengroups.JavaLiterals;
import com.puresol.coding.lang.java.source.tokengroups.JavaSymbols;
import com.puresol.coding.tokentypes.SourceTokenDefinition;
import com.puresol.parser.TokenDefinition;

public class ConsistencyTest {

    private static final ArrayList<Class<?>> keywords;
    static {
	keywords = new ArrayList<Class<?>>();
	try {
	    for (Class<?> clazz : ExtentedPackage
		    .getClasses("com.puresol.coding.lang.java.source.keywords")) {
		keywords.add(clazz);
	    }
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}
    }

    private static final ArrayList<Class<?>> literals;
    static {
	literals = new ArrayList<Class<?>>();
	try {
	    for (Class<?> clazz : ExtentedPackage
		    .getClasses("com.puresol.coding.lang.java.source.literals")) {
		literals.add(clazz);
	    }
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}
    }

    private static final ArrayList<Class<?>> symbols;
    static {
	symbols = new ArrayList<Class<?>>();
	try {
	    for (Class<?> clazz : ExtentedPackage
		    .getClasses("com.puresol.coding.lang.java.source.symbols")) {
		symbols.add(clazz);
	    }
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}
    }

    @Before
    public void testKeywordsVector() {
	Assert.assertTrue(keywords.size() > 0);
    }

    @Before
    public void testLiteralsVector() {
	Assert.assertTrue(literals.size() > 0);
    }

    @Before
    public void testSymbolsVector() {
	Assert.assertTrue(symbols.size() > 0);
    }

    @Test
    public void testKeywordsTokenInterfaces() {
	for (Class<?> clazz : keywords) {
	    if (clazz.getName().endsWith("Test")) {
		continue;
	    }
	    if (!SourceTokenDefinition.class.isAssignableFrom(clazz)) {
		Assert.fail(clazz.getName()
			+ " has not the right interface!");
	    }
	}
    }

    @Test
    public void testLiteralsTokenInterfaces() {
	for (Class<?> clazz : literals) {
	    if (clazz.getName().endsWith("Test")) {
		continue;
	    }
	    if (!SourceTokenDefinition.class.isAssignableFrom(clazz)) {
		Assert.fail(clazz.getName()
			+ " has not the right interface!");
	    }
	}
    }

    @Test
    public void testSymbolsTokenInterfaces() {
	for (Class<?> clazz : symbols) {
	    if (clazz.getName().endsWith("Test")) {
		continue;
	    }
	    if (!SourceTokenDefinition.class.isAssignableFrom(clazz)) {
		Assert.fail(clazz.getName()
			+ " has not the right interface!");
	    }
	}
    }

    @Test
    public void testKeywordsCompleteness() {
	ArrayList<TokenDefinition> tokens =
		new JavaKeywords().getTokenDefinitions();
	for (TokenDefinition definition : tokens) {
	    if (!keywords.contains(definition.getClass())) {
		Assert.fail(definition.getClass()
			+ " is not included in JavaKeywords!");
	    }
	}
	for (Class<?> clazz : keywords) {
	    if (clazz.getName().endsWith("Test")) {
		continue;
	    }
	    boolean found = false;
	    for (TokenDefinition definition : tokens) {
		if (definition.getClass().equals(clazz)) {
		    found = true;
		    break;
		}
	    }
	    if (!found) {
		Assert.fail(clazz.getName()
			+ " is not registered in JavaKeywords!");
	    }
	}
    }

    @Test
    public void testLiteralsCompleteness() {
	ArrayList<TokenDefinition> tokens =
		new JavaLiterals().getTokenDefinitions();
	for (TokenDefinition definition : tokens) {
	    if (!literals.contains(definition.getClass())) {
		Assert.fail(definition.getClass()
			+ " is not included in JavaKeywords!");
	    }
	}
	for (Class<?> clazz : literals) {
	    if (clazz.getName().endsWith("Test")) {
		continue;
	    }
	    boolean found = false;
	    for (TokenDefinition definition : tokens) {
		if (definition.getClass().equals(clazz)) {
		    found = true;
		    break;
		}
	    }
	    if (!found) {
		Assert.fail(clazz.getName()
			+ " is not registered in JavaKeywords!");
	    }
	}
    }

    @Test
    public void testSymbolsCompleteness() {
	ArrayList<TokenDefinition> tokens =
		new JavaSymbols().getTokenDefinitions();
	for (TokenDefinition definition : tokens) {
	    if (!symbols.contains(definition.getClass())) {
		Assert.fail(definition.getClass()
			+ " is not included in JavaSymbols!");
	    }
	}
	for (Class<?> clazz : symbols) {
	    if (clazz.getName().endsWith("Test")) {
		continue;
	    }
	    boolean found = false;
	    for (TokenDefinition definition : tokens) {
		if (definition.getClass().equals(clazz)) {
		    found = true;
		    break;
		}
	    }
	    if (!found) {
		Assert.fail(clazz.getName()
			+ " is not registered in JavaSymbols!");
	    }
	}
    }
}
