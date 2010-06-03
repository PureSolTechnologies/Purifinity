package com.puresol.coding.lang.java.source.grammar.classes;

import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.Assert;
import junit.framework.TestCase;

public class EnumDeclarationTest extends TestCase {

	@Test
	public void testValidPackageDeclarations() {
		Assert.assertTrue(JavaGrammarTester.valid("public enum a implements b"
				+ "{A, B;" + "}", EnumDeclaration.class));
	}

	@Test
	public void testInvalidPackageDeclarations() {
		Assert.assertTrue(JavaGrammarTester.invalid("public enum a extends b"
				+ "{A, B;" + "}", EnumDeclaration.class));
	}
}
