package com.puresoltechnologies.purifinity.framework.store.fs.analysis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.framework.analysis.impl.DirectoryRepositoryLocation;
import com.puresoltechnologies.purifinity.framework.analysis.test.TestFileSearchConfiguration;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreFactory;

@Ignore("The services are not reachable here.")
public class AnalysisStoreImplTest {

	@Test
	public void testGetAllAnalysisInformation() throws AnalysisStoreException {
		AnalysisStore store = AnalysisStoreFactory.getFactory().getInstance();
		List<AnalysisProject> allAnalysisInformation = store
				.getAnalysisProjects();
		assertNotNull(allAnalysisInformation);
		File storeDir = new File(System.getProperty("user.home"),
				".code.analysis.store");
		assertTrue(storeDir.exists());
		assertTrue(storeDir.isDirectory());
	}

	@Test
	public void testCreateAndDeleteAnalysis() throws AnalysisStoreException {
		AnalysisStore store = AnalysisStoreFactory.getFactory().getInstance();
		File sourceDirectory = new File(".");
		AnalysisProject analysis = store
				.createAnalysis(new AnalysisProjectSettings(
						"Name",
						"Description",
						new TestFileSearchConfiguration(),
						new DirectoryRepositoryLocation("name", sourceDirectory)));

		AnalysisProjectInformation information = analysis.getInformation();
		assertNotNull(information.getUUID());
		assertNotNull(information.getCreationTime());
		assertEquals("Name", analysis.getSettings().getName());
		assertEquals("Description", analysis.getSettings().getDescription());

		File analysisDir = new File(new File(System.getProperty("user.home"),
				".code.analysis.store"), information.getUUID().toString());
		assertTrue(analysisDir.exists());
		assertTrue(analysisDir.isDirectory());

		store.removeAnalysis(information.getUUID());

		assertFalse(analysisDir.exists());
	}

	@Test
	public void testCreateAndOpenAndDeleteAnalysis()
			throws AnalysisStoreException {
		AnalysisStore store = AnalysisStoreFactory.getFactory().getInstance();
		File sourceDirectory = new File(System.getProperty("user.dir"));
		AnalysisProject analysis = store
				.createAnalysis(new AnalysisProjectSettings(
						"Name",
						"Description",
						new TestFileSearchConfiguration(),
						new DirectoryRepositoryLocation("name", sourceDirectory)));

		AnalysisProjectInformation information = analysis.getInformation();
		assertNotNull(information.getUUID());
		assertNotNull(information.getCreationTime());
		assertEquals("Name", analysis.getSettings().getName());
		assertEquals("Description", analysis.getSettings().getDescription());

		File analysisDir = new File(new File(System.getProperty("user.home"),
				".code.analysis.store"), information.getUUID().toString());
		assertTrue(analysisDir.exists());
		assertTrue(analysisDir.isDirectory());

		AnalysisProject loadedAnalysis = store.loadAnalysis(information
				.getUUID());

		assertEquals(analysis.getInformation(), loadedAnalysis.getInformation());
		assertEquals(analysis.getSettings(), loadedAnalysis.getSettings());

		store.removeAnalysis(information.getUUID());

		assertFalse(analysisDir.exists());
	}
}
