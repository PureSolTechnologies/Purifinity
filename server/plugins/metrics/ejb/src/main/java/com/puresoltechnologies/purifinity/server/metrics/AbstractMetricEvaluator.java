package com.puresoltechnologies.purifinity.server.metrics;

import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricFileResults;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluator;

public abstract class AbstractMetricEvaluator extends AbstractEvaluator {

    private static final long serialVersionUID = -3527239400633451795L;

    public AbstractMetricEvaluator(String id, String name, String description) {
	super(id, name, description);
    }

    abstract protected Class<? extends MetricFileResults> getFileResultsClass();

    abstract protected Class<? extends MetricDirectoryResults> getDirectoryResultsClass();

    @Override
    protected MetricFileResults readFileResults(HashId hashId)
	    throws EvaluationStoreException {
	return getEvaluatorStore().readFileResults(getFileResultsClass(),
		hashId);
    }

    @Override
    protected boolean hasFileResults(HashId hashId)
	    throws EvaluationStoreException {
	return getEvaluatorStore()
		.hasFileResults(getFileResultsClass(), hashId);
    }

    @Override
    protected void storeFileResults(AnalysisRun analysisRun,
	    CodeAnalysis fileAnalysis, AbstractEvaluator evaluator,
	    MetricFileResults fileResults) throws EvaluationStoreException {
	getEvaluatorStore().storeFileResults(analysisRun, fileAnalysis,
		evaluator, fileResults);
    }

    @Override
    protected MetricDirectoryResults readDirectoryResults(HashId hashId)
	    throws EvaluationStoreException {
	return getEvaluatorStore().readDirectoryResults(
		getDirectoryResultsClass(), hashId);
    }

    @Override
    protected boolean hasDirectoryResults(HashId hashId)
	    throws EvaluationStoreException {
	return getEvaluatorStore().hasDirectoryResults(
		getDirectoryResultsClass(), hashId);
    }

    @Override
    protected void storeDirectoryResults(AnalysisRun analysisRun,
	    AnalysisFileTree directoryNode, AbstractEvaluator evaluator,
	    MetricDirectoryResults directoryResults)
	    throws EvaluationStoreException {
	getEvaluatorStore().storeDirectoryResults(analysisRun, directoryNode,
		evaluator, directoryResults);
    }

}
