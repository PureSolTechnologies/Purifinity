package com.puresoltechnologies.purifinity.server.test.analysis.store;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.client.analysisservice.AnalysisStoreClient;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.common.DirectoryRepositoryLocation;

public class AnalysisStoreServiceClientIT extends
		AbstractAnalysisStoreServiceClientTest {

	private static AnalysisStoreClient analysisStoreService;

	@BeforeClass
	public static void initialize() {
		analysisStoreService = AnalysisStoreClient.getInstance();
	}

	@AfterClass
	public static void destroy() throws Exception {
		if (analysisStoreService != null) {
			analysisStoreService.close();
		}
	}

	@Before
	public void cleanup() throws AnalysisStoreException {
		cleanupAnalysisStore();

		List<AnalysisProjectInformation> projects = analysisStoreService
				.readAllAnalysisProjectInformation();
		assertNotNull(projects);
		assertEquals(0, projects.size());
	}

	private AnalysisProjectSettings createProjectSettings() {
		DirectoryRepositoryLocation directoryRepositoryLocation = new DirectoryRepositoryLocation(
				"Test", new File("."));
		FileSearchConfiguration fileSearchConfiguration = new FileSearchConfiguration(
				new ArrayList<String>(), new ArrayList<String>(),
				new ArrayList<String>(), new ArrayList<String>(), true);
		AnalysisProjectSettings settings = new AnalysisProjectSettings("name",
				"description", fileSearchConfiguration,
				directoryRepositoryLocation.getSerialization());
		return settings;
	}

	@Test
	public void testCreateProject() throws AnalysisStoreException {
		Date start = new Date();
		AnalysisProjectSettings settings = createProjectSettings();

		AnalysisProjectInformation information = analysisStoreService
				.createAnalysisProject(settings);
		assertNotNull(information);

		Date date = information.getCreationTime();
		assertNotNull(date);
		assertTrue(date.getTime() >= start.getTime());

		UUID uuid = information.getUUID();
		assertNotNull(uuid);
	}

	@Test
	public void testReadAllProjects() throws AnalysisStoreException {
		AnalysisProjectSettings settings = createProjectSettings();

		AnalysisProjectInformation information = analysisStoreService
				.createAnalysisProject(settings);
		assertNotNull(information);

		List<AnalysisProjectInformation> projects = analysisStoreService
				.readAllAnalysisProjectInformation();
		assertNotNull(projects);
		assertEquals(1, projects.size());

		AnalysisProjectInformation readInformation = projects.get(0);
		assertEquals(information.getCreationTime(),
				readInformation.getCreationTime());
		assertEquals(information.getUUID(), readInformation.getUUID());
	}

	@Test
	public void testDeleteProject() throws AnalysisStoreException {
		AnalysisProjectSettings settings = createProjectSettings();

		AnalysisProjectInformation information = analysisStoreService
				.createAnalysisProject(settings);
		assertNotNull(information);

		List<AnalysisProjectInformation> projects = analysisStoreService
				.readAllAnalysisProjectInformation();
		assertNotNull(projects);
		assertEquals(1, projects.size());

		analysisStoreService.removeAnalysisProject(information.getUUID());

		projects = analysisStoreService.readAllAnalysisProjectInformation();
		assertNotNull(projects);
		assertEquals(0, projects.size());
	}

}