package com.puresol.coding.lang.fortran;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import junit.framework.Assert;

import org.junit.Test;

import com.puresol.coding.analysis.SourceCodeLexer;
import com.puresol.coding.lang.fortran.grammar.FortranGrammar;
import com.puresol.parser.lexer.LexerException;
import com.puresol.parser.lexer.NoMatchingTokenDefinitionFound;
import com.puresol.parser.tokens.Token;
import com.puresol.parser.tokens.TokenStream;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.RegExpLexer;

public class FortranLexerTest {

	@Test
	public void testLexer() {
		try {
			FortranPreConditioner conditioner = new FortranPreConditioner(
					new File(
							"test/com/puresol/coding/lang/fortran/samples/zgerc.f"));
			TokenStream tokenStream = conditioner.getTokenStream();
			SourceCodeLexer lexer = new SourceCodeLexer(Fortran.getInstance(),
					tokenStream);
			TokenStream tokenStream2 = lexer.getTokenStream();
			for (Token token : tokenStream2.getTokens()) {
				System.out.println(token.toString());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		} catch (NoMatchingTokenDefinitionFound e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		} catch (LexerException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}

	@Test
	public void testUhuraLexer() {
		Grammar grammar = FortranGrammar.get();
		Lexer lexer = new RegExpLexer(new Properties());
		try {
			lexer.scan(new FileReader(new File(
					"test/com/puresol/coding/lang/fortran/samples/zgerc.f")),
					grammar.getTokenDefinitions());
			lexer.getTokenStream();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		} catch (com.puresol.uhura.lexer.LexerException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}
}
