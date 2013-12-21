package com.puresoltechnologies.purifinity.framework.store.fs.analysis;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.puresoltechnologies.commons.misc.HashAlgorithm;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisStore;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.analysis.api.AnalyzedCode;
import com.puresoltechnologies.purifinity.analysis.api.HashIdFileTree;
import com.puresoltechnologies.purifinity.framework.analysis.impl.AnalysisStoreFactory;
import com.puresoltechnologies.purifinity.framework.analysis.impl.DirectoryRepositoryLocation;
import com.puresoltechnologies.purifinity.framework.analysis.test.TestFileSearchConfiguration;
import com.puresoltechnologies.purifinity.framework.store.fs.analysis.FileStoreImpl;

@Ignore("The services are not reachable here.")
public class AnalysisRunImplTest {

	private static AnalysisStore analysisStore;
	private static AnalysisProject analysis;

	@BeforeClass
	public static void initialize() throws AnalysisStoreException {
		analysisStore = AnalysisStoreFactory.getFactory().getInstance();
		analysis = analysisStore.createAnalysis(new AnalysisProjectSettings(
				"Name", "Description", new TestFileSearchConfiguration(),
				new DirectoryRepositoryLocation("src", new File("src"))));
		assertNotNull(analysis);
	}

	@Test
	@Ignore("For test, we do not have a bundle context!")
	public void test() throws Exception {
		AnalysisRun analysisRun = analysis.createAnalysisRun();
		assertNotNull(analysisRun);
		List<AnalyzedCode> analyzedFiles = analysisRun.getAnalyzedFiles();
		assertNotNull(analyzedFiles);
		List<AnalyzedCode> failedFiles = analysisRun.getFailedFiles();
		assertNotNull(failedFiles);
		HashIdFileTree fileTree = analysisRun.getFileTree();
		assertNotNull(fileTree);
		AnalysisRunInformation information = analysisRun.getInformation();
		assertNotNull(information);
	}

	@Test
	public void testGetFileStoreDirectory() {
		File fileStoreDirectory = FileStoreImpl.getFileDirectory(new HashId(
				HashAlgorithm.SHA256, "1234567890"));
		assertTrue(fileStoreDirectory.getPath().endsWith("/12/34/567890"));
	}

	@AfterClass
	public static void destroy() throws AnalysisStoreException {
		assertNotNull(analysisStore);
		assertNotNull(analysis);
		analysisStore.removeAnalysis(analysis.getInformation().getUUID());
		analysis = null;
		analysisStore = null;
	}

}
