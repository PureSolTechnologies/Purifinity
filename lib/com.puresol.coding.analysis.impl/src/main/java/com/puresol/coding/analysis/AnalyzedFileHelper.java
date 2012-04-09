package com.puresol.coding.analysis;

import java.io.File;

import com.puresol.coding.analysis.api.AnalyzedFile;

public class AnalyzedFileHelper {

    public static File getPropertyFile(File targetDirectory,
	    AnalyzedFile analyzedFile) {
	return new File(new File(targetDirectory, analyzedFile.getFile()
		.getPath()), "analysis.properties");
    }

    public static File getAnalyzerFile(File targetDirectory,
	    AnalyzedFile analyzedFile) {
	return new File(new File(targetDirectory, analyzedFile.getFile()
		.getPath()), "analyzer.persist");
    }

}
