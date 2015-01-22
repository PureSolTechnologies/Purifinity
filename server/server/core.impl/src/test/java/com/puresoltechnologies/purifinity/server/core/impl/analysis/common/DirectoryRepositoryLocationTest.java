package com.puresoltechnologies.purifinity.server.core.impl.analysis.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Test;

import com.puresoltechnologies.commons.misc.io.FileSearchConfiguration;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;

public class DirectoryRepositoryLocationTest {

	/**
	 * This test checks whether a file exclude of '*' will lead into an empty
	 * (not null!) list.
	 */
	@Test
	public void testEmptyCodeList() {
		DirectoryRepositoryLocation location = new DirectoryRepositoryLocation(
				"TestRepository", new File("."));
		List<String> dirIncludes = new ArrayList<String>();
		List<String> dirExcludes = new ArrayList<String>();
		List<String> fileIncludes = new ArrayList<String>();
		List<String> fileExcludes = new ArrayList<String>();
		fileExcludes.add("*");
		FileSearchConfiguration searchConfiguration = new FileSearchConfiguration(
				dirIncludes, dirExcludes, fileIncludes, fileExcludes, true);
		List<SourceCodeLocation> sourceCodes = location
				.getSourceCodes(searchConfiguration);
		assertNotNull(sourceCodes);
		assertEquals(0, sourceCodes.size());
	}

	@Test
	public void test() {
		DirectoryRepositoryLocation location = new DirectoryRepositoryLocation(
				"TestRepository", new File("."));
		List<String> dirIncludes = new ArrayList<String>();
		List<String> dirExcludes = new ArrayList<String>();
		List<String> fileIncludes = new ArrayList<String>();
		List<String> fileExcludes = new ArrayList<String>();
		FileSearchConfiguration searchConfiguration = new FileSearchConfiguration(
				dirIncludes, dirExcludes, fileIncludes, fileExcludes, true);
		List<SourceCodeLocation> sourceCodes = location
				.getSourceCodes(searchConfiguration);
		assertNotNull(sourceCodes);
		assertTrue(sourceCodes.size() > 0);
	}

	@Test
	public void testSerialization() {
		DirectoryRepositoryLocation location = new DirectoryRepositoryLocation(
				"TestRepository", new File("/home/ludwig"));
		Properties serialization = location.getSerialization();
		DirectoryRepositoryLocation clonedLocation = new DirectoryRepositoryLocation(
				serialization);
		assertNotSame(location, clonedLocation);
		assertEquals(location, clonedLocation);
	}
}
