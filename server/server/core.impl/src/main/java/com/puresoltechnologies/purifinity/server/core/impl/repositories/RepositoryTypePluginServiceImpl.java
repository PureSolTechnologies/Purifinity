package com.puresoltechnologies.purifinity.server.core.impl.repositories;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

import com.puresoltechnologies.commons.math.LevelOfMeasurement;
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
		Map<String, ParameterWithArbitraryUnit<?>> parameters = new LinkedHashMap<>();
		parameters
				.put("directory", new ParameterWithArbitraryUnit<>("directory",
						"", LevelOfMeasurement.NOMINAL,
						"The directory the source code can be found in.",
						String.class));
		registerService("", new RepositoryType("Directory",
				"Simple directory in the file system of the server.",
				parameters), new Properties());
	}
}
