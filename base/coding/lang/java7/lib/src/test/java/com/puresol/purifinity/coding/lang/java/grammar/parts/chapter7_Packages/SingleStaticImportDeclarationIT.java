package com.puresol.purifinity.coding.lang.java.grammar.parts.chapter7_Packages;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.purifinity.coding.lang.java.grammar.JavaGrammarPartTester;

public class SingleStaticImportDeclarationIT {

    @Test
    public void test() throws Exception {
	assertTrue(JavaGrammarPartTester.test("SingleStaticImportDeclaration",
		"import static TypeName . Identifier;"));
    }
}
