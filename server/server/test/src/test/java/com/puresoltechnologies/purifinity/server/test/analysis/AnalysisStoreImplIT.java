package com.puresoltechnologies.purifinity.server.test.analysis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.inject.Inject;

import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.junit.Ignore;
import org.junit.Test;

import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.commons.misc.StopWatch;
import com.puresoltechnologies.commons.trees.TreeUtils;
import com.puresoltechnologies.parsers.source.RepositoryLocation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.framework.commons.utils.io.FileSearch;
import com.puresoltechnologies.purifinity.framework.commons.utils.io.FileTree;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.FileStore;
import com.puresoltechnologies.purifinity.framework.store.api.FileStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisStoreService;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.common.DirectoryRepositoryLocation;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.common.RepositoryLocationCreator;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.FileStoreImpl;
import com.puresoltechnologies.purifinity.wildfly.test.arquillian.EnhanceDeployment;

public class AnalysisStoreImplIT extends AbstractAnalysisStoreServiceServerTest {

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
		assertNotNull(analysisRun.getUUID());
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

		analysisRunRead = analysisStore.readAnalysisRun(projectUUID,
				analysisRun.getUUID());
		assertNotSame(analysisRun, analysisRunRead);
		assertEquals(analysisRun, analysisRunRead);
	}

	@Ignore("Database consistency check fails. A more realistic scenario needs to be tested.")
	@Test
	public void testSaveAndReadAnalysisRunFileTree()
			throws AnalysisStoreException, IOException, FileStoreException {
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

		FileTree fileTree = FileSearch.getFileTree(new File("."),
				fileSearchConfiguration);
		assertNotNull(fileTree);
		assertTrue(TreeUtils.countNodes(fileTree) > 0);

		StopWatch storeRawFileWatch = new StopWatch();
		storeRawFileWatch.start();
		FileStore fileStore = new FileStoreImpl();
		for (FileTree f : fileTree) {
			File file = f.getPathFile(true);
			if (file.isFile()) {
				try (FileInputStream inputStream = new FileInputStream(file)) {
					fileStore.storeRawFile(inputStream);
				}
			}
		}
		storeRawFileWatch.stop();

		AnalysisFileTree hashIdFileTree = TreeTestUtils
				.convertToHashIdFileTree(fileTree);

		StopWatch storeFileTreeWatch = new StopWatch();
		storeFileTreeWatch.start();
		analysisStore.storeAnalysisFileTree(projectUUID, analysisRun.getUUID(),
				hashIdFileTree);
		storeFileTreeWatch.stop();

		StopWatch readFileTreeWatch = new StopWatch();
		readFileTreeWatch.start();
		AnalysisFileTree treeRead = analysisStore.readAnalysisFileTree(
				projectUUID, analysisRun.getUUID());
		readFileTreeWatch.stop();

		System.out.println("Storing raw files: "
				+ storeRawFileWatch.getMilliseconds() + "ms");
		System.out.println("Storing file tree: "
				+ storeFileTreeWatch.getMilliseconds() + "ms");
		System.out.println("Reading file tree: "
				+ readFileTreeWatch.getMilliseconds() + "ms");

		assertNotNull(treeRead);
		assertEquals(TreeUtils.countNodes(hashIdFileTree),
				TreeUtils.countNodes(treeRead));
		TreeUtils.equalsWithoutOrder(hashIdFileTree, treeRead);
	}
}
