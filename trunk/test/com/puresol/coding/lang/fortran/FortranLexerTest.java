package com.puresol.coding.lang.fortran;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

import com.puresol.coding.analysis.SourceCodeLexer;
import com.puresol.parser.lexer.LexerException;
import com.puresol.parser.lexer.NoMatchingTokenDefinitionFound;
import com.puresol.parser.tokens.Token;
import com.puresol.parser.tokens.TokenStream;

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
}
