package com.puresol.coding.lang.fortran.grammar.parts.clause5_attribute_declarations_and_specifications;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R501_TypeDeclarationStmtIT {

    @Test
    public void testDoubleComplex() throws Exception {
	assertTrue(FortranGrammarPartTester.test("type-declaration-stmt",
		"      DOUBLE COMPLEX DC\n"));
    }

    @Test
    public void testInteger() throws Exception {
	assertTrue(FortranGrammarPartTester.test("type-declaration-stmt",
		"      INTEGER(4) i, j\n"));
    }

    @Test
    public void testInteger2() throws Exception {
	assertTrue(FortranGrammarPartTester.test("type-declaration-stmt",
		"INTEGER(4) :: NODOGR\n"));
    }

}
