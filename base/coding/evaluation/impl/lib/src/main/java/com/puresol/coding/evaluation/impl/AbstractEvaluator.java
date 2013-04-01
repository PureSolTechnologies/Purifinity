package com.puresol.coding.evaluation.impl;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.CodeAnalysis;
import com.puresol.coding.analysis.api.DirectoryStore;
import com.puresol.coding.analysis.api.DirectoryStoreFactory;
import com.puresol.coding.analysis.api.FileStore;
import com.puresol.coding.analysis.api.FileStoreException;
import com.puresol.coding.analysis.api.FileStoreFactory;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.evaluation.api.Evaluator;
import com.puresol.coding.evaluation.api.EvaluatorInformation;
import com.puresol.coding.evaluation.api.EvaluatorStore;
import com.puresol.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.trees.TreeUtils;
import com.puresol.uhura.ust.eval.EvaluationException;
import com.puresol.utils.StopWatch;
import com.puresol.utils.progress.AbstractProgressObservable;

/**
 * This interface is the main interface for all evaluators and the general
 * behavior of them. All evaluators should have a name and a description as well
 * as some standard output like the report, the quality level and the evaluated
 * quality characteristics.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractEvaluator extends
	AbstractProgressObservable<Evaluator> implements Evaluator {

    private static final long serialVersionUID = -497819792461488182L;

    private static final Logger logger = LoggerFactory
	    .getLogger(AbstractEvaluator.class);

    private final FileStore fileStore = FileStoreFactory.getFactory()
	    .getInstance();
    private final DirectoryStore directoryStore = DirectoryStoreFactory
	    .getFactory().getInstance();
    private final EvaluatorStoreFactory evaluatorStoreFactory = EvaluatorStoreFactory
	    .getFactory();

    private final EvaluatorStore evaluatorStore;

    private final EvaluatorInformation information;
    private final AnalysisRun analysisRun;
    private final HashIdFileTree path;
    private final Date timeStamp;

    private long timeOfRun;

    public AbstractEvaluator(String name, String description,
	    AnalysisRun analysisRun, HashIdFileTree path) {
	super();
	this.information = new EvaluatorInformation(name, description);
	this.analysisRun = analysisRun;
	this.path = path;
	timeStamp = new Date();
	evaluatorStore = createEvaluatorStore();
    }

    @Override
    public final EvaluatorInformation getInformation() {
	return information;
    }

    @Override
    public final AnalysisRun getAnalysisRun() {
	return analysisRun;
    }

    @Override
    public final Date getStartTime() {
	return timeStamp;
    }

    @Override
    public long getDuration() {
	return timeOfRun;
    }

    /**
     * This method is used to run an evaluation of an analyzed file. This method
     * is called by the run method.
     * 
     * @param file
     * @return
     * @throws EvaluationException
     * @throws IOException
     * @throws FileStoreException
     */
    abstract protected void processFile(CodeAnalysis analysis)
	    throws InterruptedException, EvaluationException;

    abstract protected void processDirectory(HashIdFileTree directory)
	    throws InterruptedException;

    abstract protected void processProject() throws InterruptedException;

    @Override
    public final Boolean call() {
	try {
	    // Start time measurement
	    StopWatch watch = new StopWatch();
	    watch.start();
	    // check the files to evaluate and calculate amount of work!
	    if (path != null) {
		int nodeCount = TreeUtils.countNodes(path);
		fireStarted("Beginning evaluation...", nodeCount + 1);
	    } else {
		fireStarted("Beginning evaluation...", 0);
	    }
	    // process files and directories
	    processTree(path);
	    // process project as whole
	    processProject();
	    // Stop time measurement
	    watch.stop();
	    timeOfRun = watch.getMilliseconds();
	    fireDone("Evaluation finished.", true);
	} catch (InterruptedException e) {
	    /*
	     * XXX This exception is silly, but we need to introduce
	     * InterruptedException into TreeWalker to get a real interrupted
	     * handling!
	     */
	}
	return true;
    }

    public void processTree(HashIdFileTree tree) {
	try {
	    processNode(tree);
	} catch (FileStoreException e) {
	    logger.error("Evaluation result could not be stored.", e);
	} catch (InterruptedException e) {
	    logger.error("Evaluation was interrupted.", e);
	} catch (EvaluationException e) {
	    logger.error("Evaluation failed.", e);
	}
    }

    public void processNode(HashIdFileTree node) throws FileStoreException,
	    InterruptedException, EvaluationException {
	if (node.isFile()) {
	    processAsFile(node);
	} else {
	    processAsDirectory(node);
	}
	fireUpdateWork("Evaluated '" + node.getName() + "'.", 1);
    }

    private void processAsFile(HashIdFileTree fileNode)
	    throws FileStoreException, InterruptedException,
	    EvaluationException {
	if (fileStore.wasAnalyzed(fileNode.getHashId())) {
	    if (!evaluatorStore.hasFileResults(fileNode.getHashId())) {
		CodeAnalysis fileAnalysis = fileStore.loadAnalysis(fileNode
			.getHashId());
		processFile(fileAnalysis);
	    }
	}
    }

    private void processAsDirectory(HashIdFileTree directoryNode)
	    throws FileStoreException, InterruptedException,
	    EvaluationException {
	if (directoryStore.isAvailable(directoryNode.getHashId())) {
	    if (!evaluatorStore.hasDirectoryResults(directoryNode.getHashId())) {
		for (HashIdFileTree child : directoryNode.getChildren()) {
		    processNode(child);
		}
		processDirectory(directoryNode);
	    }
	}
    }

    @Override
    public EvaluatorStore createEvaluatorStore() {
	return evaluatorStoreFactory.createInstance(getClass());
    }

    /**
     * This method is used to start evaluations.
     * 
     * @param evaluator
     * @return
     * @throws InterruptedException
     * @throws EvaluationException
     */
    protected <T> T execute(Callable<T> evaluator) throws InterruptedException,
	    EvaluationException {
	try {
	    ExecutorService executor = Executors.newSingleThreadExecutor();
	    Future<T> future = executor.submit(evaluator);
	    executor.shutdown();
	    return future.get(30, TimeUnit.SECONDS);
	} catch (ExecutionException e) {
	    fireDone(e.getMessage(), false);
	    throw new EvaluationException(e);
	} catch (TimeoutException e) {
	    fireDone(e.getMessage(), false);
	    throw new EvaluationException(e);
	}
    }
}
