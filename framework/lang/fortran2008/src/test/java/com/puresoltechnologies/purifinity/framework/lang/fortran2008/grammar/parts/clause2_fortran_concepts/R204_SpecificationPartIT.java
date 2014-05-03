package com.puresoltechnologies.purifinity.framework.lang.fortran2008.grammar.parts.clause2_fortran_concepts;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.framework.lang.fortran2008.grammar.FortranGrammarPartTester;

public class R204_SpecificationPartIT {

    @Test
    public void test1() throws Exception {
	assertTrue(FortranGrammarPartTester.test("specification-part",//
		"      INTEGER            INCX, INCY, N\n",//
		"      REAL               X( * ), Y( * )\n",//
		"      EXTERNAL           ATL_F77WRAP_SCOPY\n"//
	));
    }

    @Test
    public void test2() throws Exception {
	assertTrue(FortranGrammarPartTester.test("specification-part",//
		"      DOUBLE COMPLEX ALPHA\n",//
		"      INTEGER INCX,INCY,LDA,M,N\n",//
		"      DOUBLE COMPLEX A(LDA,*),X(*),Y(*)\n",//
		"      DOUBLE COMPLEX ZERO\n",//
		"      PARAMETER (ZERO= (0.0D+0,0.0D+0))\n",//
		"      DOUBLE COMPLEX TEMP\n",//
		"      INTEGER I,INFO,IX,J,JY,KX\n",//
		"      EXTERNAL XERBLA\n",//
		"      INTRINSIC DCONJG,MAX\n"//
	));
    }

    @Test
    public void test3() throws Exception {
	assertTrue(FortranGrammarPartTester.test("specification-part",//
		"PARAMETER(INOM2 =  2)\n",//
		"REAL(8) CROSR\n"//
	));
    }

    @Test
    public void test4() throws Exception {
	assertTrue(FortranGrammarPartTester.test("specification-part",
		"PARAMETER(INOM2 =  2 * N)\n",//
		"\n",//
		"REAL(8) CROSR\n"//
	));
    }

    @Test
    public void test5() throws Exception {
	assertTrue(FortranGrammarPartTester.test("specification-part",//
		"IMPLICIT REAL(8) (A-H,O-Z)\n",//
		"PARAMETER(INOM2 =  2 * INOM)\n"//
	));
    }

    @Test
    public void test6() throws Exception {
	assertTrue(FortranGrammarPartTester.test("specification-part",//
		"PARAMETER(INOM2 =  2 * INOM)\n",//
		"REAL(8) CROSR\n"//
	));
    }
}
