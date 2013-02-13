package com.puresol.coding.richclient.application.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.puresol.coding.analysis.api.Analysis;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalysisRunInformation;
import com.puresol.coding.analysis.api.AnalysisSettings;
import com.puresol.coding.analysis.api.AnalysisStore;
import com.puresol.coding.analysis.api.AnalyzedCode;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.analysis.api.ModuleStoreException;
import com.puresol.coding.analysis.api.TestFileSearchConfiguration;
import com.puresol.coding.analysis.filestore.AnalysisStoreImpl;
import com.puresol.coding.analysis.filestore.CodeStoreImpl;
import com.puresol.uhura.source.CodeLocation;
import com.puresol.utils.HashAlgorithm;
import com.puresol.utils.HashId;

public class AnalysisRunImplTest {

	private static AnalysisStore analysisStore;
	private static Analysis analysis;

	@BeforeClass
	public static void initialize() throws ModuleStoreException {
		analysisStore = new AnalysisStoreImpl();
		analysis = analysisStore.createAnalysis(new AnalysisSettings("Name",
				"Description", new TestFileSearchConfiguration(),
				new DirectoryRepositoryLocation("src", new File("src"))));
		assertNotNull(analysis);
	}

	@Test
	@Ignore("For test, we do not have a bundle context!")
	public void test() throws Exception {
		AnalysisRun analysisRun = analysis.runAnalysis();
		assertNotNull(analysisRun);
		List<AnalyzedCode> analyzedFiles = analysisRun.getAnalyzedCodes();
		assertNotNull(analyzedFiles);
		List<CodeLocation> failedFiles = analysisRun.getFailedCodeLocations();
		assertNotNull(failedFiles);
		HashIdFileTree fileTree = analysisRun.getFileTree();
		assertNotNull(fileTree);
		AnalysisRunInformation information = analysisRun.getInformation();
		assertNotNull(information);
	}

	@Test
	public void testGetFileStoreDirectory() {
		File fileStoreDirectory = CodeStoreImpl.getFileDirectory(new HashId(
				HashAlgorithm.SHA256, "1234567890"));
		assertTrue(fileStoreDirectory.getPath().endsWith("/12/34/567890"));
	}

	@AfterClass
	public static void destroy() throws ModuleStoreException {
		assertNotNull(analysisStore);
		assertNotNull(analysis);
		analysisStore.removeAnalysis(analysis.getInformation().getUUID());
		analysis = null;
		analysisStore = null;
	}

}
