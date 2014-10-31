package com.puresoltechnologies.commons.misc;

import static org.junit.Assert.assertEquals;

import java.text.DecimalFormat;

import org.junit.Test;

public class FileUtilitiesTest {

    @Test
    public void testCreateHumanReadableSizeString() {
	assertEquals("1kB", FileUtilities.createHumanReadableSizeString(1024));
	assertEquals("2kB", FileUtilities.createHumanReadableSizeString(2048));
	assertEquals(new DecimalFormat("#.##").format(2.5) + "kB",
		FileUtilities.createHumanReadableSizeString(2560));
	assertEquals("1000kB",
		FileUtilities.createHumanReadableSizeString(1024 * 1000));
	assertEquals("1000MB",
		FileUtilities.createHumanReadableSizeString(1024 * 1024 * 1000));
    }

    @Test
    public void testCreateHumanReadableNegativeSizeString() {
	assertEquals("-1kB", FileUtilities.createHumanReadableSizeString(-1024));
	assertEquals("-2kB", FileUtilities.createHumanReadableSizeString(-2048));
	assertEquals(new DecimalFormat("#.##").format(-2.5) + "kB",
		FileUtilities.createHumanReadableSizeString(-2560));
	assertEquals("-1000kB",
		FileUtilities.createHumanReadableSizeString(-1024 * 1000));
	assertEquals("-1000MB",
		FileUtilities
			.createHumanReadableSizeString(-1024 * 1024 * 1000));
    }

    @Test
    public void testCreateHumanReadableSizeStringBoundaryCases() {
	assertEquals("-1B", FileUtilities.createHumanReadableSizeString(-1));
	assertEquals("0B", FileUtilities.createHumanReadableSizeString(0));
	assertEquals("1B", FileUtilities.createHumanReadableSizeString(1));
    }

}
