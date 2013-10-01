package com.puresol.purifinity.coding.evaluation.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.commons.configuration.ConfigurationParameter;
import com.puresol.commons.trees.TreeUtils;
import com.puresol.commons.utils.StopWatch;
import com.puresol.commons.utils.progress.AbstractProgressObservable;
import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.analysis.api.CodeAnalysis;
import com.puresol.purifinity.coding.analysis.api.DirectoryStore;
import com.puresol.purifinity.coding.analysis.api.DirectoryStoreFactory;
import com.puresol.purifinity.coding.analysis.api.FileStore;
import com.puresol.purifinity.coding.analysis.api.FileStoreException;
import com.puresol.purifinity.coding.analysis.api.FileStoreFactory;
import com.puresol.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresol.purifinity.coding.evaluation.api.Evaluator;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorInformation;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStore;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.purifinity.license.api.LicensedObject;
import com.puresol.purifinity.uhura.ust.eval.UniversalSyntaxTreeEvaluationException;

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

	private static final int EXECUTION_TIMEOUT_IN_SECONDS = 30;

	private final Map<String, Object> properties = new HashMap<>();

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

	private boolean reEvaluation = false;

	public AbstractEvaluator(String name, String description,
			AnalysisRun analysisRun, HashIdFileTree path) {
		super();
		LicensedObject.checkIfLicensed(getClass());
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

	@Override
	public final void setReEvaluation(boolean reEvaluation) {
		this.reEvaluation = reEvaluation;
	}

	@Override
	public final boolean isReEvaluation() {
		return reEvaluation;
	}

	@Override
	public final <T> T getConfigurationParameter(
			ConfigurationParameter<T> parameter) {
		@SuppressWarnings("unchecked")
		T t = (T) properties.get(parameter.getPropertyKey());
		return t != null ? t : parameter.getDefaultValue();
	}

	@Override
	public final <T> void setConfigurationParameter(
			ConfigurationParameter<T> parameter, T value) {
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
	 */
	abstract protected void processFile(CodeAnalysis analysis)
			throws InterruptedException, UniversalSyntaxTreeEvaluationException;

	/**
	 * This method is used to run an evaluation of an entire directory. This
	 * method is called by the run method.
	 * 
	 * @param directory
	 *            is the {@link HashIdFileTree} object of the directory to be
	 *            evaluated.
	 * @throws InterruptedException
	 *             is thrown if the evaluation was interrupted.
	 */
	abstract protected void processDirectory(HashIdFileTree directory)
			throws InterruptedException;

	/**
	 * This method is used to run an evaluation of the entire project. This
	 * method is called by the run method.
	 * 
	 * @throws InterruptedException
	 *             is thrown if the evaluation was interrupted.
	 */
	abstract protected void processProject() throws InterruptedException;

	@Override
	public final Boolean call() throws InterruptedException {
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
		return true;
	}

	private void processTree(HashIdFileTree tree) throws InterruptedException {
		try {
			processNode(tree);
		} catch (FileStoreException e) {
			logger.error("Evaluation result could not be stored.", e);
		} catch (UniversalSyntaxTreeEvaluationException e) {
			logger.error("Evaluation failed.", e);
		}
	}

	private void processNode(HashIdFileTree node) throws FileStoreException,
			InterruptedException, UniversalSyntaxTreeEvaluationException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}
		if (node.isFile()) {
			processAsFile(node);
		} else {
			processAsDirectory(node);
		}
		fireUpdateWork("Evaluated '" + node.getName() + "'.", 1);
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
	 */
	private void processAsFile(HashIdFileTree fileNode)
			throws FileStoreException, InterruptedException,
			UniversalSyntaxTreeEvaluationException {
		if (fileStore.wasAnalyzed(fileNode.getHashId())) {
			if ((!evaluatorStore.hasFileResults(fileNode.getHashId()))
					|| (reEvaluation)) {
				CodeAnalysis fileAnalysis = fileStore.loadAnalysis(fileNode
						.getHashId());
				processFile(fileAnalysis);
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
	 */
	private void processAsDirectory(HashIdFileTree directoryNode)
			throws FileStoreException, InterruptedException,
			UniversalSyntaxTreeEvaluationException {
		if (directoryStore.isAvailable(directoryNode.getHashId())) {
			if ((!evaluatorStore.hasDirectoryResults(directoryNode.getHashId()))
					|| (reEvaluation)) {
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
			fireDone(e.getMessage(), false);
			throw new UniversalSyntaxTreeEvaluationException(e);
		} catch (TimeoutException e) {
			fireDone(e.getMessage(), false);
			throw new UniversalSyntaxTreeEvaluationException(e);
		}
	}
}
