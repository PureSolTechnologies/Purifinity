package com.puresol.coding.lang.java.source.grammar.packages;

import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.Assert;
import junit.framework.TestCase;

public class ImportDeclarationTest extends TestCase {

	@Test
	public void testValidImportDeclarations() {
		Assert.assertTrue(JavaGrammarTester.valid("import a;",
				ImportDeclaration.class));
		Assert.assertTrue(JavaGrammarTester.valid("import a.b;",
				ImportDeclaration.class));
		Assert.assertTrue(JavaGrammarTester.valid("import a.b.c;",
				ImportDeclaration.class));
		Assert.assertTrue(JavaGrammarTester.valid("import a.*;",
				ImportDeclaration.class));
		Assert.assertTrue(JavaGrammarTester.valid("import a.b.*;",
				ImportDeclaration.class));
		Assert.assertTrue(JavaGrammarTester.valid("import a.b.c.*;",
				ImportDeclaration.class));
	}

	@Test
	public void testImportPackageDeclarations() {
		Assert.assertTrue(JavaGrammarTester
				.invalid("", ImportDeclaration.class));
		Assert.assertTrue(JavaGrammarTester.invalid("import ;",
				ImportDeclaration.class));
		Assert.assertTrue(JavaGrammarTester.invalid("import *;",
				ImportDeclaration.class));
	}

	@Test
	public void testStaticValidImportDeclarations() {
		Assert.assertTrue(JavaGrammarTester.valid("import static a.b;",
				ImportDeclaration.class));
		Assert.assertTrue(JavaGrammarTester.valid("import static a.b.c;",
				ImportDeclaration.class));
		Assert.assertTrue(JavaGrammarTester.valid("import static a.*;",
				ImportDeclaration.class));
		Assert.assertTrue(JavaGrammarTester.valid("import static a.b.*;",
				ImportDeclaration.class));
		Assert.assertTrue(JavaGrammarTester.valid("import static a.b.c.*;",
				ImportDeclaration.class));
	}

	@Test
	public void testStaticImportPackageDeclarations() {
		Assert.assertTrue(JavaGrammarTester
				.invalid("", ImportDeclaration.class));
		Assert.assertTrue(JavaGrammarTester.invalid("import static ;",
				ImportDeclaration.class));
		Assert.assertTrue(JavaGrammarTester.invalid("import static a;",
				ImportDeclaration.class));
		Assert.assertTrue(JavaGrammarTester.invalid("import static *;",
				ImportDeclaration.class));
	}
}
