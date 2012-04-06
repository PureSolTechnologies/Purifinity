package com.puresol.coding.analysis;

import java.io.File;

import com.puresol.coding.analysis.api.AnalyzedFile;

public class AnalyzedFileHelper {

    public static File getPropertyFile(AnalyzedFile analyzedFile) {
	return new File(analyzedFile.getFileDirectory(), "analysis.properties");
    }

    public static File getAnalyzerFile(AnalyzedFile analyzedFile) {
	return new File(analyzedFile.getFileDirectory(), "analyzer.persist");
    }

}
