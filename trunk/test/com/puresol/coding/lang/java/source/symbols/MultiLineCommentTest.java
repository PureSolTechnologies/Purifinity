package com.puresol.coding.lang.java.source.symbols;

import junit.framework.TestCase;

import org.junit.Test;

import com.puresol.coding.lang.java.source.symbols.TraditionalComment;

public class MultiLineCommentTest extends TestCase {

	@Test
	public void test() {
		TraditionalComment comment = new TraditionalComment();
		assertEquals("/*** Test\n */", comment
				.getTokenAtStart("/*** Test\n */ AAA /* Test */"));
		assertTrue(comment.atStart("/** Test **/"));
		assertEquals("/** Test **/", comment.getTokenAtStart("/** Test **/"));
		assertFalse(comment.atStart("// how many characters have been read\n\n"));
	}

}
