package com.puresol.utils;

import org.junit.Test;

import junit.framework.TestCase;

public class ConsoleUtilsTest extends TestCase {

	@Test
	public void testCreatePercentageBar() {
		assertEquals("[..........]", ConsoleUtils.createPercentageBar(12, 0.0,
				false));
		assertEquals("[*****.....]", ConsoleUtils.createPercentageBar(12, 0.5,
				false));
		assertEquals("[**********]", ConsoleUtils.createPercentageBar(12, 1.0,
				false));

		assertEquals("[..........] (  0%)", ConsoleUtils.createPercentageBar(
				12, 0.0, true));
		assertEquals("[*****.....] ( 50%)", ConsoleUtils.createPercentageBar(
				12, 0.5, true));
		assertEquals("[**********] (100%)", ConsoleUtils.createPercentageBar(
				12, 1.0, true));
	}

}
