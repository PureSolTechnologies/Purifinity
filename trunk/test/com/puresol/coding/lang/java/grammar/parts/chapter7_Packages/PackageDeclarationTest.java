package com.puresol.coding.lang.java.grammar.parts.chapter7_Packages;

import static org.junit.Assert.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.lang.java.grammar.GrammarPartTester;

public class PackageDeclarationTest {

	@Test
	public void testSingle() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("PackageDeclaration",
				"package pkgname ;"));
	}

	@Test
	public void testMultiple() {
		assertTrue(GrammarPartTester.test("PackageDeclaration",
				"package pkgname1.pkgname2.pkgname3 ;"));
	}

}
