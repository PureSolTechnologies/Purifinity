package com.puresol.coding.metrics.sloc;

import java.io.File;

import com.puresol.coding.analysis.impl.AnalysisRun;
import com.puresol.coding.analysis.impl.evaluation.DirectoryResults;
import com.puresol.coding.analysis.impl.evaluation.FileResults;
import com.puresol.coding.analysis.impl.evaluation.ProjectResults;
import com.puresol.coding.metrics.AbstractEvaluatorStore;
import com.puresol.utils.HashId;

public class SLOCEvaluatorStore extends AbstractEvaluatorStore {

    @Override
    protected File getFileResultsFile(HashId hashId) {
	return getFileResultsFile(hashId, SLOCFileResults.class);
    }

    @Override
    protected File getDirectoryResultsFile(HashId hashId) {
	return getDirectoryResultsFile(hashId, SLOCDirectoryResults.class);
    }

    @Override
    protected File getProjectResultsFile(AnalysisRun analysisRun) {
	return getProjectResultsFile(analysisRun, SLOCProjectResults.class);
    }

    @Override
    public void storeFileResults(HashId hashId, FileResults results) {
	File file = getFileResultsFile(hashId);
	persist(results, file);
    }

    @Override
    public void storeDirectoryResults(HashId hashId, DirectoryResults results) {
	File file = getDirectoryResultsFile(hashId);
	persist(results, file);
    }

    @Override
    public void storeProjectResults(AnalysisRun analysisRun,
	    ProjectResults results) {
	File file = getProjectResultsFile(analysisRun);
	persist(results, file);
    }

    @Override
    public FileResults readFileResults(HashId hashId) {
	File file = getFileResultsFile(hashId);
	return restore(file, SLOCFileResults.class);
    }

    @Override
    public DirectoryResults readDirectoryResults(HashId hashId) {
	File file = getDirectoryResultsFile(hashId);
	return restore(file, SLOCDirectoryResults.class);
    }

    @Override
    public ProjectResults readProjectResults(AnalysisRun analysisRun) {
	File file = getProjectResultsFile(analysisRun);
	return restore(file, SLOCProjectResults.class);
    }
}
