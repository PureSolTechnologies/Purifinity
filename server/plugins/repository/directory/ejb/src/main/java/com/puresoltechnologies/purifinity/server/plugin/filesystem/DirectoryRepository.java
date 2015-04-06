package com.puresoltechnologies.purifinity.server.plugin.filesystem;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.puresoltechnologies.commons.math.ConfigurationParameter;
import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.ParameterWithArbitraryUnit;
import com.puresoltechnologies.commons.misc.io.FileSearchConfiguration;
import com.puresoltechnologies.parsers.source.RepositoryLocation;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.parsers.source.SourceFileLocation;
import com.puresoltechnologies.purifinity.repository.spi.AbstractRepository;
import com.puresoltechnologies.purifinity.repository.spi.Repository;
import com.puresoltechnologies.purifinity.server.common.utils.BuildInformation;
import com.puresoltechnologies.purifinity.server.common.utils.io.FileSearch;
import com.puresoltechnologies.purifinity.server.common.utils.io.FileTree;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryServiceInformation;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;
import com.puresoltechnologies.versioning.Version;

/**
 * 
 * 
 * @author Rick-Rainer Ludwig
 */
@Stateless
@Remote(RepositoryLocation.class)
public class DirectoryRepository extends AbstractRepository {

	private static final long serialVersionUID = -5405680480509263585L;

	public static final String REPOSITORY_LOCATION_DIRECTORY = "repository.location.directory";
	public static final String ID = DirectoryRepository.class.getName();
	public static final String NAME = "Directory";
	public static final Version PLUGIN_VERSION = BuildInformation.getVersion();
	public static final String JNDI_ADDRESS = JndiUtils.createGlobalName(
			"repository.directory.plugin", "repository.directory.ejb",
			Repository.class, DirectoryRepository.class);
	public static final Map<String, Parameter<?>> PARAMETERS = new HashMap<>();
	static {
		PARAMETERS
				.put("Directory", new ParameterWithArbitraryUnit<>(
						DirectoryRepository.REPOSITORY_LOCATION_DIRECTORY, "",
						LevelOfMeasurement.NOMINAL,
						"The directory the source code can be found in.",
						String.class));
	}
	public static final Set<ConfigurationParameter<?>> CONFIG_PARAMETERS = new LinkedHashSet<>();
	public static final RepositoryServiceInformation INFORMATION = new RepositoryServiceInformation(
			ID, NAME, PLUGIN_VERSION.toString(), PLUGIN_VERSION, JNDI_ADDRESS,
			"Simple directory in the file system of the server.", PARAMETERS,
			CONFIG_PARAMETERS, "/repository.directory.ui/index.jsf",
			"/repository.directory.ui/config.jsf",
			"/repository.directory.ui/project.jsf",
			"/repository.directory.ui/run.jsf");

	private final File repositoryDirectory;

	public DirectoryRepository() {
		super("Directory");
		this.repositoryDirectory = null;
	}

	public DirectoryRepository(File repositoryDirectory) {
		super("Directory");
		this.repositoryDirectory = repositoryDirectory;
	}

	public DirectoryRepository(Properties properties) {
		super(properties
				.getProperty(RepositoryLocation.REPOSITORY_LOCATION_NAME));
		Object repositoryLocationClass = properties
				.get(RepositoryLocation.REPOSITORY_LOCATION_CLASS);
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
		DirectoryRepository other = (DirectoryRepository) obj;
		if (repositoryDirectory == null) {
			if (other.repositoryDirectory != null)
				return false;
		} else if (!repositoryDirectory.equals(other.repositoryDirectory))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Directory repository: " + repositoryDirectory;
	}
}
