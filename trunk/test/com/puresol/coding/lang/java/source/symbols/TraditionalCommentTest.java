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
		String commentString = "/* \n"
				+ "         * Event pump will be actually created in the setVisible(true) but\n"
				+ "         * it will also block execution until dialog is hidden.\n"
				+ "         * Given that dialog is hidden by worker thread when job js complete\n"
				+ "         * we can safely use setVisible() as sync mechanism here.\n"
				+ "         *\n"
				+ "         * Technically setVisible() implements blocking as a recursive call\n"
				+ "         * that starts new event pump. As a result we may have a situation when\n"
				+ "         * we have another call to delegate() from the same EDT while first call\n"
				+ "         * was in progress.\n"
				+ "         *\n"
				+ "         * In such case synchronized statements will not help because\n"
				+ "         * it will be the same thread.\n"
				+ "         * However, first setVisible() will not exit until additional event pump\n"
				+ "         * finishes processing of the current event (even if dialog was hidden!).\n"
				+ "         * Therefore setVisible() also prevents potential problems if we have\n"
				+ "         * such \"recursive\" calls to delegate().\n"
				+ "         *\n"
				+ "         * Note that we may have another non-EDT action in progress when first\n"
				+ "         * action from EDT comes. In such case we will create and show dialog.\n"
				+ "         * And then wait in the listener until we can add action to queue.\n"
				+ "         * This way we will have event pump started and will not interfere with\n"
				+ "         * action in progress.\n"
				+ "     */";
		assertEquals(commentString, comment.getTokenAtStart(commentString));
	}

}
