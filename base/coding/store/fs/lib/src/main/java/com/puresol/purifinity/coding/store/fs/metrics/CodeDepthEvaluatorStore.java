package com.puresol.purifinity.coding.store.fs.metrics;

import java.io.File;

import com.puresol.commons.utils.HashId;
import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.evaluation.api.MetricDirectoryResults;
import com.puresol.purifinity.coding.evaluation.api.MetricFileResults;
import com.puresol.purifinity.coding.metrics.codedepth.CodeDepthDirectoryResults;
import com.puresol.purifinity.coding.metrics.codedepth.CodeDepthFileResults;
import com.puresol.purifinity.coding.store.fs.evaluation.AbstractEvaluatorStore;

public class CodeDepthEvaluatorStore extends AbstractEvaluatorStore {

	@Override
	protected Class<?> getFileResultClass() {
		return CodeDepthFileResults.class;
	}

	@Override
	protected Class<?> getDirectoryResultClass() {
		return CodeDepthFileResults.class;
	}

	@Override
	protected Class<?> getProjectResultClass() {
		return CodeDepthFileResults.class;
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
	public CodeDepthFileResults readFileResults(HashId hashId) {
		if (hasFileResults(hashId)) {
			File file = getFileResultsFile(hashId);
			return restore(file, CodeDepthFileResults.class);
		} else {
			return null;
		}
	}

	@Override
	public CodeDepthDirectoryResults readDirectoryResults(HashId hashId) {
		if (hasDirectoryResults(hashId)) {
			File file = getDirectoryResultsFile(hashId);
			return restore(file, CodeDepthDirectoryResults.class);
		} else {
			return null;
		}
	}

	@Override
	public CodeDepthDirectoryResults readProjectResults(AnalysisRun analysisRun) {
		if (hasProjectResults(analysisRun)) {
			File file = getProjectResultsFile(analysisRun);
			return restore(file, CodeDepthDirectoryResults.class);
		} else {
			return null;
		}
	}
}
