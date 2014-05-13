package com.puresoltechnologies.purifinity.framework.lang.java7.grammar.parts.chapter7_Packages;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.framework.lang.java7.grammar.JavaGrammarPartTester;

public class PackageDeclarationIT {

	@Test
	public void testSingle() throws Exception {
		assertTrue(JavaGrammarPartTester.test("PackageDeclaration",
				"package pkgname ;"));
	}

	@Test
	public void testMultiple() throws Exception {
		assertTrue(JavaGrammarPartTester.test("PackageDeclaration",
				"package pkgname1.pkgname2.pkgname3 ;"));
	}

}
