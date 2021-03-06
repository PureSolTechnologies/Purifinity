package com.puresoltechnologies.purifinity.server.common.utils.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.puresoltechnologies.commons.misc.io.FileSearchConfiguration;

public class FileSearchConfigurationTest {

	@Test
	public void testClone() {
		List<String> includeFiles = new ArrayList<String>();
		List<String> excludeFiles = new ArrayList<String>();
		List<String> includeDirs = new ArrayList<String>();
		List<String> excludeDirs = new ArrayList<String>();
		FileSearchConfiguration conf = new FileSearchConfiguration(includeDirs,
				excludeDirs, includeFiles, excludeFiles, false);
		FileSearchConfiguration cloned = conf.clone();
		assertNotSame(conf, cloned);
		assertEquals(conf, cloned);
	}
}
