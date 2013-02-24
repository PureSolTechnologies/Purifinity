package com.puresol.coding.store.fs.analysis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.puresol.coding.analysis.api.AnalysisProject;
import com.puresol.coding.analysis.api.AnalysisProjectInformation;
import com.puresol.coding.analysis.api.AnalysisProjectSettings;
import com.puresol.coding.analysis.api.AnalysisStore;
import com.puresol.coding.analysis.api.AnalysisStoreException;
import com.puresol.coding.analysis.api.AnalysisStoreFactory;
import com.puresol.coding.analysis.api.DirectoryRepositoryLocation;
import com.puresol.coding.analysis.test.TestFileSearchConfiguration;

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
