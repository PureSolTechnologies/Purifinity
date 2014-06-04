package com.puresoltechnologies.purifinity.server.core.impl.repositories;

import java.util.LinkedHashMap;
import java.util.Map;

import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.ParameterWithArbitraryUnit;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.common.DirectoryRepositoryLocation;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryType;

public class DirectoryRepositoryTypeCreator {

	public static RepositoryType create() {
		Map<String, Parameter<?>> parameters = new LinkedHashMap<>();
		parameters
				.put("Directory",
						new ParameterWithArbitraryUnit<>(
								DirectoryRepositoryLocation.REPOSITORY_LOCATION_DIRECTORY,
								"",
								LevelOfMeasurement.NOMINAL,
								"The directory the source code can be found in.",
								String.class));
		return new RepositoryType(DirectoryRepositoryLocation.class.getName(),
				"Directory",
				"Simple directory in the file system of the server.",
				parameters);
	}
}
