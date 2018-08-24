package com.puresoltechnologies.purifinity.analysis.api;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.puresoltechnologies.commons.domain.JSONSerializer;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.commons.misc.io.FileSearchConfiguration;
import com.puresoltechnologies.parsers.source.UnspecifiedSourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;

public class AnalysisRunTest {

    @Test
    @Ignore("Ignored for now due to NPE in Jackson.")
    public void test() throws JsonGenerationException, JsonMappingException, IOException {
	Instant time = Instant.now();
	String projectId = "test_project";
	long runId = System.currentTimeMillis();
	AnalysisRunInformation analysisRunInformation = new AnalysisRunInformation(projectId, runId, time,
		Duration.ofMillis(1000), "description", new FileSearchConfiguration(new ArrayList<String>(),
			new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), true));
	AnalysisFileTree tree = new AnalysisFileTree(null, "name", HashId.valueOf("SHA-256:12445"), true, 100, 100,
		new UnspecifiedSourceCodeLocation(), new ArrayList<AnalysisInformation>());
	AnalysisRun run = new AnalysisRun(analysisRunInformation, tree);
	String json = JSONSerializer.toJSONString(run);
	assertNotNull(json);
	AnalysisRun unmarshalled = JSONSerializer.fromJSONString(json, AnalysisRun.class);
	assertNotNull(unmarshalled);
    }
}
