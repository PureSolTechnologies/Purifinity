package com.puresoltechnologies.purifinity.server.core.impl.analysis.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.puresoltechnologies.commons.os.FileSearchConfiguration;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.parsers.source.SourceFileLocation;
import com.puresoltechnologies.purifinity.server.common.utils.io.FileSearch;
import com.puresoltechnologies.purifinity.server.common.utils.io.FileTree;

/**
 * 
 * 
 * @author Rick-Rainer Ludwig
 */
public class DirectoryRepositoryLocation extends AbstractRepositoryLocation {

	private static final long serialVersionUID = -5405680480509263585L;

	public static final String REPOSITORY_LOCATION_DIRECTORY = "repository.location.directory";

	private final File repositoryDirectory;

	public DirectoryRepositoryLocation(String name, File repositoryDirectory) {
		super(name);
		this.repositoryDirectory = repositoryDirectory;
	}

	public DirectoryRepositoryLocation(Properties properties) {
		super(properties.getProperty(REPOSITORY_LOCATION_NAME));
		Object repositoryLocationClass = properties
				.get(REPOSITORY_LOCATION_CLASS);
		if (!getClass().getName().equals(repositoryLocationClass)) {
			throw new IllegalArgumentException(
					"Repository location with class '"
							+ repositoryLocationClass
							+ "' is not suitable for '" + getClass().getName()
							+ "'. (" + properties.toString() + ")");
		}
		String repositoryDirectoryString = properties
				.getProperty(REPOSITORY_LOCATION_DIRECTORY);
		if (repositoryDirectoryString == null) {
			throw new IllegalArgumentException(
					"Repository location with class '"
							+ repositoryLocationClass
							+ "' does not contain a repository directory. ("
							+ properties.toString() + ")");
		}
		this.repositoryDirectory = new File(repositoryDirectoryString);
	}

	@Override
	public String getHumanReadableLocationString() {
		return "Directory: " + repositoryDirectory.getPath();
	}

	@Override
	public List<SourceCodeLocation> getSourceCodes(
			FileSearchConfiguration fileSearchConfiguration) {
		FileTree fileTree = FileSearch.getFileTree(repositoryDirectory,
				fileSearchConfiguration);
		List<SourceCodeLocation> locations = new ArrayList<SourceCodeLocation>();
		for (FileTree fileNode : fileTree) {
			File file = fileNode.getPathFile(false);
			if (new File(repositoryDirectory, file.getPath()).isFile()) {
				SourceFileLocation location = new SourceFileLocation(
						repositoryDirectory, file);
				locations.add(location);
			}
		}
		return locations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime
				* result
				+ ((repositoryDirectory == null) ? 0 : repositoryDirectory
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		DirectoryRepositoryLocation other = (DirectoryRepositoryLocation) obj;
		if (repositoryDirectory == null) {
			if (other.repositoryDirectory != null)
				return false;
		} else if (!repositoryDirectory.equals(other.repositoryDirectory))
			return false;
		return true;
	}

	@Override
	public Properties getSerialization() {
		Properties properties = new Properties();
		properties.setProperty(REPOSITORY_LOCATION_CLASS, getClass().getName());
		properties.setProperty(REPOSITORY_LOCATION_NAME, getName());
		properties.setProperty(REPOSITORY_LOCATION_DIRECTORY,
				repositoryDirectory.getPath());
		return properties;
	}

	@Override
	public String toString() {
		return "Directory repository: " + repositoryDirectory;
	}
}
