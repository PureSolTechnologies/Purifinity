package com.puresol.purifinity.coding.analysis.api;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.puresol.purifinity.trees.FileTree;
import com.puresol.purifinity.uhura.source.CodeLocation;
import com.puresol.purifinity.uhura.source.SourceFileLocation;
import com.puresol.purifinity.utils.FileSearch;

/**
 * 
 * 
 * @author Rick-Rainer Ludwig
 */
public class DirectoryRepositoryLocation extends AbstractRepositoryLocation {

	private static final long serialVersionUID = -5405680480509263585L;

	private final File repositoryDirectory;

	public DirectoryRepositoryLocation(String name, File repositoryDirectory) {
		super(name);
		this.repositoryDirectory = repositoryDirectory;
	}

	@Override
	public String getHumanReadableLocationString() {
		return "Directory: " + repositoryDirectory.getPath();
	}

	@Override
	public List<CodeLocation> getSourceCodes() {
		FileTree fileTree = FileSearch.getFileTree(repositoryDirectory,
				getCodeSearchConfiguration());
		List<CodeLocation> locations = new ArrayList<CodeLocation>();
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

}