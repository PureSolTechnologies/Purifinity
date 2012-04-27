package com.puresol.coding.evaluator;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.DirectoryStore;
import com.puresol.coding.analysis.api.DirectoryStoreFactory;
import com.puresol.coding.analysis.api.FileAnalysis;
import com.puresol.coding.analysis.api.FileStore;
import com.puresol.coding.analysis.api.FileStoreException;
import com.puresol.coding.analysis.api.FileStoreFactory;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.evaluation.api.Evaluator;
import com.puresol.coding.evaluation.api.EvaluatorInformation;
import com.puresol.coding.evaluation.api.EvaluatorResults;
import com.puresol.coding.evaluation.api.FileResult;
import com.puresol.trees.TreeUtils;
import com.puresol.trees.TreeVisitor;
import com.puresol.trees.TreeWalker;
import com.puresol.trees.WalkingAction;
import com.puresol.utils.StopWatch;

/**
 * This interface is the main interface for all evaluators and the general
 * behavior of them. All evaluators should have a name and a description as well
 * as some standard output like the report, the quality level and the evaluated
 * quality characteristics.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractEvaluator<T extends EvaluatorResults> extends Job
	implements Serializable, Evaluator<T> {

    private static final long serialVersionUID = -497819792461488182L;

    private final AnalysisRun analysisRun;
    private final EvaluatorInformation information;
    private final Date timeStamp;
    private long timeOfRun;

    public AbstractEvaluator(EvaluatorInformation information,
	    AnalysisRun analysisRun) {
	super(information.getName());
	this.information = information;
	this.analysisRun = analysisRun;
	timeStamp = new Date();
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
    public final Date getTime() {
	return timeStamp;
    }

    @Override
    public long getTimeOfRun() {
	return timeOfRun;
    }

    /**
     * This method is used to run an evaluation of an analyzed file. This method
     * is called by the run method.
     * 
     * @param file
     * @return
     * @throws IOException
     * @throws FileStoreException
     */
    abstract protected FileResult processFile(FileAnalysis analysis)
	    throws InterruptedException;

    abstract protected void processDirectory(HashIdFileTree directory)
	    throws InterruptedException;

    abstract protected void processProject() throws InterruptedException;

    @Override
    public final void runEvaluation() throws InterruptedException {
	schedule();
	join();
    }

    private class EvaluationVisitor implements TreeVisitor<HashIdFileTree> {

	private final FileStore fileStore = FileStoreFactory.getInstance();
	private final DirectoryStore directoryStore = DirectoryStoreFactory
		.getInstance();
	private final IProgressMonitor monitor;

	private EvaluationVisitor(IProgressMonitor monitor) {
	    super();
	    this.monitor = monitor;
	}

	@Override
	public WalkingAction visit(HashIdFileTree tree) {
	    try {
		if (monitor.isCanceled()) {
		    monitor.done();
		    return WalkingAction.ABORT;
		}
		if (tree.isFile()) {
		    processAsFile(tree);
		} else {
		    processAsDirectory(tree);
		}
		monitor.worked(1);
		return WalkingAction.PROCEED;
	    } catch (FileStoreException e) {
		e.printStackTrace();
		return WalkingAction.ABORT;
	    } catch (InterruptedException e) {
		e.printStackTrace();
		return WalkingAction.ABORT;
	    }
	}

	private void processAsFile(HashIdFileTree tree)
		throws FileStoreException, InterruptedException {
	    if (fileStore.wasAnalyzed(tree.getHashId())) {
		FileAnalysis fileAnalysis = fileStore.loadAnalysis(tree
			.getHashId());
		FileResult levels = processFile(fileAnalysis);
		// save result...
	    }
	}

	private void processAsDirectory(HashIdFileTree tree)
		throws FileStoreException, InterruptedException {
	    if (directoryStore.isAvailable(tree.getHashId())) {
		processDirectory(tree);
	    }
	}
    }

    @Override
    public IStatus run(IProgressMonitor monitor) {
	// Start time measurement
	StopWatch watch = new StopWatch();
	watch.start();

	HashIdFileTree fileTree = getAnalysisRun().getFileTree();
	int nodeCount = TreeUtils.countNodes(fileTree);
	monitor.beginTask(getName(), nodeCount);

	TreeWalker<HashIdFileTree> treeWalker = new TreeWalker<HashIdFileTree>(
		fileTree);
	EvaluationVisitor treeVisitor = new EvaluationVisitor(monitor);
	treeWalker.walkBackward(treeVisitor);

	// Stop time measurement
	watch.stop();
	timeOfRun = watch.getMilliseconds();

	monitor.done();
	return Status.OK_STATUS;
    }

}
