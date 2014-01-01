package com.puresoltechnologies.purifinity.framework.evaluation.commons.impl;

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

import com.puresoltechnologies.commons.misc.ConfigurationParameter;
import com.puresoltechnologies.commons.trees.api.TreeUtils;
import com.puresoltechnologies.parsers.impl.ust.eval.UniversalSyntaxTreeEvaluationException;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorInformation;
import com.puresoltechnologies.purifinity.framework.commons.utils.StopWatch;
import com.puresoltechnologies.purifinity.framework.commons.utils.progress.AbstractProgressObservable;
import com.puresoltechnologies.purifinity.framework.store.api.DirectoryStore;
import com.puresoltechnologies.purifinity.framework.store.api.DirectoryStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.DirectoryStoreFactory;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStoreFactory;
import com.puresoltechnologies.purifinity.framework.store.api.FileStore;
import com.puresoltechnologies.purifinity.framework.store.api.FileStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.FileStoreFactory;

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
	private final AnalysisFileTree path;
	private final Date timeStamp;

	private long timeOfRun;

	private boolean reEvaluation = false;

	public AbstractEvaluator(String name, String description,
			AnalysisRun analysisRun, AnalysisFileTree path) {
		super();
		this.information = new EvaluatorInformation(name, description);
		this.analysisRun = analysisRun;
		this.path = path;
		timeStamp = new Date();
		evaluatorStore = evaluatorStoreFactory.createInstance(getClass());
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

	protected EvaluatorStore getEvaluatorStore() {
		return evaluatorStore;
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
	 * @throws EvaluationStoreException
	 */
	abstract protected void processFile(CodeAnalysis analysis)
			throws InterruptedException,
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
	abstract protected void processDirectory(AnalysisFileTree directory)
			throws InterruptedException, EvaluationStoreException;

	/**
	 * This method is used to run an evaluation of the entire project. This
	 * method is called by the run method.
	 * 
	 * @throws InterruptedException
	 *             is thrown if the evaluation was interrupted.
	 * @throws EvaluationStoreException
	 */
	abstract protected void processProject() throws InterruptedException,
			EvaluationStoreException;

	@Override
	public final Boolean call() throws InterruptedException,
			EvaluationStoreException {
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

	private void processTree(AnalysisFileTree tree) throws InterruptedException {
		try {
			processNode(tree);
		} catch (FileStoreException | DirectoryStoreException
				| ArrayStoreException | EvaluationStoreException e) {
			logger.error("Evaluation result could not be stored.", e);
		} catch (UniversalSyntaxTreeEvaluationException e) {
			logger.error("Evaluation failed.", e);
		}
	}

	private void processNode(AnalysisFileTree node) throws FileStoreException,
			InterruptedException, UniversalSyntaxTreeEvaluationException,
			EvaluationStoreException, DirectoryStoreException {
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
	 * @throws EvaluationStoreException
	 */
	private void processAsFile(AnalysisFileTree fileNode)
			throws FileStoreException, InterruptedException,
			UniversalSyntaxTreeEvaluationException, EvaluationStoreException {
		if (fileStore.wasAnalyzed(fileNode.getHashId())) {
			if ((!evaluatorStore.hasFileResults(fileNode.getHashId()))
					|| (reEvaluation)) {
				CodeAnalysis fileAnalysis = fileStore.loadAnalysis(fileNode
						.getHashId(), Thread.currentThread()
						.getContextClassLoader());
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
	 * @throws EvaluationStoreException
	 * @throws DirectoryStoreException
	 */
	private void processAsDirectory(AnalysisFileTree directoryNode)
			throws FileStoreException, InterruptedException,
			UniversalSyntaxTreeEvaluationException, DirectoryStoreException,
			EvaluationStoreException {
		if (directoryStore.isAvailable(directoryNode.getHashId())) {
			if ((!evaluatorStore.hasDirectoryResults(directoryNode.getHashId()))
					|| (reEvaluation)) {
				for (AnalysisFileTree child : directoryNode.getChildren()) {
					processNode(child);
				}
				processDirectory(directoryNode);
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
			fireDone(e.getMessage(), false);
			throw new UniversalSyntaxTreeEvaluationException(e);
		} catch (TimeoutException e) {
			fireDone(e.getMessage(), false);
			throw new UniversalSyntaxTreeEvaluationException(e);
		}
	}
}
