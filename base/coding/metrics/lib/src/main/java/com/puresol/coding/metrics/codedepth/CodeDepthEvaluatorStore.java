package com.puresol.coding.metrics.codedepth;

import java.io.File;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.evaluation.api.DirectoryResults;
import com.puresol.coding.evaluation.api.FileResults;
import com.puresol.coding.evaluation.api.ProjectResults;
import com.puresol.coding.metrics.AbstractEvaluatorStore;
import com.puresol.utils.HashId;

public class CodeDepthEvaluatorStore extends AbstractEvaluatorStore {

	@Override
	protected File getFileResultsFile(HashId hashId) {
		return getFileResultsFile(hashId, CodeDepthFileResults.class);
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
	public void storeFileResults(HashId hashId, FileResults results) {
		File file = getFileResultsFile(hashId);
		persist(results, file);
	}

	@Override
	public void storeDirectoryResults(HashId hashId, DirectoryResults results) {
	}

	@Override
	public void storeProjectResults(AnalysisRun analysisRun,
			ProjectResults results) {
	}

	@Override
	public FileResults readFileResults(HashId hashId) {
		File file = getFileResultsFile(hashId);
		return restore(file, CodeDepthFileResults.class);
	}

	@Override
	public DirectoryResults readDirectoryResults(HashId hashId) {
		return null;
	}

	@Override
	public ProjectResults readProjectResults(AnalysisRun analysisRun) {
		return null;
	}
}
