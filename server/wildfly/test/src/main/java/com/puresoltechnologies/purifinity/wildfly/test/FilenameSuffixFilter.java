package com.puresoltechnologies.purifinity.wildfly.test;

import java.io.File;
import java.io.FilenameFilter;

public class FilenameSuffixFilter implements FilenameFilter {

	private final String suffix;

	public FilenameSuffixFilter(String suffix) {
		super();
		this.suffix = suffix;
	};

	@Override
	public boolean accept(File dir, String name) {
		return name.endsWith(suffix);
	}
}
