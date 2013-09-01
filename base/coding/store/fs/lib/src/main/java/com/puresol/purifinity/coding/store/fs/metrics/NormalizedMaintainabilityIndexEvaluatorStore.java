package com.puresol.purifinity.coding.store.fs.metrics;

import java.io.File;

import com.puresol.commons.utils.HashId;
import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.evaluation.api.MetricDirectoryResults;
import com.puresol.purifinity.coding.evaluation.api.MetricFileResults;
import com.puresol.purifinity.coding.metrics.normmaint.NormalizedMaintainabilityIndexFileResults;
import com.puresol.purifinity.coding.store.fs.evaluation.AbstractEvaluatorStore;

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
	public NormalizedMaintainabilityIndexFileResults readFileResults(
			HashId hashId) {
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
