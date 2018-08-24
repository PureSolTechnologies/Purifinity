package com.puresoltechnologies.purifinity.analysis.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.puresoltechnologies.commons.domain.JSONSerializer;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.parsers.source.SourceFileLocation;

public class AnalysisFileTreeTest {

    @Test
    public void testJSONSerialization() throws JsonGenerationException, JsonMappingException, IOException {
	SourceCodeLocation sourceCodeLocation = new SourceFileLocation(new File("/"), new File("internal/file.txt"));
	AnalysisFileTree analysisFileTree = new AnalysisFileTree(null, "Name",
		HashId.valueOf("SHA-256:1234567890abcdef"), true, 123, 123, sourceCodeLocation, new ArrayList<>());
	AnalysisFileTree analysisFileTree2 = new AnalysisFileTree(analysisFileTree, "Name",
		HashId.valueOf("SHA-256:1234567890abcdef1234567890"), true, 1234, 1234, sourceCodeLocation,
		new ArrayList<>());
	String jsonString = JSONSerializer.toJSONString(analysisFileTree);
	System.out.println(jsonString);
	assertNotNull(jsonString);
	AnalysisFileTree deserialized = JSONSerializer.fromJSONString(jsonString, AnalysisFileTree.class);
	assertEquals(analysisFileTree, deserialized);
    }

}
