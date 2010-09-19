package com.puresol.coding.lang.fortran.grammar.parts.clause2_fortran_concepts;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class R204_SpecificationPartTest extends TestCase {

	@Test
	public void testSubroutineSubprogram() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("specification-part",
				"      INTEGER            INCX, INCY, N\n"
						+ "      REAL               X( * ), Y( * )\n"
						+ "      EXTERNAL           ATL_F77WRAP_SCOPY\n"));
	}

	@Test
	public void testSubroutineSubprogram2() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("specification-part",
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
}
