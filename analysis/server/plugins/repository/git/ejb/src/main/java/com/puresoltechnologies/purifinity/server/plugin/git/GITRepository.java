package com.puresoltechnologies.purifinity.server.plugin.git;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

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
    public static final String JNDI_ADDRESS = JndiUtils.createGlobalName("repository.git.plugin",
	    "com-puresoltechnologies-purifinity-plugins-repository.git.ejb", Repository.class, GITRepository.class);
    public static final Map<String, Parameter<?>> PARAMETERS = new HashMap<>();

    static {
	PARAMETERS.put("Host", new ParameterWithArbitraryUnit<>("host", "", LevelOfMeasurement.NOMINAL,
		"The host where the repository is to be retrieved from.", String.class));
	PARAMETERS.put("Port", new ParameterWithArbitraryUnit<>("port", "", LevelOfMeasurement.NOMINAL,
		"The port of the host where the repository is to be retrieved from.", Integer.class));
	PARAMETERS.put("User", new ParameterWithArbitraryUnit<>("user", "", LevelOfMeasurement.NOMINAL,
		"The user to be used for login into the host.", String.class));
	PARAMETERS.put("Password", new ParameterWithArbitraryUnit<>("password", "", LevelOfMeasurement.NOMINAL,
		"The password of the user to be used for login into the host.", String.class));
    }

    private static final String GIT_BINARY_PATH_PROPERTY = "repository.git.binary";

    public static final ConfigurationParameter<?>[] CONFIG_PARAMETERS = new ConfigurationParameter<?>[] {
	    new ConfigurationParameter<String>("GIT Binary Location", "", LevelOfMeasurement.NOMINAL,
		    "Specifies the location of the git executable.", String.class, GIT_BINARY_PATH_PROPERTY, "/", "") };

    public static final RepositoryServiceInformation INFORMATION = new RepositoryServiceInformation(ID, NAME, "1.9",
	    PLUGIN_VERSION, JNDI_ADDRESS, "GIT Repository.", PARAMETERS, CONFIG_PARAMETERS, null, null, null);

    @Inject
    private Logger logger;

    private File gitBinaryPath = null;

    public GITRepository() {
	super(NAME);
    }

    public void setGitBinaryPath(File gitBinaryPath) {
	if (!gitBinaryPath.exists()) {
	    logger.warn("Path '" + gitBinaryPath + "' for GIT binary does not exist.");
	} else if (!gitBinaryPath.isFile()) {
	    logger.warn("Path '" + gitBinaryPath + "' for GIT binary is not a file.");
	} else if (!gitBinaryPath.canExecute()) {
	    logger.warn("Path '" + gitBinaryPath + "' for GIT binary is not executable.");
	} else {
	    this.gitBinaryPath = gitBinaryPath;
	}
    }

    public File getGitBinaryPath() {
	return gitBinaryPath;
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
    public ConfigurationParameter<?>[] getConfigurationParameters() {
	return CONFIG_PARAMETERS;
    }

    @Override
    public void setConfigurationParameter(ConfigurationParameter<?> parameter, Object value) {
	if (GIT_BINARY_PATH_PROPERTY.equals(parameter.getPropertyKey())) {
	    setGitBinaryPath(new File((String) value));
	} else {
	    throw new IllegalArgumentException("Parameter '" + parameter + "' is unknown.");
	}
    }

    @Override
    public Object getConfigurationParameter(ConfigurationParameter<?> parameter) {
	if (GIT_BINARY_PATH_PROPERTY.equals(parameter.getPropertyKey())) {
	    return getGitBinaryPath().toString();
	} else {
	    throw new IllegalArgumentException("Parameter '" + parameter + "' is unknown.");
	}
    }

}
