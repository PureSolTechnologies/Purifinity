package com.puresol.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swingx.data.LineEnd;

import org.junit.Test;

import com.puresol.testing.Tester;

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
			File file = new File(
					"test/com/puresol/parser/DefaultPreConditionerTest.java");
			DefaultPreConditioner conditioner = new DefaultPreConditioner(file);
			TokenStream stream = conditioner.getTokenStream();
			Assert.assertNotNull(stream);
			ArrayList<Token> tokens = stream.getTokens();
			Assert.assertEquals(1, tokens.size());
			Token token = tokens.get(0);

			String text = readFile(file);

			Assert.assertEquals(0, token.getTokenID());
			Assert.assertEquals(TokenPublicity.VISIBLE, token.getPublicity());
			Assert.assertEquals(text, token.getText());
			Assert.assertEquals(0, token.getStartPos());
			Assert.assertEquals(text.length(), token.getLength());
			Assert.assertEquals(0, token.getStartLine());
			Assert.assertEquals(
					text.split(LineEnd.UNIX.getString()).length - 1, token
							.getStopLine());
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
