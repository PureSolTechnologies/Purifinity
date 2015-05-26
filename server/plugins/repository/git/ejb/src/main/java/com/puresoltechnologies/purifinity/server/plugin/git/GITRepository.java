package com.puresoltechnologies.purifinity.server.plugin.git;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.commons.domain.LevelOfMeasurement;
import com.puresoltechnologies.commons.domain.Parameter;
import com.puresoltechnologies.commons.domain.ParameterWithArbitraryUnit;
import com.puresoltechnologies.commons.misc.io.FileSearchConfiguration;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.repository.spi.AbstractRepository;
import com.puresoltechnologies.purifinity.repository.spi.Repository;
import com.puresoltechnologies.purifinity.server.common.utils.BuildInformation;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryServiceInformation;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;
import com.puresoltechnologies.versioning.Version;

/**
 * 
 * @author Rick-Rainer Ludwig
 */
@Stateless
@Remote(Repository.class)
public class GITRepository extends AbstractRepository {

	private static final long serialVersionUID = -5405680480509263585L;

	public static final String ID = GITRepository.class.getName();
	public static final String NAME = "GIT";
	public static final Version PLUGIN_VERSION = BuildInformation.getVersion();
	public static final String JNDI_ADDRESS = JndiUtils.createGlobalName(
			"repository.git.plugin", "repository.git.ejb", Repository.class,
			GITRepository.class);
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
	public static final List<ConfigurationParameter<?>> CONFIG_PARAMETERS = new ArrayList<>();
	static {
		CONFIG_PARAMETERS.add(new ConfigurationParameter<String>(
				"GIT Binary Location", "", LevelOfMeasurement.NOMINAL,
				"Specifies the location of the git executable.", String.class,
				"repository.git.binary", "/", ""));
	}
	public static final RepositoryServiceInformation INFORMATION = new RepositoryServiceInformation(
			ID, NAME, "1.9", PLUGIN_VERSION, JNDI_ADDRESS, "GIT Repository.",
			PARAMETERS, CONFIG_PARAMETERS, null, null, null, null);

	public GITRepository() {
		super("GIT Repository");
	}

	public String getHost(Properties properties) {
		return "";
	}

	@Override
	public List<SourceCodeLocation> getSourceCodes(Properties configuration,
			FileSearchConfiguration fileSearchConfiguration) {
		List<SourceCodeLocation> locations = new ArrayList<SourceCodeLocation>();
		return locations;
	}

	@Override
	public String getHumanReadableLocationString(Properties repositoryLocation) {
		return "GIT Repository '???'";
	}

	@Override
	public List<ConfigurationParameter<?>> getConfigurationParameters() {
		return CONFIG_PARAMETERS;
	}

	@Override
	public <T> void setConfigurationParameter(
			ConfigurationParameter<T> parameter, T value) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> T getConfigurationParameter(ConfigurationParameter<T> parameter) {
		// TODO Auto-generated method stub
		return null;
	}

}
