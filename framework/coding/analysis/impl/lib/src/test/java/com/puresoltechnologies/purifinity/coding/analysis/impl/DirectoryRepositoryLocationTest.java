package com.puresoltechnologies.purifinity.coding.analysis.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

<<<<<<< HEAD:framework/coding/analysis/impl/lib/src/test/java/com/puresoltechnologies/purifinity/coding/analysis/impl/DirectoryRepositoryLocationTest.java
import com.puresoltechnologies.commons.FileSearchConfiguration;
import com.puresoltechnologies.parsers.api.source.CodeLocation;
import com.puresoltechnologies.purifinity.coding.analysis.impl.DirectoryRepositoryLocation;
=======
import com.puresoltechnologies.commons.utils.FileSearchConfiguration;
import com.puresoltechnologies.parsers.api.source.CodeLocation;
import com.puresoltechnologies.purifinity.coding.analysis.api.DirectoryRepositoryLocation;
>>>>>>> 22bb20bf218d5d810e936dd668128ce7c35efbf9:framework/coding/analysis/api/lib/src/test/java/com/puresoltechnologies/purifinity/coding/analysis/api/DirectoryRepositoryLocationTest.java

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
		location.setCodeSearchConfiguration(searchConfiguration);
		List<CodeLocation> sourceCodes = location.getSourceCodes();
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
		location.setCodeSearchConfiguration(searchConfiguration);
		List<CodeLocation> sourceCodes = location.getSourceCodes();
		assertNotNull(sourceCodes);
		assert (sourceCodes.size() > 0);
	}
}
