package com.puresoltechnologies.purifinity.server.plugin.git;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.puresoltechnologies.commons.math.ConfigurationParameter;
import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.ParameterWithArbitraryUnit;
import com.puresoltechnologies.commons.misc.io.FileSearchConfiguration;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.repository.spi.AbstractRepository;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryServiceInformation;

/**
 * 
 * @author Rick-Rainer Ludwig
 */
public class GITRepository extends AbstractRepository {

	private static final long serialVersionUID = -5405680480509263585L;

	public static final String ID = GITRepository.class.getName();
	public static final String NAME = "GIT Repository";
	public static final Map<String, Parameter<?>> PARAMETERS = new HashMap<>();
	static {
		PARAMETERS.put("Host", new ParameterWithArbitraryUnit<>("host", "",
				LevelOfMeasurement.NOMINAL,
				"The host where the repository is to be retrieved from.",
				String.class));
		PARAMETERS
				.put("Port",
						new ParameterWithArbitraryUnit<>(
								"port",
								"",
								LevelOfMeasurement.NOMINAL,
								"The port of the host where the repository is to be retrieved from.",
								Integer.class));
		PARAMETERS.put("User", new ParameterWithArbitraryUnit<>("user", "",
				LevelOfMeasurement.NOMINAL,
				"The user to be used for login into the host.", String.class));
		PARAMETERS.put("Password", new ParameterWithArbitraryUnit<>("password",
				"", LevelOfMeasurement.NOMINAL,
				"The password of the user to be used for login into the host.",
				String.class));
	}
	public static final Set<ConfigurationParameter<?>> CONFIG_PARAMETERS = new LinkedHashSet<>();
	public static final RepositoryServiceInformation INFORMATION = new RepositoryServiceInformation(
			ID, NAME, "GIT Repository.", PARAMETERS, CONFIG_PARAMETERS, null,
			null, null, null);

	public GITRepository(Properties properties) {
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
	}

	@Override
	public String getHumanReadableLocationString() {
		return "GIT";
	}

	@Override
	public List<SourceCodeLocation> getSourceCodes(
			FileSearchConfiguration fileSearchConfiguration) {
		List<SourceCodeLocation> locations = new ArrayList<SourceCodeLocation>();
		return locations;
	}

	@Override
	public Properties getSerialization() {
		Properties properties = new Properties();
		properties.setProperty(REPOSITORY_LOCATION_CLASS, getClass().getName());
		properties.setProperty(REPOSITORY_LOCATION_NAME, getName());
		return properties;
	}

	@Override
	public String toString() {
		return "GIT repository";
	}
}
