package com.puresol.purifinity.coding.lang.java.grammar.parts.chapter7_Packages;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.purifinity.coding.lang.java.grammar.JavaGrammarPartTester;

public class TypeImportOnDemandDeclarationIT {

    @Test
    public void test() throws Exception {
	assertTrue(JavaGrammarPartTester.test("TypeImportOnDemandDeclaration",
		"import java.util.*;"));
    }
}
