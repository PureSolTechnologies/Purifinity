package com.puresoltechnologies.purifinity.framework.store.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.commons.trees.api.TreeUtils;
import com.puresoltechnologies.parsers.api.source.RepositoryLocation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.analysis.domain.HashIdFileTree;
import com.puresoltechnologies.purifinity.framework.analysis.impl.DirectoryRepositoryLocation;
import com.puresoltechnologies.purifinity.framework.analysis.test.HashIdFileTreeUtils;
import com.puresoltechnologies.purifinity.framework.commons.utils.FileSearch;
import com.puresoltechnologies.purifinity.framework.commons.utils.FileTree;
import com.puresoltechnologies.purifinity.framework.commons.utils.StopWatch;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.FileStore;
import com.puresoltechnologies.purifinity.framework.store.api.FileStoreException;
import com.puresoltechnologies.purifinity.framework.store.db.analysis.AnalysisStoreImpl;
import com.puresoltechnologies.purifinity.framework.store.db.analysis.FileStoreImpl;

public class AnalysisStoreImplIT extends AbstractDbStoreTest {

	private final AnalysisStore analysisStore = new AnalysisStoreImpl();

	@Test
	public void testCreateAnalysisProject() throws AnalysisStoreException {
		RepositoryLocation location = new DirectoryRepositoryLocation(
				"DirRepo", new File("/home/ludwig"));
		FileSearchConfiguration fileSearchConfiguration = new FileSearchConfiguration();
		fileSearchConfiguration.getFileExcludes().add("*.bak");
		fileSearchConfiguration.getFileIncludes().add("*.java");
		fileSearchConfiguration.getFileIncludes().add("*.xml");
		fileSearchConfiguration.getLocationExcludes().add(".*");
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
		FileSearchConfiguration fileSearchConfiguration = new FileSearchConfiguration();
		fileSearchConfiguration.getFileExcludes().add("*.bak");
		fileSearchConfiguration.getFileIncludes().add("*.java");
		fileSearchConfiguration.getFileIncludes().add("*.xml");
		fileSearchConfiguration.getLocationExcludes().add(".*");
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
		RepositoryLocation location = new DirectoryRepositoryLocation(
				"DirRepo", new File("/home/ludwig"));
		FileSearchConfiguration fileSearchConfiguration = new FileSearchConfiguration();
		fileSearchConfiguration.getFileExcludes().add("*.bak");
		fileSearchConfiguration.getFileIncludes().add("*.java");
		fileSearchConfiguration.getFileIncludes().add("*.xml");
		fileSearchConfiguration.getLocationExcludes().add(".*");
		AnalysisProjectSettings settings = new AnalysisProjectSettings("Name",
				"Description", fileSearchConfiguration,
				location.getSerialization());
		AnalysisProjectInformation information = analysisStore
				.createAnalysisProject(settings);
		assertNotNull(information);

		UUID projectUUID = information.getUUID();
		AnalysisProjectSettings analysisProjectSettings = analysisStore
				.readAnalysisProjectSettings(projectUUID);
		assertNotSame(settings, analysisProjectSettings);
		assertEquals(settings, analysisProjectSettings);
	}

	@Test
	public void testCreateUpdateAndReadAnalysisProjectInformationAndSettings()
			throws AnalysisStoreException {
		RepositoryLocation location = new DirectoryRepositoryLocation(
				"DirRepo", new File("/home/ludwig"));
		FileSearchConfiguration fileSearchConfiguration = new FileSearchConfiguration();
		fileSearchConfiguration.getFileExcludes().add("*.bak");
		fileSearchConfiguration.getFileIncludes().add("*.java");
		fileSearchConfiguration.getFileIncludes().add("*.xml");
		fileSearchConfiguration.getLocationExcludes().add(".*");
		AnalysisProjectSettings settings = new AnalysisProjectSettings("Name",
				"Description", fileSearchConfiguration,
				location.getSerialization());
		AnalysisProjectInformation information = analysisStore
				.createAnalysisProject(settings);
		assertNotNull(information);

		UUID projectUUID = information.getUUID();

		RepositoryLocation location2 = new DirectoryRepositoryLocation(
				"DirRepo", new File("/home/ludwig2"));
		FileSearchConfiguration fileSearchConfiguration2 = new FileSearchConfiguration();
		fileSearchConfiguration2.getFileExcludes().add("*.bak2");
		fileSearchConfiguration2.getFileIncludes().add("*.java2");
		fileSearchConfiguration2.getFileIncludes().add("*.xml2");
		fileSearchConfiguration2.getLocationExcludes().add(".*");
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

		RepositoryLocation location = new DirectoryRepositoryLocation(
				"DirRepo", new File("/home/ludwig"));
		FileSearchConfiguration fileSearchConfiguration = new FileSearchConfiguration();
		fileSearchConfiguration.getFileExcludes().add("*.bak");
		fileSearchConfiguration.getFileIncludes().add("*.java");
		fileSearchConfiguration.getFileIncludes().add("*.xml");
		fileSearchConfiguration.getLocationExcludes().add(".*");
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

	@Test
	public void testSaveAndReadAnalysisRunFileTree()
			throws AnalysisStoreException, IOException, FileStoreException {
		Date startTime = new Date();

		RepositoryLocation location = new DirectoryRepositoryLocation(
				"DirRepo", new File("/home/ludwig"));
		FileSearchConfiguration fileSearchConfiguration = new FileSearchConfiguration();
		fileSearchConfiguration.getFileExcludes().add("*.bak");
		fileSearchConfiguration.getFileIncludes().add("*.java");
		fileSearchConfiguration.getFileIncludes().add("*.xml");
		fileSearchConfiguration.getLocationExcludes().add(".*");
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

		HashIdFileTree hashIdFileTree = HashIdFileTreeUtils
				.convertToHashIdFileTree(fileTree);

		StopWatch storeFileTreeWatch = new StopWatch();
		storeFileTreeWatch.start();
		analysisStore.storeFileTree(projectUUID, analysisRun.getUUID(),
				hashIdFileTree);
		storeFileTreeWatch.stop();

		StopWatch readFileTreeWatch = new StopWatch();
		readFileTreeWatch.start();
		HashIdFileTree treeRead = analysisStore.readFileTree(projectUUID,
				analysisRun.getUUID());
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
