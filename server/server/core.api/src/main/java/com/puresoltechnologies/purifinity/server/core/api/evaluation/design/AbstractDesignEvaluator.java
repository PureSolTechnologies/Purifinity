package com.puresoltechnologies.purifinity.server.core.api.evaluation.design;

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
import com.puresoltechnologies.purifinity.evaluation.api.defects.DefectEvaluator;
import com.puresoltechnologies.purifinity.evaluation.domain.design.DirectoryDesignIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.design.FileDesignIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.design.GenericDirectoryDesignIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.design.GenericFileDesignIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.design.GenericProjectDesignIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.design.ProjectDesignIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.FileMetrics;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.DirectoryStore;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.DirectoryStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStore;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStoreException;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluator;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;
import com.puresoltechnologies.versioning.Version;

public abstract class AbstractDesignEvaluator extends
	AbstractEvaluator<FileDesignIssues, GenericFileDesignIssues, DirectoryDesignIssues, GenericDirectoryDesignIssues, ProjectDesignIssues, GenericProjectDesignIssues>
	implements DefectEvaluator {

    private final EvaluatorDesignIssuesStoreServiceRemote designIssuesStore;

    public AbstractDesignEvaluator(String id, String name, Version version, String description) {
	super(id, name, version, EvaluatorType.DESGIN, description);
	designIssuesStore = JndiUtils.createRemoteEJBInstance(EvaluatorDesignIssuesStoreServiceRemote.class,
		EvaluatorDesignIssuesStoreServiceRemote.JNDI_NAME);
    }

    protected final EvaluatorDesignIssuesStore getDesignIssuessStore() {
	return designIssuesStore;
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
    abstract protected FileDesignIssues processFile(AnalysisRun analysisRun, CodeAnalysis analysis)
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
    abstract protected DirectoryDesignIssues processDirectory(AnalysisRun analysisRun, AnalysisFileTree directory)
	    throws InterruptedException, EvaluationStoreException;

    @Override
    protected final void processAsFile(AnalysisRun analysisRun, AnalysisFileTree fileNode, boolean enableReevaluation)
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
		    FileDesignIssues fileResults = processFile(analysisRun, fileAnalysis);
		    if (fileResults != null) {
			GenericFileDesignIssues metrics = new GenericFileDesignIssues(getInformation().getId(),
				getInformation().getVersion(), hashId, fileResults.getSourceCodeLocation(), new Date(),
				fileResults.getParameters(), fileResults.getCodeRangeDesignIssues());
			storeFileResults(analysisRun, fileAnalysis, metrics);
		    }
		}
	    } else {
		FileDesignIssues fileResults = readFileResults(hashId);
		if (fileResults != null) {
		    storeDesignIssuesInBigTable(analysisRun, fileAnalysis, fileResults);
		}
	    }
	}
    }

    @Override
    protected final void processAsDirectory(AnalysisRun analysisRun, AnalysisFileTree directoryNode,
	    boolean enableReevaluation) throws FileStoreException, InterruptedException,
		    UniversalSyntaxTreeEvaluationException, DirectoryStoreException, EvaluationStoreException {
	HashId hashId = directoryNode.getHashId();
	DirectoryStore directoryStore = getDirectoryStore();
	EvaluatorDesignIssuesStore store = getDesignIssuessStore();
	if (directoryStore.isAvailable(hashId)) {
	    for (AnalysisFileTree child : directoryNode.getChildren()) {
		processNode(analysisRun, child, enableReevaluation);
	    }
	    if ((!store.hasDirectoryResults(hashId, getInformation().getId())) || (enableReevaluation)) {
		DirectoryDesignIssues directoryResults = processDirectory(analysisRun, directoryNode);
		if (directoryResults != null) {
		    GenericDirectoryDesignIssues designIssues = new GenericDirectoryDesignIssues(
			    getInformation().getId(), getInformation().getVersion(), hashId, new Date(),
			    directoryResults.getParameters(), directoryResults.getDesignIssues());
		    storeDirectoryResults(analysisRun, directoryNode, designIssues);
		}
	    } else {
		DirectoryDesignIssues directoryResults = readDirectoryResults(hashId);
		if (directoryResults != null) {
		    storeDesignIssuesInBigTable(analysisRun, directoryNode, directoryResults);
		}
	    }
	}
    }

    private void storeDesignIssuesInBigTable(AnalysisRun analysisRun, CodeAnalysis fileAnalysis,
	    FileDesignIssues fileResults) {
	// TODO Auto-generated method stub

    }

    private void storeDesignIssuesInBigTable(AnalysisRun analysisRun, AnalysisFileTree directoryNode,
	    DirectoryDesignIssues directoryResults) {
	// TODO Auto-generated method stub

    }

}
