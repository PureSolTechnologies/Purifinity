package com.puresoltechnologies.purifinity.server.test.analysis.store;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.puresoltechnologies.commons.misc.io.FileSearchConfiguration;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreService;
import com.puresoltechnologies.purifinity.server.plugin.filesystem.DirectoryRepository;

public class AnalysisStoreServiceBeanIT extends
	AbstractAnalysisStoreServiceServerTest {

    @Inject
    private AnalysisStoreService analysisStoreService;

    @Before
    public void cleanup() throws AnalysisStoreException {
	List<AnalysisProjectInformation> projects = analysisStoreService
		.readAllAnalysisProjectInformation();
	assertNotNull(projects);
	assertEquals(0, projects.size());
    }

    private AnalysisProjectSettings createProjectSettings() {
	DirectoryRepository directoryRepositoryLocation = new DirectoryRepository(
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
		.createAnalysisProject("TestProject", settings);
	assertNotNull(information);

	Date date = information.getCreationTime();
	assertNotNull(date);
	assertTrue(date.getTime() >= start.getTime());

	assertEquals("TestProject", information.getProjectId());
    }

    @Test
    public void testReadAllProjects() throws AnalysisStoreException {
	AnalysisProjectSettings settings = createProjectSettings();

	AnalysisProjectInformation information = analysisStoreService
		.createAnalysisProject("TestProject2", settings);
	assertNotNull(information);

	List<AnalysisProjectInformation> projects = analysisStoreService
		.readAllAnalysisProjectInformation();
	assertNotNull(projects);
	assertEquals(1, projects.size());

	AnalysisProjectInformation readInformation = projects.get(0);
	assertEquals(information.getCreationTime(),
		readInformation.getCreationTime());
	assertEquals(information.getProjectId(), readInformation.getProjectId());
    }

    @Test
    @Ignore("Temporarly disabled.")
    public void testDeleteProject() throws AnalysisStoreException {
	List<AnalysisProjectInformation> projects = analysisStoreService
		.readAllAnalysisProjectInformation();
	assertNotNull(projects);
	assertEquals(0, projects.size());

	AnalysisProjectSettings settings = createProjectSettings();
	AnalysisProjectInformation information = analysisStoreService
		.createAnalysisProject("TestProject3", settings);
	assertNotNull(information);

	projects = analysisStoreService.readAllAnalysisProjectInformation();
	assertNotNull(projects);
	assertEquals(1, projects.size());

	analysisStoreService.removeAnalysisProject(information.getProjectId());

	projects = analysisStoreService.readAllAnalysisProjectInformation();
	assertNotNull(projects);
	assertEquals(0, projects.size());
    }

}
