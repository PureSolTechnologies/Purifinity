package com.puresol.config.sources;

import java.io.File;
import java.io.IOException;

public class HomeFile extends DirectoryFile {

	public HomeFile(String name, File relativeFile, boolean changeable,
			boolean overridable) throws IOException {
		super(name, new File(System.getProperty("user.home")), relativeFile,
				changeable, overridable);
	}
}
