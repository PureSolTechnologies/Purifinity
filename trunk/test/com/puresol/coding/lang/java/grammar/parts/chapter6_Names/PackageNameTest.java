package com.puresol.coding.lang.java.grammar.parts.chapter6_Names;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.GrammarPartTester;

public class PackageNameTest {

	@Test
	public void testEmptyCompilationUnit() {
		assertTrue(GrammarPartTester.test("PackageName",
				"packageName.packageName.packageName.packageName."
						+ "packageName.packageName.packageName."
						+ "packageName.packageName.packageName."
						+ "packageName.packageName.packageName."
						+ "packageName.packageName.packageName."
						+ "packageName.packageName.packageName."
						+ "packageName.packageName.packageName."
						+ "packageName.packageName.packageName."
						+ "packageName.packageName.packageName."
						+ "packageName.packageName.packageName."
						+ "packageName.packageName.packageName"));
	}
}
