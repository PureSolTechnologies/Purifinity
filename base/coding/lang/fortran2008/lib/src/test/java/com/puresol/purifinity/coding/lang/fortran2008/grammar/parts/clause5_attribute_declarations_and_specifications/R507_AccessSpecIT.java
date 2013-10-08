package com.puresol.purifinity.coding.lang.fortran2008.grammar.parts.clause5_attribute_declarations_and_specifications;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.purifinity.coding.lang.fortran2008.grammar.FortranGrammarPartTester;

public class R507_AccessSpecIT {

    @Test
    public void testPublic() throws Exception {
	assertTrue(FortranGrammarPartTester.test("access-spec", "PUBLIC"));
    }

    @Test
    public void testPrivate() throws Exception {
	assertTrue(FortranGrammarPartTester.test("access-spec", "PRIVATE"));
    }

}
