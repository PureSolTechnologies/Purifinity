package com.puresoltechnologies.purifinity.server.core.impl.repositories;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.ParameterWithArbitraryUnit;
import com.puresoltechnologies.purifinity.server.common.plugins.AbstractPluginService;
import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryTypePluginService;
import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryTypePluginServiceRemote;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryType;

@Singleton
public class RepositoryTypePluginServiceImpl extends
		AbstractPluginService<RepositoryType> implements
		RepositoryTypePluginService, RepositoryTypePluginServiceRemote {

	public RepositoryTypePluginServiceImpl() {
		super("Repository Type Plugin Service");
	}

	@PostConstruct
	public void addDefaultRepositoryTypes() {
		registerDirectoryRepository();
		registerGitRepository();
	}

	private void registerDirectoryRepository() {
		Map<String, Parameter<?>> parameters = new LinkedHashMap<>();
		parameters
				.put("Directory", new ParameterWithArbitraryUnit<>("directory",
						"", LevelOfMeasurement.NOMINAL,
						"The directory the source code can be found in.",
						String.class));
		registerService("jndi:dir", new RepositoryType("Directory",
				"Simple directory in the file system of the server.",
				parameters), new Properties());
	}

	private void registerGitRepository() {
		Map<String, Parameter<?>> parameters = new LinkedHashMap<>();
		parameters.put("Host", new ParameterWithArbitraryUnit<>("host", "",
				LevelOfMeasurement.NOMINAL,
				"The host where the repository is to be retrieved from.",
				String.class));
		parameters
				.put("Port",
						new ParameterWithArbitraryUnit<>(
								"port",
								"",
								LevelOfMeasurement.NOMINAL,
								"The port of the host where the repository is to be retrieved from.",
								Integer.class));
		parameters.put("User", new ParameterWithArbitraryUnit<>("user", "",
				LevelOfMeasurement.NOMINAL,
				"The user to be used for login into the host.", String.class));
		parameters.put("Password", new ParameterWithArbitraryUnit<>("password",
				"", LevelOfMeasurement.NOMINAL,
				"The password of the user to be used for login into the host.",
				String.class));
		registerService("jndi:git", new RepositoryType("GIT",
				"Remote GIT repository.", parameters), new Properties());
	}
}
