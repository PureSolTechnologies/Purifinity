package com.puresol.coding.lang.java.source.symbols;

import org.junit.Test;

import junit.framework.TestCase;

public class EndOfLineCommentTest extends TestCase {

	@Test
	public void testValids() {
		EndOfLineComment comment = new EndOfLineComment();
		assertTrue(comment.atStart("//\n"));
		assertTrue(comment.atStart("// Normal Comment...\n"));
		assertTrue(comment.atStart("// /* check nesting\n"));
		assertEquals("//\n", comment.getTokenAtStart("//\n"));
		assertEquals("// Normal Comment...\n", comment
				.getTokenAtStart("// Normal Comment...\n"));
		assertEquals("// /* check nesting\n", comment
				.getTokenAtStart("// /* check nesting\n"));
		assertTrue(comment.atStart("// how many characters have been read\n\n"));
	}
}
