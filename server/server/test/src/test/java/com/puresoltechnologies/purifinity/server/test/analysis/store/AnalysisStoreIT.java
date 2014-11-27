package com.puresoltechnologies.purifinity.server.test.analysis.store;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.inject.Inject;

import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.junit.Test;

import com.puresoltechnologies.commons.os.FileSearchConfiguration;
import com.puresoltechnologies.parsers.source.RepositoryLocation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreService;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.common.DirectoryRepositoryLocation;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.common.RepositoryLocationCreator;
import com.puresoltechnologies.purifinity.wildfly.test.arquillian.EnhanceDeployment;

public class AnalysisStoreIT extends AbstractAnalysisStoreServiceServerTest {

	@Inject
	private AnalysisStoreService analysisStore;

	@EnhanceDeployment
	public static void removeWARFile(EnterpriseArchive archive)
			throws Exception {
		removeWAR(archive, "server.socket.impl.war");
	}

	@Test
	public void testCreateAnalysisProject() throws AnalysisStoreException {
		RepositoryLocation location = new DirectoryRepositoryLocation(
				"DirRepo", new File("/home/ludwig"));
		FileSearchConfiguration fileSearchConfiguration = new FileSearchConfiguration(
				new ArrayList<String>(), Arrays.asList(".*"), Arrays.asList(
						"*.java", "*.xml"), Arrays.asList("*.bak"), true);
		AnalysisProjectSettings settings = new AnalysisProjectSettings("Name",
				"Description", fileSearchConfiguration,
				location.getSerialization());
		AnalysisProjectInformation information = analysisStore
				.createAnalysisProject(settings);
		assertNotNull(information);
		assertNotNull(information.getUUID());
		assertNotNull(information.getCreationTime());
		long timeDifference = new Date().getTime()
				- information.getCreationTime().getTime();
		assertTrue(timeDifference >= 0);
		assertTrue(timeDifference < 1000);
	}

	@Test
	public void testCreateAndReadAnalysisProjectInformation()
			throws AnalysisStoreException {
		RepositoryLocation location = new DirectoryRepositoryLocation(
				"DirRepo", new File("/home/ludwig"));
		FileSearchConfiguration fileSearchConfiguration = new FileSearchConfiguration(
				new ArrayList<String>(), Arrays.asList(".*"), Arrays.asList(
						"*.java", "*.xml"), Arrays.asList("*.bak"), true);
		AnalysisProjectSettings settings = new AnalysisProjectSettings("Name",
				"Description", fileSearchConfiguration,
				location.getSerialization());
		AnalysisProjectInformation information = analysisStore
				.createAnalysisProject(settings);
		assertNotNull(information);

		UUID projectUUID = information.getUUID();
		AnalysisProjectInformation readInformation = analysisStore
				.readAnalysisProjectInformation(projectUUID);
		assertNotSame(information, readInformation);
		assertEquals(information, readInformation);
	}

	@Test
	public void testCreateAndReadAnalysisProjectSettings()
			throws AnalysisStoreException {
		FileSearchConfiguration fileSearchConfiguration = new FileSearchConfiguration(
				new ArrayList<String>(), Arrays.asList(".*"), Arrays.asList(
						"*.java", "*.xml"), Arrays.asList("*.bak"), true);
		RepositoryLocation repositoryLocation = new DirectoryRepositoryLocation(
				"DirRepo", new File("/home/ludwig"));
		AnalysisProjectSettings settings = new AnalysisProjectSettings("Name",
				"Description", fileSearchConfiguration,
				repositoryLocation.getSerialization());
		AnalysisProjectInformation information = analysisStore
				.createAnalysisProject(settings);
		assertNotNull(information);

		UUID projectUUID = information.getUUID();
		AnalysisProjectSettings analysisProjectSettings = analysisStore
				.readAnalysisProjectSettings(projectUUID);
		assertNotSame(settings, analysisProjectSettings);
		assertEquals(settings, analysisProjectSettings);

		Properties readRepositoryLocationSerialized = analysisProjectSettings
				.getRepositoryLocation();
		assertNotNull(readRepositoryLocationSerialized);
		RepositoryLocation readRepositoryLocation = RepositoryLocationCreator
				.createFromSerialization(readRepositoryLocationSerialized);
		assertNotNull(readRepositoryLocation);
		assertEquals(repositoryLocation, readRepositoryLocation);
	}

	@Test
	public void testCreateUpdateAndReadAnalysisProjectInformationAndSettings()
			throws AnalysisStoreException {
		FileSearchConfiguration fileSearchConfiguration = new FileSearchConfiguration(
				new ArrayList<String>(), Arrays.asList(".*"), Arrays.asList(
						"*.java", "*.xml"), Arrays.asList("*.bak"), true);
		RepositoryLocation location = new DirectoryRepositoryLocation(
				"DirRepo", new File("/home/ludwig"));
		AnalysisProjectSettings settings = new AnalysisProjectSettings("Name",
				"Description", fileSearchConfiguration,
				location.getSerialization());
		AnalysisProjectInformation information = analysisStore
				.createAnalysisProject(settings);
		assertNotNull(information);

		UUID projectUUID = information.getUUID();

		FileSearchConfiguration fileSearchConfiguration2 = new FileSearchConfiguration(
				new ArrayList<String>(), Arrays.asList(".*"), Arrays.asList(
						"*.java2", "*.xml2"), Arrays.asList("*.bak2"), true);
		RepositoryLocation location2 = new DirectoryRepositoryLocation(
				"DirRepo", new File("/home/ludwig2"));
		AnalysisProjectSettings settings2 = new AnalysisProjectSettings(
				"Name2", "Description2", fileSearchConfiguration2,
				location2.getSerialization());
		analysisStore.updateAnalysisProjectSettings(projectUUID, settings2);

		AnalysisProjectInformation information2 = analysisStore
				.readAnalysisProjectInformation(projectUUID);
		assertNotSame(information, information2);
		assertEquals(information, information2);

		AnalysisProjectSettings settingsRead = analysisStore
				.readAnalysisProjectSettings(projectUUID);
		assertNotSame(settings2, settingsRead);
		assertEquals(settings2, settingsRead);
	}

	@Test
	public void testCreateAndReadAnalysisRun() throws AnalysisStoreException {
		Date startTime = new Date();

		FileSearchConfiguration fileSearchConfiguration = new FileSearchConfiguration(
				new ArrayList<String>(), Arrays.asList(".*"), Arrays.asList(
						"*.java", "*.xml"), Arrays.asList("*.bak"), true);
		RepositoryLocation location = new DirectoryRepositoryLocation(
				"DirRepo", new File("/home/ludwig"));
		AnalysisProjectSettings settings = new AnalysisProjectSettings("Name",
				"Description", fileSearchConfiguration,
				location.getSerialization());
		AnalysisProjectInformation information = analysisStore
				.createAnalysisProject(settings);
		assertNotNull(information);

		UUID projectUUID = information.getUUID();

		AnalysisRunInformation analysisRun = analysisStore.createAnalysisRun(
				projectUUID, startTime, 12345, "Analysis Run Description",
				fileSearchConfiguration);
		assertNotNull(analysisRun);
		assertNotNull(analysisRun.getRunUUID());
		assertEquals(projectUUID, analysisRun.getProjectUUID());
		assertEquals(startTime, analysisRun.getStartTime());
		assertEquals(12345, analysisRun.getDuration());
		assertEquals("Analysis Run Description", analysisRun.getDescription());

		List<AnalysisRunInformation> allRunInformation = analysisStore
				.readAllRunInformation(projectUUID);
		assertNotNull(allRunInformation);
		assertEquals(1, allRunInformation.size());
		AnalysisRunInformation analysisRunRead = allRunInformation.get(0);
		assertNotSame(analysisRun, analysisRunRead);
		assertEquals(analysisRun, analysisRunRead);

		analysisRunRead = analysisStore.readAnalysisRunInformation(projectUUID,
				analysisRun.getRunUUID());
		assertNotSame(analysisRun, analysisRunRead);
		assertEquals(analysisRun, analysisRunRead);
	}

}
