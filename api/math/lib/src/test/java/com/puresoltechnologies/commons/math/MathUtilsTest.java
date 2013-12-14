package com.puresoltechnologies.commons.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.commons.math.MathUtils;

public class MathUtilsTest {

	@Test
	public void testAllocate() {
		long[] values = MathUtils.allocate(100, 1.0, 2.0, 3.0);
		long sum = 0;
		for (long value : values) {
			System.out.println(value);
			sum += value;
		}
		assertEquals(100, sum);
		assertTrue(values[0] * 2 - values[1] <= 1);
		assertTrue(values[0] * 3 - values[2] <= 1);
	}

}
