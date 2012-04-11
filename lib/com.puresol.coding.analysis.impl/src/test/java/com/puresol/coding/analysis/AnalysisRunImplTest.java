package com.puresol.coding.analysis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.puresol.coding.analysis.api.Analysis;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalysisRunInformation;
import com.puresol.coding.analysis.api.AnalysisSettings;
import com.puresol.coding.analysis.api.AnalysisStore;
import com.puresol.coding.analysis.api.AnalyzedFile;
import com.puresol.coding.analysis.api.DirectoryStoreException;
import com.puresol.trees.FileTree;
import com.puresol.utils.HashAlgorithm;
import com.puresol.utils.HashId;

public class AnalysisRunImplTest {

    private static AnalysisStore analysisStore;
    private static Analysis analysis;

    @BeforeClass
    public static void initialize() throws DirectoryStoreException {
	analysisStore = new AnalysisStoreImpl();
	analysis = analysisStore.createAnalysis(new AnalysisSettings("Name",
		"Description", new TestFileSearchConfiguration(), new File(
			"src")));
	assertNotNull(analysis);
    }

    @Test
    public void test() throws DirectoryStoreException {
	AnalysisRun analysisRun = analysis.runAnalysis();
	assertNotNull(analysisRun);
	List<AnalyzedFile> analyzedFiles = analysisRun.getAnalyzedFiles();
	assertNotNull(analyzedFiles);
	List<File> failedFiles = analysisRun.getFailedFiles();
	assertNotNull(failedFiles);
	FileTree fileTree = analysisRun.getFileTree();
	assertNotNull(fileTree);
	AnalysisRunInformation information = analysisRun.getInformation();
	assertNotNull(information);
    }

    @Test
    public void testGetFileStoreDirectory() {
	File fileStoreDirectory = FileStoreImpl.getFileStoreDirectory(new File(
		"test/test2"), new HashId(HashAlgorithm.SHA256, "1234567890"));
	assertEquals("test/test2/files/12/34/567890",
		fileStoreDirectory.getPath());
    }

    @AfterClass
    public static void destroy() throws DirectoryStoreException {
	assertNotNull(analysisStore);
	assertNotNull(analysis);
	analysisStore.removeAnalysis(analysis.getInformation().getUUID());
	analysis = null;
	analysisStore = null;
    }

}
