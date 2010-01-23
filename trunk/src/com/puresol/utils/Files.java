package com.puresol.utils;

import java.io.File;

public class Files {

	public static File addPaths(File path1, File path2) {
		return new File(path1.getPath() + "/" + path2.getPath());
	}
}
