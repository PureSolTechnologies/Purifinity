package com.puresol.packages;

public enum PackageDirectory {

	SRC("src"), TEST("test"), RES("res");

	private final String directoryName;

	private PackageDirectory(String directoryName) {
		this.directoryName = directoryName;
	}

	public String getDirectoryName() {
		return directoryName;
	}
}
