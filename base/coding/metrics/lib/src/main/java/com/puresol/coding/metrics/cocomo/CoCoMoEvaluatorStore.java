package com.puresol.coding.metrics.cocomo;

import java.io.File;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.evaluation.DirectoryResults;
import com.puresol.coding.analysis.api.evaluation.FileResults;
import com.puresol.coding.analysis.api.evaluation.ProjectResults;
import com.puresol.coding.metrics.AbstractEvaluatorStore;
import com.puresol.utils.HashId;

public class CoCoMoEvaluatorStore extends AbstractEvaluatorStore {

	@Override
	protected File getFileResultsFile(HashId hashId) {
		return getFileResultsFile(hashId, CoCoMoValueSet.class);
	}

	@Override
	protected File getDirectoryResultsFile(HashId hashId) {
		return getDirectoryResultsFile(hashId, CoCoMoValueSet.class);
	}

	@Override
	protected File getProjectResultsFile(AnalysisRun analysisRun) {
		return getProjectResultsFile(analysisRun, CoCoMoValueSet.class);
	}

	@Override
	public void storeFileResults(HashId hashId, FileResults results) {
		File file = getFileResultsFile(hashId);
		persist(results, file);
	}

	@Override
	public void storeDirectoryResults(HashId hashId, DirectoryResults results) {
		File file = getDirectoryResultsFile(hashId);
		persist(results, file);
	}

	@Override
	public void storeProjectResults(AnalysisRun analysisRun,
			ProjectResults results) {
		File file = getProjectResultsFile(analysisRun);
		persist(results, file);
	}

	@Override
	public FileResults readFileResults(HashId hashId) {
		File file = getFileResultsFile(hashId);
		return restore(file, CoCoMoValueSet.class);
	}

	@Override
	public DirectoryResults readDirectoryResults(HashId hashId) {
		File file = getDirectoryResultsFile(hashId);
		return restore(file, CoCoMoValueSet.class);
	}

	@Override
	public ProjectResults readProjectResults(AnalysisRun analysisRun) {
		File file = getProjectResultsFile(analysisRun);
		return restore(file, CoCoMoValueSet.class);
	}
}
