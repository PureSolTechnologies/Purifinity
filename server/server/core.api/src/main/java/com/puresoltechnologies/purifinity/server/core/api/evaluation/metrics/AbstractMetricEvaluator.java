package com.puresoltechnologies.purifinity.server.core.api.evaluation.metrics;

import java.util.Date;
import java.util.List;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.ust.eval.UniversalSyntaxTreeEvaluationException;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
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
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.DirectoryStore;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.DirectoryStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStore;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStoreException;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluator;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;
import com.puresoltechnologies.versioning.Version;

/**
 * This is an abstract implementation of special metric evaluator.
 * 
 * <b>Extending implementations must not have mutable state!</b>
 * 
 * @author Rick-Rainer Ludwig
 *
 */
public abstract class AbstractMetricEvaluator extends AbstractEvaluator implements MetricEvaluator {

    private final EvaluatorMetricsStoreServiceRemote metricStore;

    public AbstractMetricEvaluator(String id, String name, Version version, String description) {
	super(id, name, version, EvaluatorType.METRICS, description);
	metricStore = JndiUtils.createRemoteEJBInstance(EvaluatorMetricsStoreServiceRemote.class,
		EvaluatorMetricsStoreServiceRemote.JNDI_NAME);
    }

    protected EvaluatorMetricsStore getMetricStore() {
	return metricStore;
    }

    /**
     * This method is used to run an evaluation of an analyzed file. This method
     * is called by the run method.
     * 
     * @param analysisRun
     *            is the {@link AnalysisRun} for which the evaluation is to be
     *            run.
     * @param analysis
     *            is the {@link CodeAnalysis} of the file which is to be
     *            evaluated.
     * @return The results are returned as {@link FileMetrics} object.
     * @throws InterruptedException
     *             is thrown if the evaluation was interrupted.
     * @throws UniversalSyntaxTreeEvaluationException
     *             is thrown if the evaluation was aborted by an exceptional
     *             event.
     * @throws EvaluationStoreException
     *             is thrown in cases of issues within evaluation store.
     */
    abstract protected FileMetrics processFile(AnalysisRun analysisRun, CodeAnalysis analysis)
	    throws InterruptedException, UniversalSyntaxTreeEvaluationException, EvaluationStoreException;

    /**
     * This method is used to run an evaluation of an entire directory. This
     * method is called by the run method.
     * 
     * @param analysisRun
     *            is the {@link AnalysisRun} for which the evaluation is to be
     *            run.
     * @param directory
     *            is the {@link AnalysisFileTree} object of the directory to be
     *            evaluated.
     * @return The results are returned as {@link DirectoryMetrics} object.
     * @throws InterruptedException
     *             is thrown if the evaluation was interrupted.
     * @throws EvaluationStoreException
     *             is thrown in cases of issues within evaluation store.
     */
    abstract protected DirectoryMetrics processDirectory(AnalysisRun analysisRun, AnalysisFileTree directory)
	    throws InterruptedException, EvaluationStoreException;

    @Override
    protected void processAsFile(AnalysisRun analysisRun, AnalysisFileTree fileNode, boolean enableReevaluation)
	    throws FileStoreException, InterruptedException, UniversalSyntaxTreeEvaluationException,
	    EvaluationStoreException {
	HashId hashId = fileNode.getHashId();
	FileStore fileStore = getFileStore();
	if (!fileStore.wasAnalyzed(hashId)) {
	    // Files was not analyzed, so we cannot do something here...
	    return;
	}
	List<CodeAnalysis> fileAnalyses = fileStore.loadAnalyzes(hashId);
	for (CodeAnalysis fileAnalysis : fileAnalyses) {
	    if ((!hasFileResults(hashId)) || (enableReevaluation)) {
		AnalysisInformation analysisInformation = fileAnalysis.getAnalysisInformation();
		if (analysisInformation.isSuccessful()) {
		    FileMetrics fileResults = processFile(analysisRun, fileAnalysis);
		    if (fileResults != null) {
			GenericFileMetrics metrics = new GenericFileMetrics(getInformation().getId(),
				getInformation().getVersion(), hashId, fileResults.getSourceCodeLocation(), new Date(),
				fileResults.getParameters(), fileResults.getCodeRangeMetrics());
			storeFileResults(analysisRun, fileAnalysis, metrics);
		    }
		}
	    } else {
		FileMetrics fileResults = readFileResults(hashId);
		if (fileResults != null) {
		    storeMetricsInBigTable(analysisRun, fileAnalysis, fileResults);
		}
	    }
	}
    }

    @Override
    protected void processAsDirectory(AnalysisRun analysisRun, AnalysisFileTree directoryNode,
	    boolean enableReevaluation) throws FileStoreException, InterruptedException,
		    UniversalSyntaxTreeEvaluationException, DirectoryStoreException, EvaluationStoreException {
	HashId hashId = directoryNode.getHashId();
	DirectoryStore directoryStore = getDirectoryStore();
	EvaluatorMetricsStore store = getMetricStore();
	if (directoryStore.isAvailable(hashId)) {
	    for (AnalysisFileTree child : directoryNode.getChildren()) {
		processNode(analysisRun, child, enableReevaluation);
	    }
	    if ((!store.hasDirectoryResults(hashId, getInformation().getId())) || (enableReevaluation)) {
		DirectoryMetrics directoryResults = processDirectory(analysisRun, directoryNode);
		if (directoryResults != null) {
		    GenericDirectoryMetrics metrics = new GenericDirectoryMetrics(getInformation().getId(),
			    getInformation().getVersion(), hashId, new Date(), directoryResults.getParameters(),
			    directoryResults.getValues());
		    storeDirectoryResults(analysisRun, directoryNode, metrics);
		}
	    } else {
		DirectoryMetrics directoryResults = readDirectoryResults(hashId);
		if (directoryResults != null) {
		    storeMetricsInBigTable(analysisRun, directoryNode, directoryResults);
		}
	    }
	}
    }

    protected final void storeMetricsInBigTable(AnalysisRun analysisRun, CodeAnalysis fileAnalysis,
	    FileMetrics fileResults) throws EvaluationStoreException {
	GenericFileMetrics metrics = new GenericFileMetrics(getInformation().getId(), getInformation().getVersion(),
		fileResults.getHashId(), fileResults.getSourceCodeLocation(), new Date(), fileResults.getParameters(),
		fileResults.getCodeRangeMetrics());
	getMetricStore().storeFileMetricsInBigTable(analysisRun, fileAnalysis, metrics);
    }

    protected final void storeMetricsInBigTable(AnalysisRun analysisRun, AnalysisFileTree directoryNode,
	    DirectoryMetrics directoryResults) throws EvaluationStoreException {
	GenericDirectoryMetrics metrics = new GenericDirectoryMetrics(getInformation().getId(),
		getInformation().getVersion(), directoryResults.getHashId(), new Date(),
		directoryResults.getParameters(), directoryResults.getValues());
	getMetricStore().storeDirectoryMetricsInBigTable(analysisRun, directoryNode, metrics);
    }

    @Override
    protected FileMetrics readFileResults(HashId hashId) throws EvaluationStoreException {
	return getMetricStore().readFileResults(hashId, getInformation().getId());
    }

    @Override
    protected boolean hasFileResults(HashId hashId) throws EvaluationStoreException {
	return getMetricStore().hasFileResults(hashId, getInformation().getId());
    }

    @Override
    protected void storeFileResults(AnalysisRun analysisRun, CodeAnalysis fileAnalysis, GenericFileMetrics metrics)
	    throws EvaluationStoreException {
	getMetricStore().storeFileResults(analysisRun, fileAnalysis, metrics);
    }

    @Override
    protected DirectoryMetrics readDirectoryResults(HashId hashId) throws EvaluationStoreException {
	return getMetricStore().readDirectoryResults(hashId, getInformation().getId());
    }

    @Override
    protected boolean hasDirectoryResults(HashId hashId) throws EvaluationStoreException {
	return getMetricStore().hasDirectoryResults(hashId, getInformation().getId());
    }

    @Override
    protected void storeDirectoryResults(AnalysisRun analysisRun, AnalysisFileTree directoryNode,
	    GenericDirectoryMetrics metrics) throws EvaluationStoreException {
	getMetricStore().storeDirectoryResults(analysisRun, directoryNode, metrics);
    }

    @Override
    protected ProjectMetrics readProjectResults(String projectId, long runId) throws EvaluationStoreException {
	return getMetricStore().readProjectResults(projectId, runId, getInformation().getId());
    }

    @Override
    protected boolean hasProjectResults(String projectId, long runId) throws EvaluationStoreException {
	return getMetricStore().hasProjectResults(projectId, runId, getInformation().getId());
    }

    @Override
    protected void storeProjectResults(AnalysisRun analysisRun, AnalysisFileTree directoryNode,
	    GenericProjectMetrics metrics) throws EvaluationStoreException {
	getMetricStore().storeProjectResults(analysisRun, directoryNode, metrics);
    }

}
