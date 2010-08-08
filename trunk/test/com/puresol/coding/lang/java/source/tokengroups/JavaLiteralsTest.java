package com.puresol.coding.lang.java.source.tokengroups;

import java.util.ArrayList;
import java.util.List;

import javax.swingx.config.ExtentedPackage;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.puresol.coding.lang.java.Java;
import com.puresol.coding.tokentypes.SourceTokenDefinition;
import com.puresol.parser.tokens.TokenDefinition;
import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.Instances;

public class JavaLiteralsTest {

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

	@Before
	public void testLiteralsVector() {
		Assert.assertTrue(literals.size() > 0);
	}

	@Test
	public void testLiteralsTokenInterfaces() {
		for (Class<?> clazz : literals) {
			if (clazz.getName().endsWith("Test")) {
				continue;
			}
			if (!SourceTokenDefinition.class.isAssignableFrom(clazz)) {
				Assert.fail(clazz.getName() + " has not the right interface!");
			}
		}
	}

	@Test
	public void testLiteralsCompleteness() {
		try {
			List<TokenDefinition> tokens = Instances.createInstanceList(
					TokenDefinition.class, Java.getInstance().getLiterals());
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
		} catch (ClassInstantiationException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}

}
