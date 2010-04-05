package com.puresol.coding.analysis;

import junit.framework.Assert;

import org.junit.Test;

import com.puresol.parser.TokenStream;
import com.puresol.parser.TokenStreamTest;
import com.puresol.testing.Tester;

public class AbstractCodeRangeTest {

	class CRange extends AbstractCodeRange {

		public CRange(String name, TokenStream tokenStream, int start, int stop) {
			super(name, tokenStream, start, stop);
		}

		@Override
		public String getLanguage() {
			return "Language";
		}

		@Override
		public CodeRangeType getType() {
			return CodeRangeType.FILE;
		}

		@Override
		public String getTypeName() {
			return getType().getIdentifier();
		}

	}

	@Test
	public void testConstructor() {
		TokenStream tokenStream = TokenStreamTest.newTestTokenStream();
		CRange codeRange = new CRange("CodeRangeName", tokenStream, 1, 3);
		Assert.assertNotNull(codeRange);
		Assert.assertSame(tokenStream.getFile(), codeRange.getFile());
		Assert.assertEquals(CodeRangeType.FILE, codeRange.getType());
		Assert.assertEquals("Language", codeRange.getLanguage());
		Assert.assertEquals("CodeRangeName", codeRange.getName());
		Assert.assertSame(tokenStream, codeRange.getTokenStream());
		Assert.assertEquals(1, codeRange.getStart());
		Assert.assertEquals(3, codeRange.getStop());
		Assert.assertEquals("Token1\nToken2\nToken3\n", codeRange.getText());
	}

	@Test
	public void testClose() {
		TokenStream tokenStream = TokenStreamTest.newTestTokenStream();
		CRange crange = new CRange("Test", tokenStream, 1, 3);
		Tester.testClone(crange);
	}

	@Test
	public void testCreatePartialCodeRange() {
		TokenStream tokenStream = TokenStreamTest.newTestTokenStream();
		CRange codeRange = new CRange("CodeRangeName", tokenStream, 5, 15);
		CodeRange partialCodeRange = codeRange.createPartialCodeRange(8, 12);
		Assert.assertEquals(CRange.class, partialCodeRange.getClass());
		Assert.assertEquals(8, partialCodeRange.getStart());
		Assert.assertEquals(12, partialCodeRange.getStop());
		System.out.println(codeRange.getText());
		System.out.println(partialCodeRange.getText());
	}
}
