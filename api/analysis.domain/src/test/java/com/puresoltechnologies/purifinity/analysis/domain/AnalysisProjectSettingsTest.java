package com.puresoltechnologies.purifinity.analysis.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

import com.puresoltechnologies.commons.math.JSONSerializer;
import com.puresoltechnologies.commons.misc.io.FileSearchConfiguration;

public class AnalysisProjectSettingsTest {

	@Test
	public void test() throws JsonGenerationException, JsonMappingException,
			IOException {
		List<String> dirIncludes = new ArrayList<>();
		List<String> dirExcludes = new ArrayList<>();
		List<String> fileIncludes = new ArrayList<>();
		List<String> fileExcludes = new ArrayList<>();
		FileSearchConfiguration fileSearchConfiguration = new FileSearchConfiguration(
				dirIncludes, dirExcludes, fileIncludes, fileExcludes, true);
		AnalysisProjectSettings settings = new AnalysisProjectSettings("Name",
				"Description", fileSearchConfiguration, new Properties());

		String jsonString = JSONSerializer.toJSONString(settings);
		System.out.println(jsonString);
		AnalysisProjectSettings fromJSONString = JSONSerializer.fromJSONString(
				jsonString, AnalysisProjectSettings.class);
		System.out.println(fromJSONString);
	}
}
