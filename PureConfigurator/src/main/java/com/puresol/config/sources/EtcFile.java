package com.puresol.config.sources;

import java.io.File;
import java.io.IOException;

public class EtcFile extends DirectoryFile {

	public EtcFile(String name, File relativeFile, boolean changeable,
			boolean overridable) throws IOException {
		super(name, new File(System.getProperty("/etc")), relativeFile,
				changeable, overridable);
	}
}
