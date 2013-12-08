package com.puresoltechnologies.purifinity.coding.lang.fortran2008.grammar.parts.clause4_types;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.coding.lang.fortran2008.grammar.FortranGrammarPartTester;

public class R425_DerivedTypeDefIT {

    @Test
    public void test() throws Exception {
	assertTrue(FortranGrammarPartTester.test("derived-type-def",//
		"      TYPE PERSON                  \n", //
		"          INTEGER AGE              \n", //
		"          CHARACTER (LEN = 50) NAME\n", //
		"      END TYPE PERSON              \n"//
	));
    }

}
