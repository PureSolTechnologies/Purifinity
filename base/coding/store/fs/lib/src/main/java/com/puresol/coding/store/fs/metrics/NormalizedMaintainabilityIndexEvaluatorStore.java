package com.puresol.coding.store.fs.metrics;

import java.io.File;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.evaluation.api.MetricDirectoryResults;
import com.puresol.coding.evaluation.api.MetricFileResults;
import com.puresol.coding.metrics.normmaint.NormalizedMaintainabilityIndexFileResults;
import com.puresol.coding.store.fs.evaluation.AbstractEvaluatorStore;
import com.puresol.utils.HashId;

public class NormalizedMaintainabilityIndexEvaluatorStore extends
		AbstractEvaluatorStore {

	@Override
	protected Class<?> getFileResultClass() {
		return NormalizedMaintainabilityIndexFileResults.class;
	}

	@Override
	protected Class<?> getDirectoryResultClass() {
		return NormalizedMaintainabilityIndexFileResults.class;
	}

	@Override
	protected Class<?> getProjectResultClass() {
		return NormalizedMaintainabilityIndexFileResults.class;
	}

	@Override
	public void storeFileResults(HashId hashId, MetricFileResults results) {
		File file = getFileResultsFile(hashId);
		persist(results, file);
	}

	@Override
	public void storeDirectoryResults(HashId hashId,
			MetricDirectoryResults results) {
	}

	@Override
	public void storeProjectResults(AnalysisRun analysisRun,
			MetricDirectoryResults results) {
	}

	@Override
	public MetricFileResults readFileResults(HashId hashId) {
		if (hasFileResults(hashId)) {
			File file = getFileResultsFile(hashId);
			return restore(file,
					NormalizedMaintainabilityIndexFileResults.class);
		} else {
			return null;
		}
	}

	@Override
	public MetricDirectoryResults readDirectoryResults(HashId hashId) {
		return null;
	}

	@Override
	public MetricDirectoryResults readProjectResults(AnalysisRun analysisRun) {
		return null;
	}
}
