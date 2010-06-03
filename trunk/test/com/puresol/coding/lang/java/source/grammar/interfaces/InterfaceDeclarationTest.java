package com.puresol.coding.lang.java.source.grammar.interfaces;

import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.Assert;
import junit.framework.TestCase;

public class InterfaceDeclarationTest extends TestCase {

	@Test
	public void testValidPackageDeclarations() {
		Assert.assertTrue(JavaGrammarTester.valid(
				"public interface a extends b" + "{" + "}",
				InterfaceDeclaration.class));
	}

	@Test
	public void testInvalidPackageDeclarations() {
		Assert.assertTrue(JavaGrammarTester.invalid(
				"public interface a implements b" + "{" + "}",
				InterfaceDeclaration.class));
	}
}
