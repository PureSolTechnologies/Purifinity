package com.puresol.coding.store.fs.metrics;

import java.io.File;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.evaluation.api.MetricResults;
import com.puresol.coding.metrics.halstead.HalsteadMetricResults;
import com.puresol.coding.metrics.sloc.SLOCResults;
import com.puresol.coding.store.fs.evaluation.AbstractEvaluatorStore;
import com.puresol.utils.HashId;

public class HalsteadMetricEvaluatorStore extends AbstractEvaluatorStore {

    @Override
    protected Class<?> getFileResultClass() {
	return HalsteadMetricResults.class;
    }

    @Override
    protected Class<?> getDirectoryResultClass() {
	return HalsteadMetricResults.class;
    }

    @Override
    protected Class<?> getProjectResultClass() {
	return HalsteadMetricResults.class;
    }

    @Override
    public void storeFileResults(HashId hashId, MetricResults results) {
	File file = getFileResultsFile(hashId);
	persist(results, file);
    }

    @Override
    public void storeDirectoryResults(HashId hashId, MetricResults results) {
    }

    @Override
    public void storeProjectResults(AnalysisRun analysisRun,
	    MetricResults results) {
    }

    @Override
    public MetricResults readFileResults(HashId hashId) {
	if (hasFileResults(hashId)) {
	    File file = getFileResultsFile(hashId);
	    return restore(file, SLOCResults.class);
	} else {
	    return null;
	}
    }

    @Override
    public MetricResults readDirectoryResults(HashId hashId) {
	return null;
    }

    @Override
    public MetricResults readProjectResults(AnalysisRun analysisRun) {
	return null;
    }
}
