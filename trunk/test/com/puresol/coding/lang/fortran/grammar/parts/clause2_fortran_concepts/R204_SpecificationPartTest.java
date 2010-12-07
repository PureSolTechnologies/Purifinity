package com.puresol.coding.lang.fortran.grammar.parts.clause2_fortran_concepts;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R204_SpecificationPartTest extends TestCase {

	@Test
	public void test1() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(FortranGrammarPartTester.test("specification-part",
				"      INTEGER            INCX, INCY, N\n"
						+ "      REAL               X( * ), Y( * )\n"
						+ "      EXTERNAL           ATL_F77WRAP_SCOPY\n"));
	}

	@Test
	public void test2() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(FortranGrammarPartTester.test("specification-part",
				"      DOUBLE COMPLEX ALPHA\n"
						+ "      INTEGER INCX,INCY,LDA,M,N\n"
						+ "      DOUBLE COMPLEX A(LDA,*),X(*),Y(*)\n"
						+ "      DOUBLE COMPLEX ZERO\n"
						+ "      PARAMETER (ZERO= (0.0D+0,0.0D+0))\n"
						+ "      DOUBLE COMPLEX TEMP\n"
						+ "      INTEGER I,INFO,IX,J,JY,KX\n"
						+ "      EXTERNAL XERBLA\n"
						+ "      INTRINSIC DCONJG,MAX\n"));
	}

	@Test
	public void test3() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(FortranGrammarPartTester.test("specification-part",
				"PARAMETER(INOM2 =  2)\n" + "REAL(8) CROSR\n"));
	}

	@Test
	public void test4() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(FortranGrammarPartTester.test("specification-part",
				"PARAMETER(INOM2 =  2 * N)\n\n" + "REAL(8) CROSR\n"));
	}

//	@Test
//	public void test5() {
//		Logger.getRootLogger().setLevel(Level.TRACE);
//		assertTrue(FortranGrammarPartTester.test("specification-part",
//				"IMPLICIT REAL(8) (A-H,O-Z)\n"
//						+ "PARAMETER(INOM2 =  2 * INOM)\n"));
//	}
//
//	@Test
//	public void test6() {
//		Logger.getRootLogger().setLevel(Level.TRACE);
//		assertTrue(FortranGrammarPartTester.test("specification-part",
//				"PARAMETER(INOM2 =  2 * INOM)\n" + "REAL(8) CROSR\n"));
//	}
}
