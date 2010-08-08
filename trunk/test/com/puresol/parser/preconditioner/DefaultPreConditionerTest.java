package com.puresol.parser.preconditioner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.Test;

import com.puresol.parser.preconditioner.DefaultPreConditioner;
import com.puresol.parser.tokens.Token;
import com.puresol.parser.tokens.TokenPublicity;
import com.puresol.parser.tokens.TokenStream;
import com.puresol.testing.Tester;
import com.puresol.utils.FileUtilities;

import junit.framework.Assert;
import junit.framework.TestCase;

public class DefaultPreConditionerTest extends TestCase {

	@Test
	public void testStandards() {
		Tester.testStandards(DefaultPreConditionerTest.class);
	}

	@Test
	public void testConstructor() {
		try {
			DefaultPreConditioner conditioner = new DefaultPreConditioner(
					new File("test", FileUtilities.classToRelativePackagePath(
							DefaultPreConditionerTest.class).getPath()));
			TokenStream stream = conditioner.getTokenStream();
			Assert.assertNotNull(stream);
			List<Token> tokens = stream.getTokens();
			Assert.assertEquals(1, tokens.size());
			Token token = tokens.get(0);

			String text = readFile(new File("test",
					FileUtilities.classToRelativePackagePath(
							DefaultPreConditionerTest.class).getPath()));

			Assert.assertEquals(TokenPublicity.VISIBLE, token.getPublicity());
			Assert.assertEquals(text, token.getText());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}

	private String readFile(File file) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(file)));
		String text = "";
		String line;
		while ((line = reader.readLine()) != null) {
			text += line + "\n";
		}
		return text;
	}
}
