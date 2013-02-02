package com.puresol.coding.analysis;

import java.io.File;

public class AnalyzedFileHelper {

    public static File getPropertyFile(File storageDirectory) {
	return new File(storageDirectory, "analysis.properties");
    }

    public static File getAnalyzerFile(File storageDirectory) {
	return new File(storageDirectory, "analyzer.persist");
    }

}
