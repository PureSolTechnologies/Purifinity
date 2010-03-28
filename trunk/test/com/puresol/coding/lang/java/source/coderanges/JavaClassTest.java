package com.puresol.coding.lang.java.source.coderanges;

import org.junit.Test;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.parser.TokenStream;
import com.puresol.parser.TokenStreamTest;
import com.puresol.testing.Tester;

import junit.framework.Assert;
import junit.framework.TestCase;

public class JavaClassTest extends TestCase {

	@Test
	public void testClone() {
		Tester.testClone(JavaClass.class);
	}

	@Test
	public void testConstructor() {
		TokenStream tokenStream = TokenStreamTest.newTestTokenStream();
		JavaClass codeRange = new JavaClass("CodeRangeName", tokenStream, 1, 3);
		Assert.assertNotNull(codeRange);
		Assert.assertSame(tokenStream.getFile(), codeRange.getFile());
		Assert.assertEquals(CodeRangeType.CLASS, codeRange.getType());
		Assert.assertEquals("CodeRangeName", codeRange.getName());
		Assert.assertSame(tokenStream, codeRange.getTokenStream());
		Assert.assertEquals(1, codeRange.getStart());
		Assert.assertEquals(3, codeRange.getStop());
		Assert.assertEquals("Token1\nToken2\nToken3\n", codeRange.getText());
	}

}
