package com.puresol.html;

import org.junit.Test;

import com.puresol.testing.Tester;

import junit.framework.Assert;
import junit.framework.TestCase;

public class LinkTargetsTest extends TestCase {
	@Test
	public void testIdentifiable() {
		Assert.assertTrue(Tester.testIdentifiable(LinkTargets.class));
	}

	@Test
	public void testStandards() {
		Assert.assertTrue(Tester.testStandards(LinkTargets.class));
	}

	@Test
	public void testKeyword() {
		Assert.assertEquals("", LinkTargets.DEFAULT.getKeyword());
		Assert.assertEquals("_blank", LinkTargets.BLANK.getKeyword());
		Assert.assertEquals("_self", LinkTargets.SELF.getKeyword());
		Assert.assertEquals("_top", LinkTargets.TOP.getKeyword());
	}

}
