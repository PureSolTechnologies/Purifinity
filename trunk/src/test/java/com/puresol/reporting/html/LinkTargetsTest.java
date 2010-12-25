package com.puresol.reporting.html;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.reporting.html.LinkTarget;
import com.puresol.testing.Tester;

public class LinkTargetsTest {
	@Test
	public void testIdentifiable() {
		assertTrue(Tester.testIdentifiable(LinkTarget.class));
	}

	@Test
	public void testStandards() {
		assertTrue(Tester.testStandards(LinkTarget.class));
	}

	@Test
	public void testKeyword() {
		assertEquals("", LinkTarget.DEFAULT.getKeyword());
		assertEquals("_blank", LinkTarget.BLANK.getKeyword());
		assertEquals("_self", LinkTarget.SELF.getKeyword());
		assertEquals("_top", LinkTarget.TOP.getKeyword());
	}

}
