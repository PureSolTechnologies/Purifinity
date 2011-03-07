package com.puresol.config.sources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class DirectoryFile extends AbstractConfigurationSource {

	private final File file;
	private final boolean changeable;
	private final boolean overridable;
	private final Properties properties = new Properties();

	public DirectoryFile(String name, File directory, File relativeFile,
			boolean changeable, boolean overridable) throws IOException {
		super(name);
		this.changeable = changeable;
		this.overridable = overridable;
		file = new File(directory, relativeFile.getPath());
		if (file.exists()) {
			FileReader reader = new FileReader(file);
			try {
				properties.load(reader);
			} finally {
				reader.close();
			}
		}
	}

	@Override
	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	@Override
	public boolean isChangeable() {
		return changeable;
	}

	@Override
	public boolean isOverridable() {
		return overridable;
	}

	@Override
	public void setProperty(String key, String value) {
		if (changeable) {
			properties.setProperty(key, value);
		} else {
			throw new IllegalStateException(
					"This source is not allowed to be changed!");
		}
	}

	@Override
	public void save() throws IOException {
		if (changeable) {
			OutputStream outputStream = new FileOutputStream(file);
			try {
				properties.store(outputStream, getName());
			} finally {
				outputStream.close();
			}
		}
	}

}
