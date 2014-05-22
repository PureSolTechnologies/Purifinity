package com.puresoltechnologies.purifinity.server.plugin.c11;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.puresoltechnologies.purifinity.server.client.analysisservice.AnalysisServiceClient;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerInformation;

public class RegistrationIT extends AbstractJava7PluginClientTest {

	private static AnalysisServiceClient client;

	@BeforeClass
	public static void initilize() {
		client = AnalysisServiceClient.getInstance();
	}

	@AfterClass
	public static void destroy() throws IOException {
		client.close();
	}

	@Test
	public void test() throws IOException {
		Collection<AnalyzerInformation> analyzers = client.getAnalyzers();
		assertNotNull(analyzers);
		assertEquals(1, analyzers.size());
		AnalyzerInformation analyzer = analyzers.iterator().next();
		assertEquals("C", analyzer.getName());
		assertEquals("11", analyzer.getVersion());
	}
}
