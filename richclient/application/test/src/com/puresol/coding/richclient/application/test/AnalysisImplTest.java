package com.puresol.coding.richclient.application.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.io.File;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.puresol.coding.analysis.api.Analysis;
import com.puresol.coding.analysis.api.AnalysisInformation;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalysisRunInformation;
import com.puresol.coding.analysis.api.AnalysisSettings;
import com.puresol.coding.analysis.api.AnalysisStore;
import com.puresol.coding.analysis.api.ModuleStoreException;
import com.puresol.coding.analysis.api.TestFileSearchConfiguration;
import com.puresol.coding.analysis.filestore.AnalysisStoreImpl;

public class AnalysisImplTest {

	private static AnalysisStore analysisStore;
	private static Analysis analysis;

	@BeforeClass
	public static void initialize() throws ModuleStoreException {
		analysisStore = new AnalysisStoreImpl();
		analysis = analysisStore.createAnalysis(new AnalysisSettings("Name",
				"Description", new TestFileSearchConfiguration(),
				new DirectoryRepositoryLocation(".", new File("."))));
		assertNotNull(analysis);
	}

	@Test
	public void test() throws ModuleStoreException {
		List<AnalysisRunInformation> allRunInformation = analysis
				.getAllRunInformation();
		assertNotNull(allRunInformation);
	}

	@Test
	public void testUpdateSettings() throws ModuleStoreException {
		AnalysisInformation oldInformation = analysis.getInformation();
		AnalysisSettings settingsForUpdate = new AnalysisSettings("Name2",
				"Description2", new TestFileSearchConfiguration(),
				new DirectoryRepositoryLocation("/", new File("/")));

		analysis.updateSettings(settingsForUpdate);

		AnalysisInformation newInformation = analysis.getInformation();
		AnalysisSettings newSettings = analysis.getSettings();

		/* UUID and creation time must not to be changed! */
		assertNotSame(oldInformation, newInformation);
		assertEquals(oldInformation.getUUID(), newInformation.getUUID());
		assertEquals(oldInformation.getCreationTime(),
				newInformation.getCreationTime());
		/* name and description are updated */
		assertEquals("Name2", newInformation.getName());
		assertEquals("Description2", newInformation.getDescription());

		assertEquals("Name2", newSettings.getName());
		assertEquals("Description2", newSettings.getDescription());
		assertEquals("/", newSettings.getRepositoryLocation().getName());
	}

	@Test
	@Ignore("For tests, we do not have a bundle context!")
	public void testCreateRun() throws Exception {
		AnalysisRun analysisRun = analysis.runAnalysis();
		assertNotNull(analysisRun);
		AnalysisRun run = analysis.loadLastAnalysisRun();
		assertNotNull(run);
	}

	@AfterClass
	public static void destroy() throws ModuleStoreException {
		assertNotNull(analysisStore);
		assertNotNull(analysis);
		analysisStore.removeAnalysis(analysis.getInformation().getUUID());
	}

}
