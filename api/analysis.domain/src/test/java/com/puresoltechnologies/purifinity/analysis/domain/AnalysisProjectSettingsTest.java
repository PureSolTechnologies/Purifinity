package com.puresoltechnologies.purifinity.analysis.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.puresoltechnologies.commons.math.JSONSerializer;
import com.puresoltechnologies.commons.misc.io.FileSearchConfiguration;

public class AnalysisProjectSettingsTest {

	@Test
	public void testSerialization() throws JsonGenerationException,
			JsonMappingException, IOException {
		List<String> dirIncludes = new ArrayList<>();
		dirIncludes.add("src");
		List<String> dirExcludes = new ArrayList<>();
		dirExcludes.add("target");
		List<String> fileIncludes = new ArrayList<>();
		fileIncludes.add("*.java");
		List<String> fileExcludes = new ArrayList<>();
		fileExcludes.add("*~");
		FileSearchConfiguration fileSearchConfiguration = new FileSearchConfiguration(
				dirIncludes, dirExcludes, fileIncludes, fileExcludes, true);
		Properties repositoryLocation = new Properties();
		AnalysisProjectSettings settings = new AnalysisProjectSettings("Name",
				"Description", fileSearchConfiguration, repositoryLocation);

		String jsonString = JSONSerializer.toJSONString(settings);
		System.out.println(jsonString);
		AnalysisProjectSettings fromJSONString = JSONSerializer.fromJSONString(
				jsonString, AnalysisProjectSettings.class);
		System.out.println(fromJSONString);
	}
}
