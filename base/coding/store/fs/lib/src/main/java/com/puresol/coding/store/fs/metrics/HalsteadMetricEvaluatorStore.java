package com.puresol.coding.store.fs.metrics;

import java.io.File;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.evaluation.api.MetricResults;
import com.puresol.coding.evaluation.api.ProjectResults;
import com.puresol.coding.metrics.halstead.HalsteadMetricFileResults;
import com.puresol.coding.metrics.sloc.SLOCResults;
import com.puresol.coding.store.fs.evaluation.AbstractEvaluatorStore;
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
	public void storeFileResults(HashId hashId, MetricResults results) {
		File file = getFileResultsFile(hashId);
		persist(results, file);
	}

	@Override
	public void storeDirectoryResults(HashId hashId, MetricResults results) {
	}

	@Override
	public void storeProjectResults(AnalysisRun analysisRun,
			ProjectResults results) {
	}

	@Override
	public MetricResults readFileResults(HashId hashId) {
		File file = getFileResultsFile(hashId);
		return restore(file, SLOCResults.class);
	}

	@Override
	public MetricResults readDirectoryResults(HashId hashId) {
		return null;
	}

	@Override
	public ProjectResults readProjectResults(AnalysisRun analysisRun) {
		return null;
	}
}
