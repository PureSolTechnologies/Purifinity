package com.puresoltechnologies.purifinity.server.test.repositories;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Set;

import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.RepositoryTypes;
import com.puresoltechnologies.purifinity.server.client.analysisservice.AnalysisServiceClient;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryType;
import com.puresoltechnologies.purifinity.server.test.AbstractPurifinityServerClientTest;
import com.puresoltechnologies.purifinity.wildfly.test.arquillian.EnhanceDeployment;

public class RepositoryTypePluginServiceIT extends
		AbstractPurifinityServerClientTest {

	private static AnalysisServiceClient client;

	@BeforeClass
	public static void initialize() {
		client = AnalysisServiceClient.getInstance();
	}

	@AfterClass
	public static void destroy() throws IOException {
		client.close();
	}

	@EnhanceDeployment
	public static void enhance(EnterpriseArchive archive) throws Exception {
		String warName = "server.socket.impl.war";
		removeWAR(archive, warName);
	}

	@Test
	public void test() throws IOException {
		RepositoryTypes repositoryTypes = client.getRepositoryTypes();
		assertNotNull(repositoryTypes);
		Set<RepositoryType> types = repositoryTypes.getRepositoryTypes();
		assertNotNull(types);
		assertTrue(types.size() > 0);
	}

}
