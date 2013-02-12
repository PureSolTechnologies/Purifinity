package com.puresol.coding.evaluation.impl;

import java.io.IOException;
import java.util.Date;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.CodeAnalysis;
import com.puresol.coding.analysis.api.CodeStore;
import com.puresol.coding.analysis.api.CodeStoreException;
import com.puresol.coding.analysis.api.CodeStoreFactory;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.analysis.api.ModuleStore;
import com.puresol.coding.analysis.api.ModuleStoreFactory;
import com.puresol.coding.evaluation.api.Evaluator;
import com.puresol.coding.evaluation.api.EvaluatorInformation;
import com.puresol.coding.evaluation.api.EvaluatorStore;
import com.puresol.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.trees.TreeUtils;
import com.puresol.trees.TreeVisitor;
import com.puresol.trees.TreeWalker;
import com.puresol.trees.WalkingAction;
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

	private final AnalysisRun analysisRun;
	private final EvaluatorInformation information;
	private final Date timeStamp;
	private final EvaluatorStoreFactory evaluatorStoreFactory = EvaluatorStoreFactory
			.getFactory();

	private long timeOfRun;

	public AbstractEvaluator(String name, String description,
			AnalysisRun analysisRun) {
		super();
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

	private class EvaluationVisitor implements TreeVisitor<HashIdFileTree> {

		private final CodeStore fileStore = CodeStoreFactory.getFactory()
				.getInstance();
		private final ModuleStore directoryStore = ModuleStoreFactory
				.getFactory().getInstance();

		private EvaluationVisitor() {
			super();
		}

		@Override
		public WalkingAction visit(HashIdFileTree tree) {
			try {
				if (Thread.interrupted()) {
					fireDone("Work was cancelled.", true);
					return WalkingAction.ABORT;
				}
				if (tree.isFile()) {
					processAsFile(tree);
				} else {
					processAsDirectory(tree);
				}
				fireUpdateWork("Evaluated '" + tree.getName() + "'.", 1);
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
	public Boolean call() {
		try {
			// Start time measurement
			StopWatch watch = new StopWatch();
			watch.start();
			// check the files to evaluate and calculate amount of work!
			HashIdFileTree fileTree = getAnalysisRun().getFileTree();
			int nodeCount = TreeUtils.countNodes(fileTree);
			fireStarted("Beginning evaluation...", nodeCount + 1);
			// process files and directories
			TreeWalker<HashIdFileTree> treeWalker = new TreeWalker<HashIdFileTree>(
					fileTree);
			EvaluationVisitor treeVisitor = new EvaluationVisitor();
			treeWalker.walkBackward(treeVisitor);
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

	@Override
	public EvaluatorStore getEvaluatorStore() {
		return evaluatorStoreFactory.createInstance(getClass());
	}

}
