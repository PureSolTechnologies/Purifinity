package com.puresoltechnologies.purifinity.server.analysisservice.rest.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

import com.puresoltechnologies.purifinity.server.common.rest.JSONMapper;

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

		String jsonString = JSONMapper.toJSONString(settings);
		System.out.println(jsonString);
		AnalysisProjectSettings fromJSONString = JSONMapper.fromJSONString(
				jsonString, AnalysisProjectSettings.class);
		System.out.println(fromJSONString);
	}
}
