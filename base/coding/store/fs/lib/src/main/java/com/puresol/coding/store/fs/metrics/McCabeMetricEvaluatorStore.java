package com.puresol.coding.store.fs.metrics;

import java.io.File;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.evaluation.api.MetricDirectoryResults;
import com.puresol.coding.evaluation.api.MetricFileResults;
import com.puresol.coding.metrics.mccabe.McCabeMetricDirectoryResults;
import com.puresol.coding.metrics.mccabe.McCabeMetricFileResults;
import com.puresol.coding.store.fs.evaluation.AbstractEvaluatorStore;
import com.puresol.utils.HashId;

public class McCabeMetricEvaluatorStore extends AbstractEvaluatorStore {

	@Override
	protected Class<?> getFileResultClass() {
		return McCabeMetricFileResults.class;
	}

	@Override
	protected Class<?> getDirectoryResultClass() {
		return McCabeMetricFileResults.class;
	}

	@Override
	protected Class<?> getProjectResultClass() {
		return McCabeMetricFileResults.class;
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
			return restore(file, McCabeMetricFileResults.class);
		} else {
			return null;
		}
	}

	@Override
	public MetricDirectoryResults readDirectoryResults(HashId hashId) {
		if (hasDirectoryResults(hashId)) {
			File file = getDirectoryResultsFile(hashId);
			return restore(file, McCabeMetricDirectoryResults.class);
		} else {
			return null;
		}
	}

	@Override
	public MetricDirectoryResults readProjectResults(AnalysisRun analysisRun) {
		File file = getProjectResultsFile(analysisRun);
		return restore(file, McCabeMetricDirectoryResults.class);
	}
}
