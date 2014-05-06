package com.puresoltechnologies.purifinity.server.test.analysis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.framework.analysis.impl.DirectoryRepositoryLocation;
import com.puresoltechnologies.purifinity.server.client.analysisservice.AnalysisStoreClient;

public class AnalysisStoreServiceClientIT extends
		AbstractAnalysisStoreServiceClientTest {

	private final AnalysisStoreClient analysisStoreService = new AnalysisStoreClient();

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
	public void testCreateProject() {
		Date start = new Date();
		AnalysisProjectSettings settings = createProjectSettings();

		// List<AnalysisProjectInformation> projects = analysisStoreService
		// .readAllAnalysisProjectInformation();
		// assertNotNull(projects);
		// assertEquals(0, projects.size());

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
	public void testReadAllProjects() {
		AnalysisProjectSettings settings = createProjectSettings();

		List<AnalysisProjectInformation> projects = analysisStoreService
				.readAllAnalysisProjectInformation();
		assertNotNull(projects);
		assertEquals(0, projects.size());

		AnalysisProjectInformation information = analysisStoreService
				.createAnalysisProject(settings);
		assertNotNull(information);

		projects = analysisStoreService.readAllAnalysisProjectInformation();
		assertNotNull(projects);
		assertEquals(1, projects.size());

		AnalysisProjectInformation readInformation = projects.get(0);
		assertEquals(information.getCreationTime(),
				readInformation.getCreationTime());
		assertEquals(information.getUUID(), readInformation.getUUID());
	}

	@Test
	public void testDeleteProject() {
		AnalysisProjectSettings settings = createProjectSettings();

		List<AnalysisProjectInformation> projects = analysisStoreService
				.readAllAnalysisProjectInformation();
		assertNotNull(projects);
		assertEquals(0, projects.size());

		AnalysisProjectInformation information = analysisStoreService
				.createAnalysisProject(settings);
		assertNotNull(information);

		projects = analysisStoreService.readAllAnalysisProjectInformation();
		assertNotNull(projects);
		assertEquals(1, projects.size());

		analysisStoreService.removeAnalysisProject(information.getUUID());

		projects = analysisStoreService.readAllAnalysisProjectInformation();
		assertNotNull(projects);
		assertEquals(0, projects.size());
	}

}
