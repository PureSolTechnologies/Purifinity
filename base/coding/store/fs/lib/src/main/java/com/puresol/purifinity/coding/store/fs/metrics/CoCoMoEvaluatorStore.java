package com.puresol.purifinity.coding.store.fs.metrics;

import java.io.File;

import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.evaluation.api.MetricDirectoryResults;
import com.puresol.purifinity.coding.evaluation.api.MetricFileResults;
import com.puresol.purifinity.coding.metrics.cocomo.CoCoMoDirectoryResults;
import com.puresol.purifinity.coding.metrics.cocomo.CoCoMoFileResults;
import com.puresol.purifinity.coding.metrics.cocomo.CoCoMoResults;
import com.puresol.purifinity.coding.store.fs.evaluation.AbstractEvaluatorStore;
import com.puresol.purifinity.utils.HashId;

public class CoCoMoEvaluatorStore extends AbstractEvaluatorStore {

	@Override
	protected Class<?> getFileResultClass() {
		return CoCoMoResults.class;
	}

	@Override
	protected Class<?> getDirectoryResultClass() {
		return CoCoMoResults.class;
	}

	@Override
	protected Class<?> getProjectResultClass() {
		return CoCoMoResults.class;
	}

	@Override
	public void storeFileResults(HashId hashId, MetricFileResults results) {
		File file = getFileResultsFile(hashId);
		persist(results, file);
	}

	@Override
	public void storeDirectoryResults(HashId hashId,
			MetricDirectoryResults results) {
		File file = getDirectoryResultsFile(hashId);
		persist(results, file);
	}

	@Override
	public void storeProjectResults(AnalysisRun analysisRun,
			MetricDirectoryResults results) {
		File file = getProjectResultsFile(analysisRun);
		persist(results, file);
	}

	@Override
	public MetricFileResults readFileResults(HashId hashId) {
		if (hasFileResults(hashId)) {
			File file = getFileResultsFile(hashId);
			return restore(file, CoCoMoFileResults.class);
		} else {
			return null;
		}
	}

	@Override
	public MetricDirectoryResults readDirectoryResults(HashId hashId) {
		if (hasDirectoryResults(hashId)) {
			File file = getDirectoryResultsFile(hashId);
			return restore(file, CoCoMoDirectoryResults.class);
		} else {
			return null;
		}
	}

	@Override
	public MetricDirectoryResults readProjectResults(AnalysisRun analysisRun) {
		File file = getProjectResultsFile(analysisRun);
		return restore(file, CoCoMoDirectoryResults.class);
	}
}