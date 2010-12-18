package com.puresol.exceptions;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import com.puresol.testing.Tester;

public class StrangeSituationExceptionTest extends TestCase {

	@Test
	public void testStandards() {
		Assert
				.assertTrue(Tester
						.testStandards(StrangeSituationException.class));
	}
}
