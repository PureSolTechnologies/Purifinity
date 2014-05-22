package com.puresoltechnologies.purifinity.server.domain.repositories;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;

import org.junit.Test;

import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.ParameterWithArbitraryUnit;
import com.puresoltechnologies.commons.misc.JSONSerializer;

public class RepositoryTypeTest {

	@Test
	public void testJSONSerialization() throws Exception {
		HashMap<String, Parameter<?>> parameters = new HashMap<>();
		parameters.put("parameter1", new ParameterWithArbitraryUnit<Double>(
				"parameterName", "parameterUnit", LevelOfMeasurement.NOMINAL,
				"parameterDescription", Double.class));
		RepositoryType repositoryType = new RepositoryType("name",
				"description", parameters);
		String serialized = JSONSerializer.toJSONString(repositoryType);
		assertNotNull(serialized);
		assertFalse(serialized.isEmpty());
		System.out.println(serialized);
		RepositoryType deserialized = JSONSerializer.fromJSONString(serialized,
				RepositoryType.class);
		assertNotNull(deserialized);
	}
}
