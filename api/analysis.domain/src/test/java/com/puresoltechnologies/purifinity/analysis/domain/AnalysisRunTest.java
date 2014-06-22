package com.puresoltechnologies.purifinity.analysis.domain;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.misc.JSONSerializer;
import com.puresoltechnologies.parsers.source.UnspecifiedSourceCodeLocation;

public class AnalysisRunTest {

    @Test
    public void test() throws JsonGenerationException, JsonMappingException,
	    IOException {
	Date time = new Date();
	UUID projectUUID = UUID.randomUUID();
	UUID uuid = UUID.randomUUID();
	AnalysisRunInformation analysisRunInformation = new AnalysisRunInformation(
		projectUUID, uuid, time, 1000, "description",
		new FileSearchConfiguration(new ArrayList<String>(),
			new ArrayList<String>(), new ArrayList<String>(),
			new ArrayList<String>(), true));
	AnalysisFileTree tree = new AnalysisFileTree(null, "name",
		HashId.valueOf("SHA-256:12445"), true, 100, 100,
		new UnspecifiedSourceCodeLocation(),
		new ArrayList<AnalysisInformation>());
	AnalysisRun run = new AnalysisRun(analysisRunInformation, tree);
	String json = JSONSerializer.toJSONString(run);
	assertNotNull(json);
	AnalysisRun unmarshalled = JSONSerializer.fromJSONString(json,
		AnalysisRun.class);
	assertNotNull(unmarshalled);
    }
}
