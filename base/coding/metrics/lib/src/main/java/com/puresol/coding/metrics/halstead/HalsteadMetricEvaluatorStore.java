package com.puresol.coding.metrics.halstead;

import java.io.File;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.evaluation.api.DirectoryResults;
import com.puresol.coding.evaluation.api.FileResults;
import com.puresol.coding.evaluation.api.ProjectResults;
import com.puresol.coding.evaluation.impl.AbstractEvaluatorStore;
import com.puresol.coding.metrics.sloc.SLOCFileResults;
import com.puresol.utils.HashId;

public class HalsteadMetricEvaluatorStore extends AbstractEvaluatorStore {

	@Override
	protected File getFileResultsFile(HashId hashId) {
		return getFileResultsFile(hashId, HalsteadMetricFileResults.class);
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
		return restore(file, SLOCFileResults.class);
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
