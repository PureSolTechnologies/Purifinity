package com.puresol.coding.lang.java.source.grammar.interfaces;

import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.Assert;
import junit.framework.TestCase;

public class AnnotationTypeDeclarationTest extends TestCase {

	@Test
	public void testValidPackageDeclarations() {
		Assert.assertTrue(JavaGrammarTester.valid("public @interface a" + "{"
				+ "}", AnnotationTypeDeclaration.class));
	}

	@Test
	public void testInvalidPackageDeclarations() {
		Assert.assertTrue(JavaGrammarTester.invalid(
				"public @interface a extends b" + "{" + "}",
				AnnotationTypeDeclaration.class));
		Assert.assertTrue(JavaGrammarTester.invalid(
				"public @interface a implements b" + "{" + "}",
				AnnotationTypeDeclaration.class));
	}
}
