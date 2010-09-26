package com.puresol.coding.lang.fortran.grammar.parts.clause2_fortran_concepts;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.FortranGrammar;
import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;
import com.puresol.trees.TreePrinter;
import com.puresol.uhura.ast.AST;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.RegExpLexer;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserManager;

public class R201_ProgramTest {

	@Test
	public void testProgram() {
		try {
			Logger.getRootLogger().setLevel(Level.TRACE);
			Grammar grammar = FortranGrammar.getInstance().getGrammar();
			Lexer lexer = new RegExpLexer(grammar);
			TokenStream tokenStream = lexer
					.lex(new InputStreamReader(
							new FileInputStream(
									new File(
											"test/com/puresol/coding/lang/fortran/samples/FortranTest.f"))));
			Parser parser = ParserManager.getManagerParser(
					GrammarPartTester.PARSER_DIRECTORY, "program-parser",
					grammar);
			AST ast = parser.parse(tokenStream);
			new TreePrinter(System.out).println(ast);
		} catch (Throwable e) {
			fail("No exception was expected!");
		}
	}

	@Test
	public void testSubroutine() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("program",
				"SUBROUTINE ZGERC(M,N,ALPHA,X,INCX,Y,INCY,A,LDA)\n"
						+ "END SUBROUTINE\n"));
		assertTrue(GrammarPartTester.test("program",
				"SUBROUTINE ZGERC(M,N,ALPHA,X,INCX,Y,INCY,A,LDA)\n"
						+ "DOUBLE COMPLEX DC\n" + "END SUBROUTINE\n"));
	}
}
