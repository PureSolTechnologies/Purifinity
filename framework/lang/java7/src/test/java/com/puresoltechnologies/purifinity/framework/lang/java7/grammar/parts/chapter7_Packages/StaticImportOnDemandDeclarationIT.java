package com.puresoltechnologies.purifinity.framework.lang.java7.grammar.parts.chapter7_Packages;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.framework.lang.java7.grammar.JavaGrammarPartTester;

public class StaticImportOnDemandDeclarationIT {

	@Test
	public void test() throws Exception {
		assertTrue(JavaGrammarPartTester.test(
				"StaticImportOnDemandDeclaration",
				"import static TypeName . * ;"));
	}
}