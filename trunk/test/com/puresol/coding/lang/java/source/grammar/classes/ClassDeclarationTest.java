package com.puresol.coding.lang.java.source.grammar.classes;

import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.Assert;
import junit.framework.TestCase;

public class ClassDeclarationTest extends TestCase {

	@Test
	public void testValidPackageDeclarations() {
		Assert.assertTrue(JavaGrammarTester.valid(
				"public class a extends b implements c" + "{" + "}",
				ClassDeclaration.class));
	}
}
