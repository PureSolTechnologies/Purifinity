package com.puresol.coding.analysis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.puresol.coding.analysis.api.Analysis;
import com.puresol.coding.analysis.api.AnalysisInformation;
import com.puresol.coding.analysis.api.AnalysisSettings;
import com.puresol.coding.analysis.api.AnalysisStore;
import com.puresol.coding.analysis.api.DirectoryRepositoryLocation;
import com.puresol.coding.analysis.api.ModuleStoreException;
import com.puresol.coding.analysis.impl.AnalysisStoreImpl;

public class AnalysisStoreImplTest {

    @Test
    public void testGetAllAnalysisInformation() throws ModuleStoreException {
	AnalysisStore store = new AnalysisStoreImpl();
	List<AnalysisInformation> allAnalysisInformation = store
		.getAllAnalysisInformation();
	assertNotNull(allAnalysisInformation);
	File storeDir = new File(System.getProperty("user.home"),
		".code.analysis.store");
	assertTrue(storeDir.exists());
	assertTrue(storeDir.isDirectory());
    }

    @Test
    public void testCreateAndDeleteAnalysis() throws ModuleStoreException {
	AnalysisStore store = new AnalysisStoreImpl();
	File sourceDirectory = new File(".");
	Analysis analysis = store.createAnalysis(new AnalysisSettings("Name",
		"Description", new TestFileSearchConfiguration(),
		new DirectoryRepositoryLocation("name", sourceDirectory)));

	AnalysisInformation information = analysis.getInformation();
	assertNotNull(information.getUUID());
	assertNotNull(information.getCreationTime());
	assertEquals("Name", information.getName());
	assertEquals("Description", information.getDescription());

	File analysisDir = new File(new File(System.getProperty("user.home"),
		".code.analysis.store"), information.getUUID().toString());
	assertTrue(analysisDir.exists());
	assertTrue(analysisDir.isDirectory());

	store.removeAnalysis(information.getUUID());

	assertFalse(analysisDir.exists());
    }

    @Test
    public void testCreateAndOpenAndDeleteAnalysis()
	    throws ModuleStoreException {
	AnalysisStore store = new AnalysisStoreImpl();
	File sourceDirectory = new File(System.getProperty("user.dir"));
	Analysis analysis = store.createAnalysis(new AnalysisSettings("Name",
		"Description", new TestFileSearchConfiguration(),
		new DirectoryRepositoryLocation("name", sourceDirectory)));

	AnalysisInformation information = analysis.getInformation();
	assertNotNull(information.getUUID());
	assertNotNull(information.getCreationTime());
	assertEquals("Name", information.getName());
	assertEquals("Description", information.getDescription());

	File analysisDir = new File(new File(System.getProperty("user.home"),
		".code.analysis.store"), information.getUUID().toString());
	assertTrue(analysisDir.exists());
	assertTrue(analysisDir.isDirectory());

	Analysis loadedAnalysis = store.loadAnalysis(information.getUUID());

	assertEquals(analysis.getInformation(), loadedAnalysis.getInformation());
	assertEquals(analysis.getSettings(), loadedAnalysis.getSettings());

	store.removeAnalysis(information.getUUID());

	assertFalse(analysisDir.exists());
    }
}
