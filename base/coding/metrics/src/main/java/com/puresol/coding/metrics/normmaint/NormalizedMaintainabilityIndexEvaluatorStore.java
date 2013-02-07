package com.puresol.coding.metrics.normmaint;

import java.io.File;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.evaluation.DirectoryResults;
import com.puresol.coding.analysis.api.evaluation.FileResults;
import com.puresol.coding.analysis.api.evaluation.ProjectResults;
import com.puresol.coding.metrics.AbstractEvaluatorStore;
import com.puresol.utils.HashId;

public class NormalizedMaintainabilityIndexEvaluatorStore extends
	AbstractEvaluatorStore {

    @Override
    protected File getFileResultsFile(HashId hashId) {
	return getFileResultsFile(hashId,
		NormalizedMaintainabilityIndexFileResults.class);
    }

    @Override
    protected File getDirectoryResultsFile(HashId hashId) {
	return null;
    }

    @Override
    protected File getProjectResultsFile(AnalysisRun analysisRun) {
	return null;
    }

    @Override
    public void storeFileResults(HashId hashId, FileResults results) {
	File file = getFileResultsFile(hashId);
	persist(results, file);
    }

    @Override
    public void storeDirectoryResults(HashId hashId, DirectoryResults results) {
    }

    @Override
    public void storeProjectResults(AnalysisRun analysisRun,
	    ProjectResults results) {
    }

    @Override
    public FileResults readFileResults(HashId hashId) {
	File file = getFileResultsFile(hashId);
	return restore(file, NormalizedMaintainabilityIndexFileResults.class);
    }

    @Override
    public DirectoryResults readDirectoryResults(HashId hashId) {
	return null;
    }

    @Override
    public ProjectResults readProjectResults(AnalysisRun analysisRun) {
	return null;
    }
}
