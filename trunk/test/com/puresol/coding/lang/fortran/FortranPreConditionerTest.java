package com.puresol.coding.lang.fortran;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Pattern;

import junit.framework.Assert;

import org.junit.Test;

import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.lexer.TokenStream;

public class FortranPreConditionerTest {

	@Test
	public void testLineLeadPattern() {
		Pattern pattern = FortranPreConditioner.getLineLeadPattern();
		Assert.assertTrue(pattern.matcher("      SUBROUTINE TEST\n").find());
		Assert.assertTrue(pattern.matcher("!     SUBROUTINE TEST\n").find());
		Assert.assertTrue(pattern.matcher("!    $SUBROUTINE TEST\n").find());
		Assert.assertTrue(pattern.matcher("!   4$SUBROUTINE TEST\n").find());
		Assert.assertTrue(pattern.matcher("!  34$SUBROUTINE TEST\n").find());
		Assert.assertTrue(pattern.matcher("! 234$SUBROUTINE TEST\n").find());
		Assert.assertTrue(pattern.matcher("!1234$SUBROUTINE TEST\n").find());
	}

	@Test
	public void testLexer() {
		try {
			FortranPreConditioner conditioner = new FortranPreConditioner(
					new File(
							"test/com/puresol/coding/lang/fortran/samples/zgerc.f"));
			TokenStream tokenStream = conditioner.getTokenStream();
			for (Token token : tokenStream) {
				System.out.println(token.toString());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}
}
