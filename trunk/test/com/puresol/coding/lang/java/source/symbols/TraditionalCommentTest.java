package com.puresol.coding.lang.java.source.symbols;

import junit.framework.TestCase;

import org.junit.Test;

import com.puresol.coding.lang.java.source.symbols.TraditionalComment;

public class TraditionalCommentTest extends TestCase {

	@Test
	public void test() {
		TraditionalComment comment = new TraditionalComment();
		assertEquals("/*** Test\n */", comment
				.getTokenAtStart("/*** Test\n */ AAA /* Test */"));
		assertTrue(comment.atStart("/** Test **/"));
		assertEquals("/** Test **/", comment.getTokenAtStart("/** Test **/"));
		assertEquals("/* \n    * Test\n    */", comment
				.getTokenAtStart("/* \n    * Test\n    */"));
	}

	@Test
	public void testComplex() {
		TraditionalComment comment = new TraditionalComment();
		String commentString = "    /* \n"
				+ "     * The problem:\n"
				+ "     *    delegate() may be called from the EDT of application appContext\n"
				+ "     *    and it may take a while to complete (e.g. require user\n"
				+ "     *    confirmation or downloading something from the network).\n"
				+ "     *    In such case application EDT will be blocked and it will\n"
				+ "     *    be completely irresponsive.\n" + "     * \n"
				+ "     */";
		assertEquals(commentString, comment.getTokenAtStart(commentString));
	}

}
