package com.puresol.coding.store.fs.metrics;

import java.io.File;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.evaluation.api.MetricResults;
import com.puresol.coding.metrics.codedepth.CodeDepthResults;
import com.puresol.coding.store.fs.evaluation.AbstractEvaluatorStore;
import com.puresol.utils.HashId;

public class CodeDepthEvaluatorStore extends AbstractEvaluatorStore {

	@Override
	protected File getFileResultsFile(HashId hashId) {
		return getFileResultsFile(hashId, CodeDepthResults.class);
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
			MetricResults results) {
	}

	@Override
	public MetricResults readFileResults(HashId hashId) {
		File file = getFileResultsFile(hashId);
		return restore(file, CodeDepthResults.class);
	}

	@Override
	public MetricResults readDirectoryResults(HashId hashId) {
		return null;
	}

	@Override
	public MetricResults readProjectResults(AnalysisRun analysisRun) {
		return null;
	}
}
