package com.puresol.coding.lang.fortran.grammar.parts.clause1_overview;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class R201_ProgramTest extends TestCase {

	@Test
	public void testSubroutine() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("program",
				"SUBROUTINE ZGERC(M,N,ALPHA,X,INCX,Y,INCY,A,LDA)\n"
						+ "END SUBROUTINE"));
		assertTrue(GrammarPartTester.test("program",
				"SUBROUTINE ZGERC(M,N,ALPHA,X,INCX,Y,INCY,A,LDA)\n"
						+ "DOUBLE COMPLEX DC\n" + "END SUBROUTINE"));
	}

}
