package com.puresol.purifinity.coding.store.fs.metrics;

import java.io.File;

import com.puresol.commons.utils.HashId;
import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.evaluation.api.MetricDirectoryResults;
import com.puresol.purifinity.coding.evaluation.api.MetricFileResults;
import com.puresol.purifinity.coding.metrics.mccabe.McCabeMetricDirectoryResults;
import com.puresol.purifinity.coding.metrics.mccabe.McCabeMetricFileResults;
import com.puresol.purifinity.coding.store.fs.evaluation.AbstractEvaluatorStore;

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
	public McCabeMetricFileResults readFileResults(HashId hashId) {
		if (hasFileResults(hashId)) {
			File file = getFileResultsFile(hashId);
			return restore(file, McCabeMetricFileResults.class);
		} else {
			return null;
		}
	}

	@Override
	public McCabeMetricDirectoryResults readDirectoryResults(HashId hashId) {
		if (hasDirectoryResults(hashId)) {
			File file = getDirectoryResultsFile(hashId);
			return restore(file, McCabeMetricDirectoryResults.class);
		} else {
			return null;
		}
	}

	@Override
	public McCabeMetricDirectoryResults readProjectResults(
			AnalysisRun analysisRun) {
		if (hasProjectResults(analysisRun)) {
			File file = getProjectResultsFile(analysisRun);
			return restore(file, McCabeMetricDirectoryResults.class);
		} else {
			return null;
		}
	}
}
