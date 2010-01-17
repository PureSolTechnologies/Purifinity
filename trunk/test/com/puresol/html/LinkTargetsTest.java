package com.puresol.html;

import org.junit.Test;

import com.puresol.testing.Tester;

import junit.framework.Assert;
import junit.framework.TestCase;

public class LinkTargetsTest extends TestCase {
	@Test
	public void testIdentifiable() {
		Assert.assertTrue(Tester.testIdentifiable(LinkTarget.class));
	}

	@Test
	public void testStandards() {
		Assert.assertTrue(Tester.testStandards(LinkTarget.class));
	}

	@Test
	public void testKeyword() {
		Assert.assertEquals("", LinkTarget.DEFAULT.getKeyword());
		Assert.assertEquals("_blank", LinkTarget.BLANK.getKeyword());
		Assert.assertEquals("_self", LinkTarget.SELF.getKeyword());
		Assert.assertEquals("_top", LinkTarget.TOP.getKeyword());
	}

}
