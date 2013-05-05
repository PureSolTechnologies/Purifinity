package com.puresol.coding.lang.fortran.grammar.parts.clause8_execution_control;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R802_AssociateConstructIT {

    @Test
    public void test() throws Exception {
	assertTrue(FortranGrammarPartTester.test("associate-construct",//
		"ASSOCIATE ( Z => EXP(-(X**2+Y**2)) * COS(THETA) )\n",//
		"    PRINT *, A+Z, A-Z                            \n",//
		"END ASSOCIATE                                    \n"//
	));
    }

    @Test
    public void test2() throws Exception {
	assertTrue(FortranGrammarPartTester.test("associate-construct",//
		"ASSOCIATE ( XC => AX%B(I,J)%C )         \n",//
		"    XC%DV = XC%DV + PRODUCT(XC%EV(1:N)) \n",//
		"END ASSOCIATE                           \n"//
	));
    }

    @Test
    public void test3() throws Exception {
	assertTrue(FortranGrammarPartTester.test("associate-construct",//
		"ASSOCIATE ( ARRAY => AX%B(I,:)%C ) \n",//
		"    ARRAY(N)%EV = ARRAY(N-1)%EV    \n",//
		"END ASSOCIATE                      \n"//
	));
    }

    @Test
    public void test4() throws Exception {
	assertTrue(FortranGrammarPartTester
		.test("associate-construct",//
			"ASSOCIATE ( W => RESULT(I,J)%W, ZX => AX%B(I,J)%D, ZY => AY%B(I,J)%D ) \n",//
			"    W = ZX*X + ZY*Y                                                    \n",//
			"END ASSOCIATE                                                          \n"//
		));
    }
}
