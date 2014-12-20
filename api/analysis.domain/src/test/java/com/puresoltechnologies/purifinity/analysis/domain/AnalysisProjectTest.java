package com.puresoltechnologies.purifinity.analysis.domain;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

import com.puresoltechnologies.commons.math.JSONSerializer;
import com.puresoltechnologies.commons.misc.FileSearchConfiguration;

public class AnalysisProjectTest {

	@Test
	public void test() throws JsonGenerationException, JsonMappingException,
			IOException {
		Date time = new Date();
		UUID uuid = UUID.randomUUID();
		AnalysisProjectInformation analysisProjectInformation = new AnalysisProjectInformation(
				uuid, time);
		AnalysisProjectSettings analysisProjectSettings = new AnalysisProjectSettings(
				"name", "description",
				new FileSearchConfiguration(new ArrayList<String>(),
						new ArrayList<String>(), new ArrayList<String>(),
						new ArrayList<String>(), true), new Properties());
		AnalysisProject project = new AnalysisProject(
				analysisProjectInformation, analysisProjectSettings);
		String json = JSONSerializer.toJSONString(project);
		assertNotNull(json);
		AnalysisProject unmarshalled = JSONSerializer.fromJSONString(json,
				AnalysisProject.class);
		assertNotNull(unmarshalled);
	}
}
