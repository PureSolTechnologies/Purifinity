package com.puresoltechnologies.purifinity.server.core.api.evaluation;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.ust.eval.UniversalSyntaxTreeEvaluationException;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorInformation;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorType;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.CommonDirectoryStore;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.DirectoryStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.DirectoryStoreRemote;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.CommonFileStore;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStoreRemote;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;
import com.puresoltechnologies.versioning.Version;

/**
 * This interface is an abstract implementation for evaluators and the general
 * behavior of them for re-use. All evaluators should have a name and a
 * description as well as some standard output like the report, the quality
 * level and the evaluated quality characteristics.
 * 
 * <b>Extending implementations must not have mutable state!</b>
 *
 * @author Rick-Rainer Ludwig
 */
public abstract class AbstractEvaluator<FileResults, FileResultsImpl extends FileResults, DirectoryResults, DirectoryResultsImpl extends DirectoryResults, ProjectResults, ProjectResultsImpl extends ProjectResults>
	implements Evaluator {

    private static final Logger logger = LoggerFactory.getLogger(AbstractEvaluator.class);

    private final FileStoreRemote fileStore;

    private final DirectoryStoreRemote directoryStore;

    private final EvaluatorInformation information;

    public AbstractEvaluator(String id, String name, Version version, EvaluatorType type, String description) {
	super();
	this.information = new EvaluatorInformation(id, name, version, type, description);

	fileStore = JndiUtils.createRemoteEJBInstance(FileStoreRemote.class, FileStoreRemote.JNDI_NAME);
	directoryStore = JndiUtils.createRemoteEJBInstance(DirectoryStoreRemote.class,
		DirectoryStoreRemote.JNDI_NAME);

    }

    protected CommonFileStore getFileStore() {
	return fileStore;
    }

    protected CommonDirectoryStore getDirectoryStore() {
	return directoryStore;
    }

    @Override
    public EvaluatorInformation getInformation() {
	return information;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void evaluate(AnalysisRun analysisRun, boolean enableReevaluation)
	    throws InterruptedException, EvaluationStoreException {
	// process files and directories
	processTree(analysisRun, enableReevaluation);
	// process project as whole
	processProject(analysisRun, enableReevaluation);
    }

    private void processTree(AnalysisRun analysisRun, boolean enableReevaluation) throws InterruptedException {
	try {
	    processNode(analysisRun, analysisRun.getFileTree(), enableReevaluation);
	} catch (FileStoreException | DirectoryStoreException | ArrayStoreException | EvaluationStoreException e) {
	    logger.error("Evaluation result could not be stored.", e);
	} catch (UniversalSyntaxTreeEvaluationException e) {
	    logger.error("Evaluation failed.", e);
	}
    }

    protected void processNode(AnalysisRun analysisRun, AnalysisFileTree node, boolean enableReevaluation)
	    throws FileStoreException, InterruptedException, UniversalSyntaxTreeEvaluationException,
	    EvaluationStoreException, DirectoryStoreException {
	if (Thread.currentThread().isInterrupted()) {
	    throw new InterruptedException();
	}
	if (node.isFile()) {
	    processAsFile(analysisRun, node, enableReevaluation);
	} else {
	    processAsDirectory(analysisRun, node, enableReevaluation);
	}
    }

    /**
     * This method is called on a file node to run the actual evaluation. The
     * evaluation is delegated to the explicit implementing class.
     * 
     * @param fileNode
     *            is the node of the file with in the project's file tree.
     * @throws FileStoreException
     *             is thrown if the file store had an exception.
     * @throws InterruptedException
     *             is thrown if the operation was interrupted.
     * @throws UniversalSyntaxTreeEvaluationException
     *             is thrown if the evaluation had an exception.
     * @throws EvaluationStoreException
     */
    abstract protected void processAsFile(AnalysisRun analysisRun, AnalysisFileTree fileNode,
	    boolean enableReevaluation) throws FileStoreException, InterruptedException,
		    UniversalSyntaxTreeEvaluationException, EvaluationStoreException;

    /**
     * This method is called on a directory node to run the actual evaluation.
     * The evaluation is delegated to the explicit implementing class.
     * 
     * @param directoryNode
     *            is the node of the directory with in the project's file tree.
     * @throws FileStoreException
     *             is thrown if the file store had an exception.
     * @throws InterruptedException
     *             is thrown if the operation was interrupted.
     * @throws UniversalSyntaxTreeEvaluationException
     *             is thrown if the evaluation had an exception.
     * @throws EvaluationStoreException
     * @throws DirectoryStoreException
     */
    abstract protected void processAsDirectory(AnalysisRun analysisRun, AnalysisFileTree directoryNode,
	    boolean enableReevaluation) throws FileStoreException, InterruptedException,
		    UniversalSyntaxTreeEvaluationException, DirectoryStoreException, EvaluationStoreException;

    /**
     * This method is used to run an evaluation of the entire project. This
     * method is called by the run method.
     *
     * @param analysisRun
     *            is the {@link AnalysisRun} for which the evaluation is to be
     *            run.
     * @param enableReevaluation
     *            is set to <code>true</code> if all evaluations are to be
     *            calculated again, even if results already exist.
     *            <code>false</code> is to be set otherwise.
     * @return The results are returned as DirectoryResults object.
     * @throws InterruptedException
     *             is thrown if the evaluation was interrupted.
     * @throws EvaluationStoreException
     *             is thrown in cases of issues within evaluation store.
     */
    abstract protected DirectoryResults processProject(AnalysisRun analysisRun, boolean enableReevaluation)
	    throws InterruptedException, EvaluationStoreException;

    abstract protected FileResults readFileResults(HashId hashId) throws EvaluationStoreException;

    abstract protected boolean hasFileResults(HashId hashId) throws EvaluationStoreException;

    abstract protected void storeFileResults(AnalysisRun analysisRun, CodeAnalysis fileAnalysis,
	    FileResultsImpl results) throws EvaluationStoreException;

    abstract protected DirectoryResults readDirectoryResults(HashId hashId) throws EvaluationStoreException;

    abstract protected boolean hasDirectoryResults(HashId hashId) throws EvaluationStoreException;

    abstract protected void storeDirectoryResults(AnalysisRun analysisRun, AnalysisFileTree directoryNode,
	    DirectoryResultsImpl results) throws EvaluationStoreException;

    abstract protected ProjectResults readProjectResults(String projectId, long runId) throws EvaluationStoreException;

    abstract protected boolean hasProjectResults(String projectId, long runId) throws EvaluationStoreException;

    abstract protected void storeProjectResults(AnalysisRun analysisRun, AnalysisFileTree directoryNode,
	    ProjectResultsImpl results) throws EvaluationStoreException;
}
