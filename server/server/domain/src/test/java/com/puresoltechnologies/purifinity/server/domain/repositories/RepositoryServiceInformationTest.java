package com.puresoltechnologies.purifinity.server.domain.repositories;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.commons.domain.JSONSerializer;
import com.puresoltechnologies.commons.domain.LevelOfMeasurement;
import com.puresoltechnologies.commons.domain.Parameter;
import com.puresoltechnologies.commons.domain.ParameterWithArbitraryUnit;
import com.puresoltechnologies.versioning.Version;

public class RepositoryServiceInformationTest {

	@Test
	public void testSerialization() throws JsonGenerationException,
			JsonMappingException, IOException {
		Map<String, Parameter<?>> parameters = new HashMap<String, Parameter<?>>();
		parameters.put("parameter1", new ParameterWithArbitraryUnit<>("name1",
				"unit1", LevelOfMeasurement.NOMINAL, "description1",
				String.class));
		parameters.put("parameter2", new ParameterWithArbitraryUnit<>("name2",
				"unit2", LevelOfMeasurement.ORDINAL, "description2",
				String.class));
		RepositoryServiceInformation information = new RepositoryServiceInformation(
				"nameOfClass", "nameOfType", "version", new Version(1, 2, 3),
				"jndi://", "descriptionOfType", parameters,
				new ArrayList<ConfigurationParameter<?>>(), "serviceURLPath",
				"projectURLPath", "runURLPath");
		String serialized = JSONSerializer.toJSONString(information);
		System.out.println(serialized);
		RepositoryServiceInformation deserialized = JSONSerializer
				.fromJSONString(serialized, RepositoryServiceInformation.class);
		assertEquals(information, deserialized);
	}
}
