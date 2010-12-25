package com.puresol.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConsoleUtilsTest {

	@Test
	public void testCreatePercentageBar() {
		assertEquals("[..........]",
				ConsoleUtils.createPercentageBar(12, 0.0, false));
		assertEquals("[*****.....]",
				ConsoleUtils.createPercentageBar(12, 0.5, false));
		assertEquals("[**********]",
				ConsoleUtils.createPercentageBar(12, 1.0, false));

		assertEquals("[..........] (  0%)",
				ConsoleUtils.createPercentageBar(12, 0.0, true));
		assertEquals("[*****.....] ( 50%)",
				ConsoleUtils.createPercentageBar(12, 0.5, true));
		assertEquals("[**********] (100%)",
				ConsoleUtils.createPercentageBar(12, 1.0, true));
	}

}
