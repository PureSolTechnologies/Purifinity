package com.puresoltechnologies.purifinity.coding.lang.java7.grammar.parts.chapter7_Packages;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.coding.lang.java7.grammar.JavaGrammarPartTester;

public class TypeImportOnDemandDeclarationIT {

	@Test
	public void test() throws Exception {
		assertTrue(JavaGrammarPartTester.test("TypeImportOnDemandDeclaration",
				"import java.util.*;"));
	}
}
