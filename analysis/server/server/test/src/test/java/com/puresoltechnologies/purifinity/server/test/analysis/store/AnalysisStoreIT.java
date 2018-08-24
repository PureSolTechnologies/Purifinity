package com.puresoltechnologies.purifinity.server.test.analysis.store;

import org.junit.Ignore;

@Ignore
public class AnalysisStoreIT extends AbstractAnalysisStoreServiceServerTest {

	// @Inject
	// private AnalysisStoreService analysisStore;
	//
	// @EnhanceDeployment
	// public static void removeWARFile(EnterpriseArchive archive)
	// throws Exception {
	// removeWAR(archive, "server.socket.impl.war");
	// }
	//
	// @Test
	// public void testCreateAnalysisProject() throws AnalysisStoreException {
	// RepositoryLocation location = new DirectoryRepository("DirRepo",
	// new File("/home/ludwig"));
	// FileSearchConfiguration fileSearchConfiguration = new
	// FileSearchConfiguration(
	// new ArrayList<String>(), Arrays.asList(".*"), Arrays.asList(
	// "*.java", "*.xml"), Arrays.asList("*.bak"), true);
	// AnalysisProjectSettings settings = new AnalysisProjectSettings("Name",
	// "Description", fileSearchConfiguration,
	// location.getSerialization());
	// AnalysisProjectInformation information = analysisStore
	// .createAnalysisProject("test_project", settings);
	// assertNotNull(information);
	// assertNotNull(information.getProjectId());
	// assertNotNull(information.getCreationTime());
	// long timeDifference = new Date().getTime()
	// - information.getCreationTime().getTime();
	// assertTrue(timeDifference >= 0);
	// assertTrue(timeDifference < 1000);
	// }
	//
	// @Test
	// public void testCreateAndReadAnalysisProjectInformation()
	// throws AnalysisStoreException {
	// RepositoryLocation location = new DirectoryRepository("DirRepo",
	// new File("/home/ludwig"));
	// FileSearchConfiguration fileSearchConfiguration = new
	// FileSearchConfiguration(
	// new ArrayList<String>(), Arrays.asList(".*"), Arrays.asList(
	// "*.java", "*.xml"), Arrays.asList("*.bak"), true);
	// AnalysisProjectSettings settings = new AnalysisProjectSettings("Name",
	// "Description", fileSearchConfiguration,
	// location.getSerialization());
	// AnalysisProjectInformation information = analysisStore
	// .createAnalysisProject("test_project2", settings);
	// assertNotNull(information);
	//
	// String projectId = information.getProjectId();
	// AnalysisProjectInformation readInformation = analysisStore
	// .readAnalysisProjectInformation(projectId);
	// assertNotSame(information, readInformation);
	// assertEquals(information, readInformation);
	// }
	//
	// @Test
	// public void testCreateAndReadAnalysisProjectSettings()
	// throws AnalysisStoreException {
	// FileSearchConfiguration fileSearchConfiguration = new
	// FileSearchConfiguration(
	// new ArrayList<String>(), Arrays.asList(".*"), Arrays.asList(
	// "*.java", "*.xml"), Arrays.asList("*.bak"), true);
	// RepositoryLocation repositoryLocation = new DirectoryRepository(
	// "DirRepo", new File("/home/ludwig"));
	// AnalysisProjectSettings settings = new AnalysisProjectSettings("Name",
	// "Description", fileSearchConfiguration,
	// repositoryLocation.getSerialization());
	// AnalysisProjectInformation information = analysisStore
	// .createAnalysisProject("test_project3", settings);
	// assertNotNull(information);
	//
	// String projectId = information.getProjectId();
	// AnalysisProjectSettings analysisProjectSettings = analysisStore
	// .readAnalysisProjectSettings(projectId);
	// assertNotSame(settings, analysisProjectSettings);
	// assertEquals(settings, analysisProjectSettings);
	// }
	//
	// @Test
	// public void
	// testCreateUpdateAndReadAnalysisProjectInformationAndSettings()
	// throws AnalysisStoreException {
	// FileSearchConfiguration fileSearchConfiguration = new
	// FileSearchConfiguration(
	// new ArrayList<String>(), Arrays.asList(".*"), Arrays.asList(
	// "*.java", "*.xml"), Arrays.asList("*.bak"), true);
	// RepositoryLocation location = new DirectoryRepository("DirRepo",
	// new File("/home/ludwig"));
	// AnalysisProjectSettings settings = new AnalysisProjectSettings("Name",
	// "Description", fileSearchConfiguration,
	// location.getSerialization());
	// AnalysisProjectInformation information = analysisStore
	// .createAnalysisProject("test_project4", settings);
	// assertNotNull(information);
	//
	// String projectId = information.getProjectId();
	//
	// FileSearchConfiguration fileSearchConfiguration2 = new
	// FileSearchConfiguration(
	// new ArrayList<String>(), Arrays.asList(".*"), Arrays.asList(
	// "*.java2", "*.xml2"), Arrays.asList("*.bak2"), true);
	// RepositoryLocation location2 = new DirectoryRepository("DirRepo",
	// new File("/home/ludwig2"));
	// AnalysisProjectSettings settings2 = new AnalysisProjectSettings(
	// "Name2", "Description2", fileSearchConfiguration2,
	// location2.getSerialization());
	// analysisStore.updateAnalysisProjectSettings(projectId, settings2);
	//
	// AnalysisProjectInformation information2 = analysisStore
	// .readAnalysisProjectInformation(projectId);
	// assertNotSame(information, information2);
	// assertEquals(information, information2);
	//
	// AnalysisProjectSettings settingsRead = analysisStore
	// .readAnalysisProjectSettings(projectId);
	// assertNotSame(settings2, settingsRead);
	// assertEquals(settings2, settingsRead);
	// }
	//
	// @Test
	// public void testCreateAndReadAnalysisRun() throws AnalysisStoreException
	// {
	// Date startTime = new Date();
	//
	// FileSearchConfiguration fileSearchConfiguration = new
	// FileSearchConfiguration(
	// new ArrayList<String>(), Arrays.asList(".*"), Arrays.asList(
	// "*.java", "*.xml"), Arrays.asList("*.bak"), true);
	// RepositoryLocation location = new DirectoryRepository("DirRepo",
	// new File("/home/ludwig"));
	// AnalysisProjectSettings settings = new AnalysisProjectSettings("Name",
	// "Description", fileSearchConfiguration,
	// location.getSerialization());
	// AnalysisProjectInformation information = analysisStore
	// .createAnalysisProject("test_project5", settings);
	// assertNotNull(information);
	//
	// String projectId = information.getProjectId();
	//
	// AnalysisRunInformation analysisRun = analysisStore.createAnalysisRun(
	// projectId, startTime, 12345, "Analysis Run Description",
	// fileSearchConfiguration);
	// assertNotNull(analysisRun);
	// assertNotNull(analysisRun.getRunId());
	// assertEquals(projectId, analysisRun.getProjectId());
	// assertEquals(startTime, analysisRun.getStartTime());
	// assertEquals(12345, analysisRun.getDuration());
	// assertEquals("Analysis Run Description", analysisRun.getDescription());
	//
	// List<AnalysisRunInformation> allRunInformation = analysisStore
	// .readAllRunInformation(projectId);
	// assertNotNull(allRunInformation);
	// assertEquals(1, allRunInformation.size());
	// AnalysisRunInformation analysisRunRead = allRunInformation.get(0);
	// assertNotSame(analysisRun, analysisRunRead);
	// assertEquals(analysisRun, analysisRunRead);
	//
	// analysisRunRead = analysisStore.readAnalysisRunInformation(projectId,
	// analysisRun.getRunId());
	// assertNotSame(analysisRun, analysisRunRead);
	// assertEquals(analysisRun, analysisRunRead);
	// }

}
