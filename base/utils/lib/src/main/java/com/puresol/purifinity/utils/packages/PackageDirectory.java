package com.puresol.purifinity.utils.packages;

public enum PackageDirectory {

	SRC("src/main/java"), TEST("src/test/java"), RES("src/main/resources");

	private final String directoryName;

	private PackageDirectory(String directoryName) {
		this.directoryName = directoryName;
	}

	public String getDirectoryName() {
		return directoryName;
	}
}
