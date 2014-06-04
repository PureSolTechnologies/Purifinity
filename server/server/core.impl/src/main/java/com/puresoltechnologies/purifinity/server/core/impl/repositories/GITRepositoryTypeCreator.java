package com.puresoltechnologies.purifinity.server.core.impl.repositories;

import java.util.LinkedHashMap;
import java.util.Map;

import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.ParameterWithArbitraryUnit;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.common.GITRepositoryLocation;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryType;

public class GITRepositoryTypeCreator {

	public static RepositoryType create() {
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
		return new RepositoryType(GITRepositoryLocation.class.getName(), "GIT",
				"Remote GIT repository.", parameters);
	}
}
