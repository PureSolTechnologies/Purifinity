package com.puresoltechnologies.purifinity.server.core.impl.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.HashMap;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.ParameterWithArbitraryUnit;
import com.puresoltechnologies.commons.misc.JSONSerializer;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryType;

public class RepositoryTypeTest {

	@Test
	public void testJSONSerializationGeneral() throws Exception {
		HashMap<String, Parameter<?>> parameters = new HashMap<>();
		parameters.put("parameter1", new ParameterWithArbitraryUnit<Double>(
				"parameterName", "parameterUnit", LevelOfMeasurement.NOMINAL,
				"parameterDescription", Double.class));
		RepositoryType repositoryType = new RepositoryType("className", "name",
				"description", parameters);
		checkSerialization(repositoryType);
	}

	@Test
	public void testJSONSerializationDirectoryRepositoryType() throws Exception {
		RepositoryType repositoryType = DirectoryRepositoryTypeCreator.create();
		checkSerialization(repositoryType);
	}

	@Test
	public void testJSONSerializationGITRepositoryType() throws Exception {
		RepositoryType repositoryType = GITRepositoryTypeCreator.create();
		checkSerialization(repositoryType);
	}

	private void checkSerialization(RepositoryType repositoryType)
			throws JsonGenerationException, JsonMappingException, IOException {
		String serialized = JSONSerializer.toJSONString(repositoryType);
		assertNotNull(serialized);
		assertFalse(serialized.isEmpty());
		System.out.println(serialized);
		RepositoryType deserialized = JSONSerializer.fromJSONString(serialized,
				RepositoryType.class);
		assertNotNull(deserialized);
		assertEquals(repositoryType.getClassName(), deserialized.getClassName());
		assertEquals(repositoryType.getName(), deserialized.getName());
		assertEquals(repositoryType.getDescription(),
				deserialized.getDescription());
		assertEquals(repositoryType.getParameters(),
				deserialized.getParameters());
	}
}
