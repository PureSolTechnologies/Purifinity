package com.puresol.coding.evaluator;

import java.io.IOException;
import java.util.Date;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import com.puresol.coding.analysis.Activator;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.ModuleStore;
import com.puresol.coding.analysis.api.ModuleStoreFactory;
import com.puresol.coding.analysis.api.CodeAnalysis;
import com.puresol.coding.analysis.api.CodeStore;
import com.puresol.coding.analysis.api.CodeStoreException;
import com.puresol.coding.analysis.api.CodeStoreFactory;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.evaluation.api.Evaluator;
import com.puresol.coding.evaluation.api.EvaluatorInformation;
import com.puresol.coding.evaluation.api.EvaluatorStore;
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
public abstract class AbstractEvaluator extends Job implements Evaluator {

    private static final long serialVersionUID = -497819792461488182L;

    private final AnalysisRun analysisRun;
    private final EvaluatorInformation information;
    private final Date timeStamp;
    private long timeOfRun;

    public AbstractEvaluator(String name, String description,
	    AnalysisRun analysisRun) {
	super(name);
	this.information = new EvaluatorInformation(name, description);
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
     * @throws IOException
     * @throws CodeStoreException
     */
    abstract protected void processFile(CodeAnalysis analysis)
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

	private final CodeStore fileStore = CodeStoreFactory.getInstance();
	private final ModuleStore directoryStore = ModuleStoreFactory
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
	    } catch (CodeStoreException e) {
		e.printStackTrace();
		return WalkingAction.ABORT;
	    } catch (InterruptedException e) {
		e.printStackTrace();
		return WalkingAction.ABORT;
	    }
	}

	private void processAsFile(HashIdFileTree tree)
		throws CodeStoreException, InterruptedException {
	    if (fileStore.wasAnalyzed(tree.getHashId())) {
		CodeAnalysis fileAnalysis = fileStore.loadAnalysis(tree
			.getHashId());
		processFile(fileAnalysis);
	    }
	}

	private void processAsDirectory(HashIdFileTree tree)
		throws CodeStoreException, InterruptedException {
	    if (directoryStore.isAvailable(tree.getHashId())) {
		processDirectory(tree);
	    }
	}
    }

    @Override
    public IStatus run(IProgressMonitor monitor) {
	try {
	    // Start time measurement
	    StopWatch watch = new StopWatch();
	    watch.start();
	    // check the files to evaluate and calculate amount of work!
	    HashIdFileTree fileTree = getAnalysisRun().getFileTree();
	    int nodeCount = TreeUtils.countNodes(fileTree);
	    monitor.beginTask(getName(), nodeCount + 1); // + 1 for the project
	    // process files and directories
	    TreeWalker<HashIdFileTree> treeWalker = new TreeWalker<HashIdFileTree>(
		    fileTree);
	    EvaluationVisitor treeVisitor = new EvaluationVisitor(monitor);
	    treeWalker.walkBackward(treeVisitor);
	    // process project as whole
	    processProject();
	    // Stop time measurement
	    watch.stop();
	    timeOfRun = watch.getMilliseconds();

	    monitor.done();
	} catch (InterruptedException e) {
	    /*
	     * XXX This exception is silly, but we need to introduce
	     * InterruptedException into TreeWalker to get a real interrupted
	     * handling!
	     */
	}
	return Status.OK_STATUS;
    }

    @Override
    public EvaluatorStore getEvaluatorStore() {
	return createEvaluatorStore(getClass());
    }

    public static EvaluatorStore createEvaluatorStore(
	    Class<? extends Evaluator> clazz) {
	try {
	    BundleContext bundleContext = Activator.getBundleContext();
	    ServiceReference[] serviceReferences = bundleContext
		    .getServiceReferences(EvaluatorStore.class.getName(),
			    "(evaluator=" + clazz.getName() + ")");
	    if ((serviceReferences == null) || (serviceReferences.length == 0)) {
		return null;
	    }
	    if (serviceReferences.length > 1) {
		throw new RuntimeException(
			"More than one evaluator store was registered for '"
				+ clazz.getName() + "'!");
	    }
	    ServiceReference serviceReference = serviceReferences[0];
	    EvaluatorStore store = (EvaluatorStore) bundleContext
		    .getService(serviceReference);
	    return store;
	} catch (InvalidSyntaxException e) {
	    throw new RuntimeException("Could not find store for evaluator '"
		    + clazz.getName() + "'!", e);
	}
    }
}
