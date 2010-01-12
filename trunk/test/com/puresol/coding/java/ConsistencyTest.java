package com.puresol.coding.java;

import java.util.ArrayList;

import javax.swingx.config.ExtentedPackage;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.puresol.coding.java.source.tokengroups.JavaKeywords;
import com.puresol.coding.tokentypes.ProgrammingLanguageTokenDefinition;
import com.puresol.parser.TokenDefinition;

public class ConsistencyTest {

	private static final ArrayList<Class<?>> classes;
	static {
		classes = new ArrayList<Class<?>>();
		try {
			for (Class<?> clazz : ExtentedPackage
					.getClasses("com.puresol.coding.java.tokens")) {
				classes.add(clazz);
			}
			for (Class<?> clazz : ExtentedPackage
					.getClasses("com.puresol.coding.java.keywords")) {
				classes.add(clazz);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Before
	public void testClassesVector() {
		Assert.assertTrue(classes.size() > 0);
	}

	@Test
	public void testTokenInterfaces() {
		for (Class<?> clazz : classes) {
			if (clazz.getName().endsWith("Test")) {
				continue;
			}
			if (!ProgrammingLanguageTokenDefinition.class
					.isAssignableFrom(clazz)) {
				Assert.fail(clazz.getName() + " has not the right interface!");
			}
		}
	}

	@Test
	public void testCompleteness() {
		ArrayList<TokenDefinition> tokens = new JavaKeywords().getKeywords();
		for (TokenDefinition definition : tokens) {
			Assert.assertTrue(classes.contains(definition.getClass()));
		}
		for (Class<?> clazz : classes) {
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
}
