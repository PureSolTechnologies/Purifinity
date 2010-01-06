package com.puresol.data;

import org.junit.Test;

import com.puresol.testing.Tester;

import junit.framework.Assert;
import junit.framework.TestCase;

public class PersonNamePartTypeTest extends TestCase {

	@Test
	public void testIdentifiable() {
		Assert.assertTrue(Tester.testIdentifiable(PersonNamePartType.class));
	}

	@Test
	public void testStandards() {
		Assert.assertTrue(Tester.testStandards(PersonNamePartType.class));
	}
}
