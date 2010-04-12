package com.puresol.coding.lang.java.source.coderanges;

import org.junit.Test;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.Java;
import com.puresol.parser.TokenStream;
import com.puresol.parser.TokenStreamTest;

import junit.framework.Assert;
import junit.framework.TestCase;

public class JavaClassTest extends TestCase {

    @Test
    public void testValues() {
	TokenStream tokenStream = TokenStreamTest.newTestTokenStream();
	JavaClass javaClass = new JavaClass("Name", tokenStream, 1, 3);
	Assert.assertEquals("Name", javaClass.getName());
	Assert.assertSame(tokenStream, javaClass.getTokenStream());
	Assert.assertEquals(1, javaClass.getStartId());
	Assert.assertEquals(3, javaClass.getStopId());
	Assert.assertEquals(Java.getInstance(), javaClass.getLanguage());
	Assert.assertEquals(CodeRangeType.CLASS, javaClass.getType());
    }
}
