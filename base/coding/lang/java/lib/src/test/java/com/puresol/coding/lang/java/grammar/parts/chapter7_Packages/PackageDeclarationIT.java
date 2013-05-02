package com.puresol.coding.lang.java.grammar.parts.chapter7_Packages;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

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
