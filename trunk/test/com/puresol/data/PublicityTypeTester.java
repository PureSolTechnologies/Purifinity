package com.puresol.data;

import org.junit.Test;

import com.puresol.testing.Tester;

import junit.framework.Assert;
import junit.framework.TestCase;

public class PublicityTypeTester extends TestCase {

	@Test
	public void testIdentifiable() {
		Assert.assertTrue(Tester.testIdentifiable(PublicityType.class));
	}

}
