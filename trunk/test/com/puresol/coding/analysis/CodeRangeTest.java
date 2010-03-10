package com.puresol.coding.analysis;

import org.junit.Test;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.Language;
import com.puresol.parser.TokenStream;
import com.puresol.parser.TokenStreamTest;

import junit.framework.Assert;
import junit.framework.TestCase;

public class CodeRangeTest extends TestCase {

    @Test
    public void testConstructor() {
	TokenStream tokenStream = TokenStreamTest.newTestTokenStream();
	CodeRange codeRange =
		new CodeRange(tokenStream.getFile(), Language.TEXT,
			CodeRangeType.FILE, "CodeRangeName", tokenStream,
			1, 3);
	Assert.assertNotNull(codeRange);
	Assert.assertSame(tokenStream.getFile(), codeRange.getFile());
	Assert.assertEquals(Language.TEXT, codeRange.getLanguage());
	Assert.assertEquals(CodeRangeType.FILE, codeRange.getType());
	Assert.assertEquals("CodeRangeName", codeRange.getName());
	Assert.assertSame(tokenStream, codeRange.getTokenStream());
	Assert.assertEquals(1, codeRange.getStart());
	Assert.assertEquals(3, codeRange.getStop());
	Assert.assertEquals("Token1\nToken2\nToken3\n", codeRange
		.getText());
    }

}
