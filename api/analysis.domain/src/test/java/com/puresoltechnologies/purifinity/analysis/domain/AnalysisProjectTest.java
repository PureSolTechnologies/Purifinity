package com.puresoltechnologies.purifinity.analysis.domain;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.puresoltechnologies.commons.math.JSONSerializer;
import com.puresoltechnologies.commons.misc.io.FileSearchConfiguration;

public class AnalysisProjectTest {

    @Test
    public void test() throws JsonGenerationException, JsonMappingException,
	    IOException {
	Date time = new Date();
	String projectId = "test_project";
	AnalysisProjectInformation analysisProjectInformation = new AnalysisProjectInformation(
		projectId, time);
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
