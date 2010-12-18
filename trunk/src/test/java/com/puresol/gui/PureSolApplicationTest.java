package com.puresol.gui;

import org.junit.Test;

import com.puresol.testing.Tester;

import junit.framework.Assert;
import junit.framework.TestCase;

public class PureSolApplicationTest extends TestCase {

	@Test
	public void testStandards() {
		Assert.assertTrue(Tester.testStandards(PureSolApplication.class));
	}

}
