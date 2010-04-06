package com.puresol.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swingx.data.LineEnd;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class AbstractPreConditionerTest extends TestCase {

	class TestPreConditioner extends AbstractPreConditioner {
		public TestPreConditioner(File directory, File file)
				throws FileNotFoundException {
			super(directory, file);
		}

		public TestPreConditioner(InputStream stream, File file) {
			super(stream, file);
		}

		@Override
		protected void generateTokenStream() throws IOException {
			StringBuffer text = new StringBuffer();
			InputStream stream = getInputStream();
			byte[] buffer = new byte[1024];
			int size;
			while ((size = stream.read(buffer)) >= 0) {
				text.append(new String(buffer, 0, size));
			}
			Token token = new Token(0, TokenPublicity.VISIBLE, 0,
					text.length(), text.toString(), 0, text.toString().split(
							LineEnd.UNIX.getString()).length - 1, null);
			addToken(token);
		}
	}

	@Test
	public void testConstructor() {
		try {
			TestPreConditioner conditioner = new TestPreConditioner(new File(
					"test"), new File(
					"com/puresol/parser/AbstractPreConditionerTest.java"));
			Assert.assertNotNull(conditioner.getInputStream());
			Assert.assertNotNull(conditioner.getTokenStream());
			Assert.assertEquals(new File(
					"com/puresol/parser/AbstractPreConditionerTest.java"),
					conditioner.getTokenStream().getFile());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}
}
