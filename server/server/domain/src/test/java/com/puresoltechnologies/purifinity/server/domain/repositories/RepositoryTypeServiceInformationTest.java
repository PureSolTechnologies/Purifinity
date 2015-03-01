package com.puresoltechnologies.purifinity.server.domain.repositories;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.puresoltechnologies.commons.math.ConfigurationParameter;
import com.puresoltechnologies.commons.math.JSONSerializer;
import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.ParameterWithArbitraryUnit;

public class RepositoryTypeServiceInformationTest {

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
	RepositoryTypeServiceInformation information = new RepositoryTypeServiceInformation(
		"nameOfClass", "nameOfType", "descriptionOfType", parameters,
		new HashSet<ConfigurationParameter<?>>(), "serviceURLPath",
		"configurationURLPath", "projectURLPath", "runURLPath");
	String serialized = JSONSerializer.toJSONString(information);
	System.out.println(serialized);
	RepositoryTypeServiceInformation deserialized = JSONSerializer
		.fromJSONString(serialized,
			RepositoryTypeServiceInformation.class);
	assertEquals(information, deserialized);
    }
}
