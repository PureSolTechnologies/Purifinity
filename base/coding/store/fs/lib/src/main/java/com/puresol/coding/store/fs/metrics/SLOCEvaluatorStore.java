package com.puresol.coding.store.fs.metrics;

import java.io.File;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.evaluation.api.MetricResults;
import com.puresol.coding.evaluation.api.ProjectResults;
import com.puresol.coding.metrics.sloc.SLOCProjectResults;
import com.puresol.coding.metrics.sloc.SLOCResults;
import com.puresol.coding.store.fs.evaluation.AbstractEvaluatorStore;
import com.puresol.utils.HashId;

public class SLOCEvaluatorStore extends AbstractEvaluatorStore {

	@Override
	protected File getFileResultsFile(HashId hashId) {
		return getFileResultsFile(hashId, SLOCResults.class);
	}

	@Override
	protected File getDirectoryResultsFile(HashId hashId) {
		return getDirectoryResultsFile(hashId, SLOCResults.class);
	}

	@Override
	protected File getProjectResultsFile(AnalysisRun analysisRun) {
		return getProjectResultsFile(analysisRun, SLOCProjectResults.class);
	}

	@Override
	public void storeFileResults(HashId hashId, MetricResults results) {
		File file = getFileResultsFile(hashId);
		persist(results, file);
	}

	@Override
	public void storeDirectoryResults(HashId hashId, MetricResults results) {
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
	public MetricResults readFileResults(HashId hashId) {
		File file = getFileResultsFile(hashId);
		return restore(file, SLOCResults.class);
	}

	@Override
	public MetricResults readDirectoryResults(HashId hashId) {
		File file = getDirectoryResultsFile(hashId);
		return restore(file, SLOCResults.class);
	}

	@Override
	public ProjectResults readProjectResults(AnalysisRun analysisRun) {
		File file = getProjectResultsFile(analysisRun);
		return restore(file, SLOCProjectResults.class);
	}
}
