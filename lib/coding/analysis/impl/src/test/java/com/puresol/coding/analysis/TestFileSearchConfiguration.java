package com.puresol.coding.analysis;

import java.util.ArrayList;
import java.util.List;

import com.puresol.utils.CodeSearchConfiguration;

public class TestFileSearchConfiguration extends CodeSearchConfiguration {

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
