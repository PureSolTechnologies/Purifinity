package com.puresoltechnologies.purifinity.framework.analysis.test;

import java.util.ArrayList;
import java.util.List;

import com.puresoltechnologies.commons.misc.FileSearchConfiguration;

public class TestFileSearchConfiguration extends FileSearchConfiguration {

	private static final long serialVersionUID = 54040518365270602L;

	private static final List<String> dirExcludes = new ArrayList<String>();
	static {
		dirExcludes.add(".*");
		dirExcludes.add("bin");
		dirExcludes.add("target");
	}

	private static final List<String> fileExcludes = new ArrayList<String>();
	static {
		fileExcludes.add(".*");
		fileExcludes.add("*~");
		fileExcludes.add("*.bak");
	}

	public TestFileSearchConfiguration() {
		super(new ArrayList<String>(), dirExcludes, new ArrayList<String>(),
				fileExcludes, true);
	}

}
