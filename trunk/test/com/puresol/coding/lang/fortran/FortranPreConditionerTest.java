package com.puresol.coding.lang.fortran;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;

public class FortranPreConditionerTest {

	@Test
	public void testLexer() {
		try {
			FortranPreConditioner conditioner = new FortranPreConditioner(
					new File(
							"test/com/puresol/coding/lang/fortran/samples/zgerc.f"));
			TokenStream tokenStream = conditioner.getTokenStream();
			for (Token token : tokenStream.getTokens()) {
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
