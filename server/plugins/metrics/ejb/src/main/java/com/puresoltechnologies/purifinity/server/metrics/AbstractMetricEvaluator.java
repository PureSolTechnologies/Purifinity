package com.puresoltechnologies.purifinity.server.metrics;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorType;
import com.puresoltechnologies.purifinity.evaluation.api.metrics.MetricEvaluator;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.FileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericDirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericFileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericProjectMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.ProjectMetrics;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluator;
import com.puresoltechnologies.versioning.Version;

/**
 * This is an abstract implementation of special metric evaluator.
 * 
 * <b>Extending implementations must not have mutable state!</b>
 * 
 * @author Rick-Rainer Ludwig
 *
 */
public abstract class AbstractMetricEvaluator extends AbstractEvaluator
	implements MetricEvaluator {

    public AbstractMetricEvaluator(String id, String name, Version version,
	    String description) {
	super(id, name, version, EvaluatorType.METRICS, description);
    }

    @Override
    protected FileMetrics readFileResults(HashId hashId)
	    throws EvaluationStoreException {
	return getEvaluatorStore().readFileResults(hashId,
		getInformation().getId());
    }

    @Override
    protected boolean hasFileResults(HashId hashId)
	    throws EvaluationStoreException {
	return getEvaluatorStore().hasFileResults(hashId,
		getInformation().getId());
    }

    @Override
    protected void storeFileResults(AnalysisRun analysisRun,
	    CodeAnalysis fileAnalysis, GenericFileMetrics metrics)
	    throws EvaluationStoreException {
	getEvaluatorStore()
		.storeFileResults(analysisRun, fileAnalysis, metrics);
    }

    @Override
    protected DirectoryMetrics readDirectoryResults(HashId hashId)
	    throws EvaluationStoreException {
	return getEvaluatorStore().readDirectoryResults(hashId,
		getInformation().getId());
    }

    @Override
    protected boolean hasDirectoryResults(HashId hashId)
	    throws EvaluationStoreException {
	return getEvaluatorStore().hasDirectoryResults(hashId,
		getInformation().getId());
    }

    @Override
    protected void storeDirectoryResults(AnalysisRun analysisRun,
	    AnalysisFileTree directoryNode, GenericDirectoryMetrics metrics)
	    throws EvaluationStoreException {
	getEvaluatorStore().storeDirectoryResults(analysisRun, directoryNode,
		metrics);
    }

    @Override
    protected ProjectMetrics readProjectResults(String projectId, long runId)
	    throws EvaluationStoreException {
	return getEvaluatorStore().readProjectResults(projectId, runId,
		getInformation().getId());
    }

    @Override
    protected boolean hasProjectResults(String projectId, long runId)
	    throws EvaluationStoreException {
	return getEvaluatorStore().hasProjectResults(projectId, runId,
		getInformation().getId());
    }

    @Override
    protected void storeProjectResults(AnalysisRun analysisRun,
	    AnalysisFileTree directoryNode, GenericProjectMetrics metrics)
	    throws EvaluationStoreException {
	getEvaluatorStore().storeProjectResults(analysisRun, directoryNode,
		metrics);
    }

}
