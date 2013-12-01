package com.puresoltechnologies.purifinity.coding.analysis.test;

import java.util.ArrayList;
import java.util.List;

import com.puresoltechnologies.commons.utils.FileSearchConfiguration;

public class TestFileSearchConfiguration extends FileSearchConfiguration {

	private static final long serialVersionUID = 54040518365270602L;

	public TestFileSearchConfiguration() {
		super();
		List<String> dirExcludes = new ArrayList<String>();
		dirExcludes.add(".*");
		dirExcludes.add("bin");
		dirExcludes.add("target");
		setLocationExcludes(dirExcludes);
		List<String> fileExcludes = new ArrayList<String>();
		fileExcludes.add(".*");
		fileExcludes.add("*~");
		fileExcludes.add("*.bak");
		setFileExcludes(fileExcludes);
	}

}
