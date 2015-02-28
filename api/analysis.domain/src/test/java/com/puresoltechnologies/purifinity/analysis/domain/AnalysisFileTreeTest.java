package com.puresoltechnologies.purifinity.analysis.domain;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

import com.puresoltechnologies.commons.math.JSONSerializer;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.UnspecifiedSourceCodeLocation;

public class AnalysisFileTreeTest {

    @Test
    public void testSerialization() throws JsonGenerationException,
	    JsonMappingException, IOException {
	AnalysisFileTree parent = new AnalysisFileTree(null, "parent",
		HashId.valueOf("SHA-256:123456"), false, 1, 2,
		new UnspecifiedSourceCodeLocation(),
		new ArrayList<AnalysisInformation>());
	// AnalysisFileTree dir1 = new AnalysisFileTree(parent, "parent",
	// HashId.valueOf("SHA-256:123456"), false, 1, 2,
	// new UnspecifiedSourceCodeLocation(),
	// new ArrayList<AnalysisInformation>());
	// AnalysisFileTree file1 = new AnalysisFileTree(dir1, "parent",
	// HashId.valueOf("SHA-256:123456"), false, 1, 2,
	// new UnspecifiedSourceCodeLocation(),
	// new ArrayList<AnalysisInformation>());
	// AnalysisFileTree dir2 = new AnalysisFileTree(parent, "parent",
	// HashId.valueOf("SHA-256:123456"), false, 1, 2,
	// new UnspecifiedSourceCodeLocation(),
	// new ArrayList<AnalysisInformation>());
	String serialized = JSONSerializer.toJSONString(parent);
	System.out.println(serialized);
	AnalysisFileTree deserialized = JSONSerializer.fromJSONString(
		serialized, AnalysisFileTree.class);
	assertEquals(parent, deserialized);
    }

}
