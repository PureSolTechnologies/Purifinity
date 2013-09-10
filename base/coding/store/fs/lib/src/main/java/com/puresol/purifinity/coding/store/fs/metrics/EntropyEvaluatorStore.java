package com.puresol.purifinity.coding.store.fs.metrics;

import java.io.File;

import com.puresol.commons.utils.HashId;
import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.evaluation.api.MetricDirectoryResults;
import com.puresol.purifinity.coding.evaluation.api.MetricFileResults;
import com.puresol.purifinity.coding.metrics.entropy.EntropyDirectoryResults;
import com.puresol.purifinity.coding.metrics.entropy.EntropyFileResults;
import com.puresol.purifinity.coding.store.fs.evaluation.AbstractEvaluatorStore;

public class EntropyEvaluatorStore extends AbstractEvaluatorStore {

	@Override
	protected Class<?> getFileResultClass() {
		return EntropyFileResults.class;
	}

	@Override
	protected Class<?> getDirectoryResultClass() {
		return EntropyFileResults.class;
	}

	@Override
	protected Class<?> getProjectResultClass() {
		return EntropyFileResults.class;
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
	public EntropyFileResults readFileResults(HashId hashId) {
		if (hasFileResults(hashId)) {
			File file = getFileResultsFile(hashId);
			return restore(file, EntropyFileResults.class);
		} else {
			return null;
		}
	}

	@Override
	public EntropyDirectoryResults readDirectoryResults(HashId hashId) {
		if (hasDirectoryResults(hashId)) {
			File file = getDirectoryResultsFile(hashId);
			return restore(file, EntropyDirectoryResults.class);
		} else {
			return null;
		}
	}

	@Override
	public EntropyDirectoryResults readProjectResults(AnalysisRun analysisRun) {
		if (hasProjectResults(analysisRun)) {
			File file = getProjectResultsFile(analysisRun);
			return restore(file, EntropyDirectoryResults.class);
		} else {
			return null;
		}
	}
}
