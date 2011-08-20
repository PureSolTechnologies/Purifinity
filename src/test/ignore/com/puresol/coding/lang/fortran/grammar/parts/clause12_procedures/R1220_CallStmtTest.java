package com.puresol.coding.lang.fortran.grammar.parts.clause12_procedures;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R1220_CallStmtTest extends TestCase {

//	@Test
//	public void test() {
//		Logger.getRootLogger().setLevel(Level.DEBUG);
//		assertTrue(FortranGrammarPartTester.test("call-stmt",
//				"      CALL ATL_F77WRAP_SCOPY\n"));
//	}
//
//	@Test
//	public void test2() {
//		Logger.getRootLogger().setLevel(Level.DEBUG);
//		assertTrue(FortranGrammarPartTester.test("call-stmt",
//				"      CALL ATL_F77WRAP_SCOPY()\n"));
//	}
//
//	@Test
//	public void test3() {
//		Logger.getRootLogger().setLevel(Level.DEBUG);
//		assertTrue(FortranGrammarPartTester.test("call-stmt",
//				"      CALL ATL_F77WRAP_SCOPY( A )\n"));
//	}
//
//	@Test
//	public void test4() {
//		Logger.getRootLogger().setLevel(Level.DEBUG);
//		assertTrue(FortranGrammarPartTester.test("call-stmt",
//				"      CALL ATL_F77WRAP_SCOPY( A, B )\n"));
//	}
//
//	@Test
//	public void test5() {
//		Logger.getRootLogger().setLevel(Level.DEBUG);
//		assertTrue(FortranGrammarPartTester.test("call-stmt",
//				"      CALL ATL_F77WRAP_SCOPY( A, B, C )\n"));
//	}

	@Test
	public void test6() {
		Logger.getRootLogger().setLevel(Level.DEBUG);
		assertTrue(FortranGrammarPartTester.test("call-stmt",
				"      CALL ATL_F77WRAP_SCOPY( N, X, INCX, Y, INCY )\n"));
	}
}
