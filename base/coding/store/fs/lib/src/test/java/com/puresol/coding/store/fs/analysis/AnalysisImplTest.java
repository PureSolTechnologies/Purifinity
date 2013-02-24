package com.puresol.coding.store.fs.analysis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.io.File;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.puresol.coding.analysis.api.AnalysisProject;
import com.puresol.coding.analysis.api.AnalysisProjectInformation;
import com.puresol.coding.analysis.api.AnalysisProjectSettings;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalysisRunInformation;
import com.puresol.coding.analysis.api.AnalysisStore;
import com.puresol.coding.analysis.api.AnalysisStoreException;
import com.puresol.coding.analysis.api.DirectoryRepositoryLocation;
import com.puresol.coding.analysis.test.TestFileSearchConfiguration;

public class AnalysisImplTest {

    private static AnalysisStore analysisStore;
    private static AnalysisProject analysis;

    @BeforeClass
    public static void initialize() throws AnalysisStoreException {
	analysisStore = new AnalysisStoreImpl();
	analysis = analysisStore.createAnalysis(new AnalysisProjectSettings(
		"Name", "Description", new TestFileSearchConfiguration(),
		new DirectoryRepositoryLocation(".", new File("."))));
	assertNotNull(analysis);
    }

    @Test
    public void test() throws AnalysisStoreException {
	List<AnalysisRunInformation> allRunInformation = analysis
		.getAllRunInformation();
	assertNotNull(allRunInformation);
    }

    @Test
    public void testUpdateSettings() throws AnalysisStoreException {
	AnalysisProjectInformation oldInformation = analysis.getInformation();
	AnalysisProjectSettings settingsForUpdate = new AnalysisProjectSettings(
		"Name2", "Description2", new TestFileSearchConfiguration(),
		new DirectoryRepositoryLocation("/", new File("/")));

	analysis.updateSettings(settingsForUpdate);

	AnalysisProjectInformation newInformation = analysis.getInformation();
	AnalysisProjectSettings newSettings = analysis.getSettings();

	/* UUID and creation time must not to be changed! */
	assertNotSame(oldInformation, newInformation);
	assertEquals(oldInformation.getUUID(), newInformation.getUUID());
	assertEquals(oldInformation.getCreationTime(),
		newInformation.getCreationTime());
	/* name and description are updated */

	assertEquals("Name2", newSettings.getName());
	assertEquals("Description2", newSettings.getDescription());
	assertEquals("/", newSettings.getRepositoryLocation().getName());
    }

    @Test
    @Ignore("For tests, we do not have a bundle context!")
    public void testCreateRun() throws Exception {
	AnalysisRun analysisRun = analysis.createAnalysisRun();
	assertNotNull(analysisRun);
	AnalysisRun run = analysis.loadLastAnalysisRun();
	assertNotNull(run);
    }

    @AfterClass
    public static void destroy() throws AnalysisStoreException {
	assertNotNull(analysisStore);
	assertNotNull(analysis);
	analysisStore.removeAnalysis(analysis.getInformation().getUUID());
    }

}
