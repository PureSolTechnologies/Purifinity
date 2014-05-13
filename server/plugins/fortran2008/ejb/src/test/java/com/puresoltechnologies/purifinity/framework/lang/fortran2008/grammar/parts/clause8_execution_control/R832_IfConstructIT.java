package com.puresoltechnologies.purifinity.framework.lang.fortran2008.grammar.parts.clause8_execution_control;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.framework.lang.fortran2008.grammar.FortranGrammarPartTester;

public class R832_IfConstructIT {

    @Test
    public void test() throws Exception {
	assertTrue(FortranGrammarPartTester.test("if-construct",//
		"      IF (A > 0.0) THEN \n",//
		"          B = SQRT (A)  \n",//
		"          C = LOG (A)   \n",//
		"      END IF            \n"//
	));
    }

    @Test
    public void test2() throws Exception {
	assertTrue(FortranGrammarPartTester.test("if-construct",//
		"      IF (M.LT.0) THEN \n",//
		"          INFO = 9     \n",//
		"      END IF           \n"//
	));
    }

    @Test
    public void test3() throws Exception {
	assertTrue(FortranGrammarPartTester.test("if-construct",//
		"      IF (M.LT.0) THEN      \n",//
		"          INFO = 1          \n",//
		"      ELSE IF (N.LT.0) THEN \n",//
		"          INFO = 9          \n",//
		"      END IF                \n"//
	));
    }

    @Test
    public void test4() throws Exception {
	assertTrue(FortranGrammarPartTester.test("if-construct",//
		"      IF (M.LT.0) THEN               \n",//
		"          INFO = 1                   \n",//
		"      ELSE IF (N.LT.0) THEN          \n",//
		"          INFO = 2                   \n",//
		"      ELSE IF (INCX.EQ.0) THEN       \n",//
		"          INFO = 5                   \n",//
		"      ELSE IF (INCY.EQ.0) THEN       \n",//
		"          INFO = 7                   \n",//
		"      ELSE IF (LDA.LT.MAX(1,M)) THEN \n",//
		"          INFO = 9                   \n",//
		"      ELSE                           \n",//
		"          INFO = 10                  \n",//
		"      END IF                         \n"//
	));
    }

    @Test
    public void test5() throws Exception {
	assertTrue(FortranGrammarPartTester
		.test("if-construct",//
			"IF (ICAS2 .LE. NCAS .AND. II .GT. 12 .AND. IZ .EQ. 1) THEN \n",//
			"    ICAS1 = ICAS2                                          \n",//
			"    GOTO 20                                                \n",//
			"ENDIF                                                      \n"//
		));
    }

    @Test
    public void test6() throws Exception {
	assertTrue(FortranGrammarPartTester.test("if-construct",//
		"if (a < b) THEN \n",//
		"    a = b       \n",//
		"ENDIF           \n"//
	));
    }
}
