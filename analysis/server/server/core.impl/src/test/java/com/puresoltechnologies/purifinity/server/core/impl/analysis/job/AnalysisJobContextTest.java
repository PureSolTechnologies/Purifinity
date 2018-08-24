package com.puresoltechnologies.purifinity.server.core.impl.analysis.job;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.junit.Test;

import com.puresoltechnologies.purifinity.server.common.utils.PersistenceUtils;

public class AnalysisJobContextTest {

    @Test
    public void testSerialization() throws IOException {
	AnalysisJobContext context = new AnalysisJobContext(new Date(), null, null);
	File tempFile = File.createTempFile("test", "serialized");
	tempFile.deleteOnExit();
	PersistenceUtils.store(tempFile, context);
	Object deserialized = PersistenceUtils.restore(tempFile);
	assertNotNull(deserialized);
	assertTrue(tempFile.delete());
    }

}
