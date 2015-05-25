package com.puresoltechnologies.purifinity.server.core.impl.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.puresoltechnologies.commons.domain.JSONSerializer;
import com.puresoltechnologies.commons.domain.LevelOfMeasurement;
import com.puresoltechnologies.commons.domain.Parameter;
import com.puresoltechnologies.commons.domain.ParameterWithArbitraryUnit;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryServiceInformation;
import com.puresoltechnologies.versioning.Version;

public class RepositoryTest {

	@Test
	public void testJSONSerializationGeneral() throws Exception {
		HashMap<String, Parameter<?>> parameters = new HashMap<>();
		parameters.put("parameter1", new ParameterWithArbitraryUnit<Double>(
				"parameterName", "parameterUnit", LevelOfMeasurement.NOMINAL,
				"parameterDescription", Double.class));
		RepositoryServiceInformation repositoryType = new RepositoryServiceInformation(
				"className", "name", "version", new Version(1, 2, 3),
				"jndi://", "description", parameters, new ArrayList<>(), null,
				null, null, null);
		checkSerialization(repositoryType);
	}

	private void checkSerialization(RepositoryServiceInformation repositoryType)
			throws JsonGenerationException, JsonMappingException, IOException {
		String serialized = JSONSerializer.toJSONString(repositoryType);
		assertNotNull(serialized);
		assertFalse(serialized.isEmpty());
		System.out.println(serialized);
		RepositoryServiceInformation deserialized = JSONSerializer
				.fromJSONString(serialized, RepositoryServiceInformation.class);
		assertNotNull(deserialized);
		assertEquals(repositoryType.getId(), deserialized.getId());
		assertEquals(repositoryType.getName(), deserialized.getName());
		assertEquals(repositoryType.getDescription(),
				deserialized.getDescription());
		assertEquals(repositoryType.getParameters(),
				deserialized.getParameters());
	}
}
