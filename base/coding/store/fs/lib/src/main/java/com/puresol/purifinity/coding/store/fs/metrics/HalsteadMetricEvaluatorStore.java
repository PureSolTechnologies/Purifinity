package com.puresol.purifinity.coding.store.fs.metrics;

import java.io.File;

import com.puresol.commons.utils.HashId;
import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.evaluation.api.MetricDirectoryResults;
import com.puresol.purifinity.coding.evaluation.api.MetricFileResults;
import com.puresol.purifinity.coding.metrics.halstead.HalsteadMetricFileResults;
import com.puresol.purifinity.coding.metrics.sloc.SLOCFileResults;
import com.puresol.purifinity.coding.store.fs.evaluation.AbstractEvaluatorStore;

public class HalsteadMetricEvaluatorStore extends AbstractEvaluatorStore {

	@Override
	protected Class<?> getFileResultClass() {
		return HalsteadMetricFileResults.class;
	}

	@Override
	protected Class<?> getDirectoryResultClass() {
		return HalsteadMetricFileResults.class;
	}

	@Override
	protected Class<?> getProjectResultClass() {
		return HalsteadMetricFileResults.class;
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
			return restore(file, SLOCFileResults.class);
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
