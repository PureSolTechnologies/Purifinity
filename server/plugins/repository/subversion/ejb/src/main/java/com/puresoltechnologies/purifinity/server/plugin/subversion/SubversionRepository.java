package com.puresoltechnologies.purifinity.server.plugin.subversion;

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
import com.puresoltechnologies.commons.misc.io.FileSearchConfiguration;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.repository.spi.AbstractRepository;
import com.puresoltechnologies.purifinity.repository.spi.Repository;
import com.puresoltechnologies.purifinity.server.common.utils.BuildInformation;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryServiceInformation;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;
import com.puresoltechnologies.versioning.Version;

@Stateless
@Remote(Repository.class)
public class SubversionRepository extends AbstractRepository {

	private static final long serialVersionUID = 1876428394155714495L;

	public static final String ID = SubversionRepository.class.getName();
	public static final String NAME = "Subversion";
	public static final Version PLUGIN_VERSION = BuildInformation.getVersion();
	public static final String JNDI_ADDRESS = JndiUtils.createGlobalName(
			"repository.subversion.plugin", "repository.subversion.ejb",
			Repository.class, SubversionRepository.class);
	public static final Map<String, Parameter<?>> PARAMETERS = new HashMap<>();
	public static final List<ConfigurationParameter<?>> CONFIG_PARAMETERS = new ArrayList<>();
	static {
		CONFIG_PARAMETERS.add(new ConfigurationParameter<String>(
				"SVN Binary Location", "", LevelOfMeasurement.NOMINAL,
				"Specifies the location of the svn executable.", String.class,
				"repository.subversion.binary", "/", ""));
	}
	public static final RepositoryServiceInformation INFORMATION = new RepositoryServiceInformation(
			ID, NAME, "1.8", PLUGIN_VERSION, JNDI_ADDRESS,
			"Subversion Repository.", PARAMETERS, CONFIG_PARAMETERS, null,
			null, null, null);

	public SubversionRepository() {
		super("Subversion Repository");
	}

	@Override
	public List<SourceCodeLocation> getSourceCodes(Properties configuration,
			FileSearchConfiguration fileSearchConfiguration) {
		return new ArrayList<>();
	}

	@Override
	public String getHumanReadableLocationString(Properties repositoryLocation) {
		return "Subversion Repository '???'";
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
