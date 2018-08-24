package com.puresoltechnologies.purifinity.evaluation.domain.metrics;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Date;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.puresoltechnologies.commons.domain.JSONSerializer;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.UnspecifiedSourceCodeLocation;
import com.puresoltechnologies.versioning.Version;

public class GenericFileMetricsTest {

    @Test
    public void testSerialization() throws JsonGenerationException, JsonMappingException, IOException {
	FileMetricsImpl metrics = new FileMetricsImpl("evaluatorId", new Version(1, 2, 3),
		HashId.valueOf("SHA-256:abcdef"), new UnspecifiedSourceCodeLocation(), new Date());
	String serialized = JSONSerializer.toJSONString(metrics);
	System.out.println(serialized);
	FileMetricsImpl deserialized = JSONSerializer.fromJSONString(serialized, FileMetricsImpl.class);
	assertEquals(metrics, deserialized);
    }
}
