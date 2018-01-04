package com.puresoltechnologies.purifinity.server.common.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.junit.Test;

import com.puresoltechnologies.versioning.Version;

public class BuildInformationTest {

    @Test
    public void testInceptionYear() {
	String year = BuildInformation.getInceptionYear();
	assertNotNull(year);
	assertEquals("2009", year);
    }

    @Test
    public void testBuildYear() {
	LocalDateTime timestamp = BuildInformation.getBuildTimestamp();
	assertNotNull(timestamp);
	String currentYear = new SimpleDateFormat("yyyy").format(new Date());
	assertEquals(Integer.parseInt(currentYear), timestamp.getYear());
    }

    @Test
    public void testVersion() {
	Version version = BuildInformation.getVersion();
	assertNotNull(version);
    }

}
