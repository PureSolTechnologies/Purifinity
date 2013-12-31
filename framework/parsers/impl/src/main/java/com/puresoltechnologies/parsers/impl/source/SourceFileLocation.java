package com.puresoltechnologies.parsers.impl.source;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.puresoltechnologies.parsers.api.source.SourceCode;
import com.puresoltechnologies.parsers.api.source.SourceCodeLocation;

public class SourceFileLocation extends AbstractSourceCodeLocation {

	private static final long serialVersionUID = -4803348905641081874L;

	private static final String SOURCE_CODE_LOCATION_REPOSITORY_DIRECTORY = "source.code.location.repository.directory";
	private static final String SOURCE_CODE_LOCATION_INTERNAL_PATH = "source.code.location.internal.path";

	private final File repositoryDirectory;
	private final String internalPath;
	private final File file;

	public SourceFileLocation(String repositoryDirectory, String internalPath) {
		this.repositoryDirectory = new File(repositoryDirectory);
		this.internalPath = internalPath;
		file = new File(repositoryDirectory, internalPath);
	}

	public SourceFileLocation(File repositoryDirectory, String internalPath) {
		this.repositoryDirectory = repositoryDirectory;
		this.internalPath = internalPath;
		file = new File(repositoryDirectory, internalPath);
	}

	public SourceFileLocation(File repositoryDirectory, File internalPath) {
		this.repositoryDirectory = repositoryDirectory;
		this.internalPath = internalPath.getPath();
		file = new File(repositoryDirectory, this.internalPath);
	}

	public SourceFileLocation(Properties properties) {
		this.repositoryDirectory = new File(
				properties
						.getProperty(SOURCE_CODE_LOCATION_REPOSITORY_DIRECTORY));
		this.internalPath = properties
				.getProperty(SOURCE_CODE_LOCATION_INTERNAL_PATH);
		file = new File(repositoryDirectory, this.internalPath);
	}

	@Override
	public InputStream openStream() throws IOException {
		return new FileInputStream(file);
	}

	@Override
	public SourceCode loadSourceCode() throws IOException {
		FileInputStream stream = new FileInputStream(file);
		try {
			return SourceCodeImpl.read(stream, this);
		} finally {
			stream.close();
		}
	}

	@Override
	public String getHumanReadableLocationString() {
		return internalPath;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((internalPath == null) ? 0 : internalPath.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SourceFileLocation other = (SourceFileLocation) obj;
		if (internalPath == null) {
			if (other.internalPath != null)
				return false;
		} else if (!internalPath.equals(other.internalPath))
			return false;
		return true;
	}

	@Override
	public SourceCodeLocation newRelativeSource(String relativePath) {
		File internalFile = new File(internalPath);
		File internalParentFile = internalFile.getParentFile();
		File newInternalPath;
		if (internalParentFile == null) {
			newInternalPath = new File(relativePath);
		} else {
			newInternalPath = new File(internalParentFile.getPath(),
					relativePath);
		}
		return new SourceFileLocation(repositoryDirectory,
				newInternalPath.getPath());
	}

	@Override
	public String getName() {
		return file.getName();
	}

	@Override
	public String getInternalLocation() {
		return internalPath;
	}

	@Override
	public boolean isAvailable() {
		return file.exists();
	}

	@Override
	public Properties getSerialization() {
		Properties properties = new Properties();
		properties
				.setProperty(SOURCE_CODE_LOCATION_CLASS, getClass().getName());
		properties.setProperty(SOURCE_CODE_LOCATION_TYPE, "unspecified");
		properties.setProperty(SOURCE_CODE_LOCATION_NAME, getName());
		properties.setProperty(SOURCE_CODE_LOCATION_REPOSITORY_DIRECTORY,
				repositoryDirectory.getPath());
		properties
				.setProperty(SOURCE_CODE_LOCATION_INTERNAL_PATH, internalPath);
		return properties;
	}
}
