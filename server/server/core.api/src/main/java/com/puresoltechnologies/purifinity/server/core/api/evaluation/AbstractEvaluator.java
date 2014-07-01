package com.puresoltechnologies.purifinity.server.core.api.evaluation;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.commons.misc.ConfigurationParameter;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.misc.StopWatch;
import com.puresoltechnologies.parsers.ust.eval.UniversalSyntaxTreeEvaluationException;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorInformation;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricFileResults;
import com.puresoltechnologies.purifinity.framework.store.api.DirectoryStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStoreFactory;
import com.puresoltechnologies.purifinity.framework.store.api.FileStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.DirectoryStoreServiceRemote;
import com.puresoltechnologies.purifinity.server.core.api.analysis.FileStoreServiceRemote;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

/**
 * This interface is the main interface for all evaluators and the general
 * behavior of them. All evaluators should have a name and a description as well
 * as some standard output like the report, the quality level and the evaluated
 * quality characteristics.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractEvaluator implements Evaluator {

    private static final long serialVersionUID = -497819792461488182L;

    private static final Logger logger = LoggerFactory
	    .getLogger(AbstractEvaluator.class);

    private static final int EXECUTION_TIMEOUT_IN_SECONDS = 30;

    private final Map<String, Object> properties = new HashMap<>();

    private FileStoreServiceRemote fileStore;

    private DirectoryStoreServiceRemote directoryStore;

    private final EvaluatorStoreFactory evaluatorStoreFactory = EvaluatorStoreFactory
	    .getFactory();

    private final EvaluatorStore evaluatorStore;

    private final EvaluatorInformation information;
    private final Date timeStamp;

    private long timeOfRun;

    private boolean reEvaluation = false;

    public AbstractEvaluator(String name, String description) {
	super();
	this.information = new EvaluatorInformation(name, description);
	timeStamp = new Date();
	evaluatorStore = evaluatorStoreFactory.createInstance(getClass());
    }

    @PostConstruct
    public void construct() {
	fileStore = JndiUtils.createRemoteEJBInstance(
		FileStoreServiceRemote.class, FileStoreServiceRemote.JNDI_NAME);
	directoryStore = JndiUtils.createRemoteEJBInstance(
		DirectoryStoreServiceRemote.class,
		DirectoryStoreServiceRemote.JNDI_NAME);
    }

    @Override
    public final EvaluatorInformation getInformation() {
	return information;
    }

    @Override
    public final Date getStartTime() {
	return timeStamp;
    }

    @Override
    public long getDuration() {
	return timeOfRun;
    }

    @Override
    public final void setReEvaluation(boolean reEvaluation) {
	this.reEvaluation = reEvaluation;
    }

    @Override
    public final boolean isReEvaluation() {
	return reEvaluation;
    }

    protected EvaluatorStore getEvaluatorStore() {
	return evaluatorStore;
    }

    @Override
    public final <T> T getConfigurationParameter(
	    ConfigurationParameter<T> parameter) {
	if (!getAvailableConfigurationParameters().contains(parameter)) {
	    throw new IllegalArgumentException("The parameter '" + parameter
		    + "' is not known.");
	}
	@SuppressWarnings("unchecked")
	T t = (T) properties.get(parameter.getPropertyKey());
	return t != null ? t : parameter.getDefaultValue();
    }

    @Override
    public final <T> void setConfigurationParameter(
	    ConfigurationParameter<T> parameter, T value) {
	if (!getAvailableConfigurationParameters().contains(parameter)) {
	    throw new IllegalArgumentException("The parameter '" + parameter
		    + "' is not known.");
	}
	properties.put(parameter.getPropertyKey(), value);
    }

    /**
     * This method is used to run an evaluation of an analyzed file. This method
     * is called by the run method.
     * 
     * @param analysis
     *            is the {@link CodeAnalysis} of the file which is to be
     *            evaluated.
     * @throws InterruptedException
     *             is thrown if the evaluation was interrupted.
     * @throws UniversalSyntaxTreeEvaluationException
     *             is thrown if the evaluation was aborted by an exceptional
     *             event.
     * @throws EvaluationStoreException
     */
    abstract protected MetricFileResults processFile(AnalysisRun analysisRun,
	    CodeAnalysis analysis) throws InterruptedException,
	    UniversalSyntaxTreeEvaluationException, EvaluationStoreException;

    /**
     * This method is used to run an evaluation of an entire directory. This
     * method is called by the run method.
     * 
     * @param directory
     *            is the {@link HashIdFileTree} object of the directory to be
     *            evaluated.
     * @throws InterruptedException
     *             is thrown if the evaluation was interrupted.
     * @throws EvaluationStoreException
     */
    abstract protected MetricDirectoryResults processDirectory(
	    AnalysisRun analysisRun, AnalysisFileTree directory)
	    throws InterruptedException, EvaluationStoreException;

    /**
     * This method is used to run an evaluation of the entire project. This
     * method is called by the run method.
     * 
     * @throws InterruptedException
     *             is thrown if the evaluation was interrupted.
     * @throws EvaluationStoreException
     */
    abstract protected MetricDirectoryResults processProject(
	    AnalysisRun analysisRun) throws InterruptedException,
	    EvaluationStoreException;

    @Override
    public final Boolean analyze(AnalysisRun analysisRun)
	    throws InterruptedException, EvaluationStoreException {
	// Start time measurement
	StopWatch watch = new StopWatch();
	watch.start();
	// check the files to evaluate and calculate amount of work!
	// process files and directories
	processTree(analysisRun, analysisRun.getFileTree());
	// process project as whole
	processProject(analysisRun);
	// Stop time measurement
	watch.stop();
	timeOfRun = watch.getMilliseconds();
	return true;
    }

    private void processTree(AnalysisRun analysisRun, AnalysisFileTree tree)
	    throws InterruptedException {
	try {
	    processNode(analysisRun, tree);
	} catch (FileStoreException | DirectoryStoreException
		| ArrayStoreException | EvaluationStoreException e) {
	    logger.error("Evaluation result could not be stored.", e);
	} catch (UniversalSyntaxTreeEvaluationException e) {
	    logger.error("Evaluation failed.", e);
	}
    }

    private void processNode(AnalysisRun analysisRun, AnalysisFileTree node)
	    throws FileStoreException, InterruptedException,
	    UniversalSyntaxTreeEvaluationException, EvaluationStoreException,
	    DirectoryStoreException {
	if (Thread.currentThread().isInterrupted()) {
	    throw new InterruptedException();
	}
	if (node.isFile()) {
	    processAsFile(analysisRun, node);
	} else {
	    processAsDirectory(analysisRun, node);
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
    private void processAsFile(AnalysisRun analysisRun,
	    AnalysisFileTree fileNode) throws FileStoreException,
	    InterruptedException, UniversalSyntaxTreeEvaluationException,
	    EvaluationStoreException {
	HashId hashId = fileNode.getHashId();
	if (fileStore.wasAnalyzed(hashId)) {
	    List<CodeAnalysis> fileAnalyses = fileStore.loadAnalyses(hashId);
	    for (CodeAnalysis fileAnalysis : fileAnalyses) {
		if ((!evaluatorStore.hasFileResults(hashId)) || (reEvaluation)) {
		    MetricFileResults fileResults = processFile(analysisRun,
			    fileAnalysis);
		    if (fileResults != null) {
			evaluatorStore.storeFileResults(analysisRun,
				fileAnalysis, this, fileResults);
		    }
		} else {
		    MetricFileResults fileResults = evaluatorStore
			    .readFileResults(hashId);
		    if (fileResults != null) {
			evaluatorStore.storeMetricsInBigTable(analysisRun,
				fileAnalysis, this, fileResults);
		    }
		}
	    }
	}
    }

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
    private void processAsDirectory(AnalysisRun analysisRun,
	    AnalysisFileTree directoryNode) throws FileStoreException,
	    InterruptedException, UniversalSyntaxTreeEvaluationException,
	    DirectoryStoreException, EvaluationStoreException {
	HashId hashId = directoryNode.getHashId();
	if (directoryStore.isAvailable(hashId)) {
	    for (AnalysisFileTree child : directoryNode.getChildren()) {
		processNode(analysisRun, child);
	    }
	    if ((!evaluatorStore.hasDirectoryResults(hashId)) || (reEvaluation)) {
		MetricDirectoryResults directoryResults = processDirectory(
			analysisRun, directoryNode);
		if (directoryResults != null) {
		    evaluatorStore.storeDirectoryResults(analysisRun,
			    directoryNode, this, directoryResults);
		}
	    } else {
		MetricDirectoryResults directoryResults = evaluatorStore
			.readDirectoryResults(hashId);
		if (directoryResults != null) {
		    evaluatorStore.storeMetricsInBigTable(analysisRun,
			    directoryNode, this, directoryResults);
		}
	    }
	}
    }

    /**
     * This method is used to explicitly start evaluations.
     * 
     * @param evaluator
     * @return
     * @throws InterruptedException
     * @throws UniversalSyntaxTreeEvaluationException
     */
    protected <T> T execute(Callable<T> evaluator) throws InterruptedException,
	    UniversalSyntaxTreeEvaluationException {
	try {
	    ExecutorService executor = Executors.newSingleThreadExecutor();
	    Future<T> future = executor.submit(evaluator);
	    executor.shutdown();
	    return future.get(EXECUTION_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
	} catch (ExecutionException e) {
	    throw new UniversalSyntaxTreeEvaluationException(e);
	} catch (TimeoutException e) {
	    throw new UniversalSyntaxTreeEvaluationException(e);
	}
    }
}
