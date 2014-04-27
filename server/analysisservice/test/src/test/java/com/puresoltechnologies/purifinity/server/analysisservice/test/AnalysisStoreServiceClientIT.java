package com.puresoltechnologies.purifinity.server.analysisservice.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.puresoltechnologies.purifinity.framework.analysis.impl.DirectoryRepositoryLocation;
import com.puresoltechnologies.purifinity.server.analysisservice.client.AnalysisStoreServiceClient;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalysisStoreRestException;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.FileSearchConfiguration;

public class AnalysisStoreServiceClientIT extends
		AbstractAnalysisStoreServiceClientTest {

	private final AnalysisStoreServiceClient analysisStoreService = new AnalysisStoreServiceClient();

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
	public void testCreateProject() throws AnalysisStoreRestException {
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
	public void testReadAllProjects() throws AnalysisStoreRestException {
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
	public void testDeleteProject() throws AnalysisStoreRestException {
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
