package com.puresol.coding.store.fs.metrics;

import java.io.File;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.evaluation.api.MetricResults;
import com.puresol.coding.metrics.cocomo.CoCoMoResults;
import com.puresol.coding.metrics.mccabe.McCabeMetricResults;
import com.puresol.coding.store.fs.evaluation.AbstractEvaluatorStore;
import com.puresol.utils.HashId;

public class McCabeMetricEvaluatorStore extends AbstractEvaluatorStore {

    @Override
    protected Class<?> getFileResultClass() {
	return McCabeMetricResults.class;
    }

    @Override
    protected Class<?> getDirectoryResultClass() {
	return McCabeMetricResults.class;
    }

    @Override
    protected Class<?> getProjectResultClass() {
	return McCabeMetricResults.class;
    }

    @Override
    public void storeFileResults(HashId hashId, MetricResults results) {
	File file = getFileResultsFile(hashId);
	persist(results, file);
    }

    @Override
    public void storeDirectoryResults(HashId hashId, MetricResults results) {
	File file = getDirectoryResultsFile(hashId);
	persist(results, file);
    }

    @Override
    public void storeProjectResults(AnalysisRun analysisRun,
	    MetricResults results) {
	File file = getProjectResultsFile(analysisRun);
	persist(results, file);
    }

    @Override
    public MetricResults readFileResults(HashId hashId) {
	File file = getFileResultsFile(hashId);
	return restore(file, CoCoMoResults.class);
    }

    @Override
    public MetricResults readDirectoryResults(HashId hashId) {
	File file = getDirectoryResultsFile(hashId);
	return restore(file, CoCoMoResults.class);
    }

    @Override
    public MetricResults readProjectResults(AnalysisRun analysisRun) {
	File file = getProjectResultsFile(analysisRun);
	return restore(file, CoCoMoResults.class);
    }
}
