package com.puresol.coding.lang.fortran.grammar.parts.clause5_attribute_declarations_and_specifications;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R507_AccessSpecTest {

    @Test
    public void testPublic() throws Exception {
	assertTrue(FortranGrammarPartTester.test("access-spec", "PUBLIC"));
    }

    @Test
    public void testPrivate() throws Exception {
	assertTrue(FortranGrammarPartTester.test("access-spec", "PRIVATE"));
    }

}
