package com.puresol.coding.lang.java.grammar.parts.chapter7_Packages;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class StaticImportOnDemandDeclarationIT {

    @Test
    public void test() throws Exception {
	assertTrue(JavaGrammarPartTester.test(
		"StaticImportOnDemandDeclaration",
		"import static TypeName . * ;"));
    }
}
