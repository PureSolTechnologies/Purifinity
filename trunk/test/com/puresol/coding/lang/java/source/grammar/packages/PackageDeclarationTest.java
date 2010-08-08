package com.puresol.coding.lang.java.source.grammar.packages;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.Assert;
import junit.framework.TestCase;

public class PackageDeclarationTest extends TestCase {

	@Test
	public void testValidPackageDeclarations() {
		Logger.getRootLogger().setLevel(Level.DEBUG);
		Assert.assertTrue(JavaGrammarTester.valid("package a;",
				PackageDeclaration.class));
		Assert.assertTrue(JavaGrammarTester.valid("package a.b;",
				PackageDeclaration.class));
		Assert.assertTrue(JavaGrammarTester.valid("package a.b.c;",
				PackageDeclaration.class));
	}

	@Test
	public void testInvalidPackageDeclarations() {
		Assert.assertTrue(JavaGrammarTester.invalid("",
				PackageDeclaration.class));
		Assert.assertTrue(JavaGrammarTester.invalid("package ;",
				PackageDeclaration.class));
		Assert.assertTrue(JavaGrammarTester.invalid("package *;",
				PackageDeclaration.class));
		Assert.assertTrue(JavaGrammarTester.invalid("package a.*;",
				PackageDeclaration.class));
	}
}
