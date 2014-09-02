package com.puresoltechnologies.purifinity.server.core.api.evaluation;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.ejb.EJB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.commons.misc.ConfigurationParameter;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.parsers.ust.eval.UniversalSyntaxTreeEvaluationException;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorInformation;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorType;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.FileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericDirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericFileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericProjectMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.ProjectMetrics;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.DirectoryStore;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.DirectoryStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.DirectoryStoreServiceRemote;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStore;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStoreServiceRemote;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.store.EvaluatorStore;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.store.EvaluatorStoreServiceRemote;

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
public abstract class AbstractEvaluator implements Evaluator {

	private static final Logger logger = LoggerFactory
			.getLogger(AbstractEvaluator.class);

	private static final int EXECUTION_TIMEOUT_IN_SECONDS = 300;

	private final Map<String, Object> properties = new HashMap<>();

	@EJB(lookup = EvaluatorStoreServiceRemote.JNDI_NAME)
	private EvaluatorStoreServiceRemote store;

	@EJB(lookup = FileStoreServiceRemote.JNDI_NAME)
	private FileStoreServiceRemote fileStore;

	@EJB(lookup = DirectoryStoreServiceRemote.JNDI_NAME)
	private DirectoryStoreServiceRemote directoryStore;

	private final EvaluatorInformation information;

	public AbstractEvaluator(String id, String name, EvaluatorType type,
			String description) {
		super();
		this.information = new EvaluatorInformation(id, name, type, description);
	}

	abstract protected FileMetrics readFileResults(HashId hashId)
			throws EvaluationStoreException;

	abstract protected boolean hasFileResults(HashId hashId)
			throws EvaluationStoreException;

	abstract protected void storeFileResults(AnalysisRun analysisRun,
			CodeAnalysis fileAnalysis, GenericFileMetrics metrics)
			throws EvaluationStoreException;

	abstract protected DirectoryMetrics readDirectoryResults(HashId hashId)
			throws EvaluationStoreException;

	abstract protected boolean hasDirectoryResults(HashId hashId)
			throws EvaluationStoreException;

	abstract protected void storeDirectoryResults(AnalysisRun analysisRun,
			AnalysisFileTree directoryNode, GenericDirectoryMetrics metrics)
			throws EvaluationStoreException;

	abstract protected ProjectMetrics readProjectResults(UUID projectUUID,
			UUID runUUID) throws EvaluationStoreException;

	abstract protected boolean hasProjectResults(UUID projectUUID, UUID runUUID)
			throws EvaluationStoreException;

	abstract protected void storeProjectResults(AnalysisRun analysisRun,
			AnalysisFileTree directoryNode, GenericProjectMetrics metrics)
			throws EvaluationStoreException;

	protected EvaluatorStore getEvaluatorStore() {
		return store;
	}

	protected FileStore getFileStore() {
		return fileStore;
	}

	protected DirectoryStore getDirectoryStore() {
		return directoryStore;
	}

	@Override
	public final EvaluatorInformation getInformation() {
		return information;
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
	abstract protected FileMetrics processFile(AnalysisRun analysisRun,
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
	abstract protected DirectoryMetrics processDirectory(
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
	abstract protected DirectoryMetrics processProject(AnalysisRun analysisRun,
			boolean enableReevaluation) throws InterruptedException,
			EvaluationStoreException;

	@Override
	public final void evaluate(AnalysisRun analysisRun,
			boolean enableReevaluation) throws InterruptedException,
			EvaluationStoreException {
		// process files and directories
		processTree(analysisRun, analysisRun.getFileTree(), enableReevaluation);
		// process project as whole
		processProject(analysisRun, enableReevaluation);
	}

	private void processTree(AnalysisRun analysisRun, AnalysisFileTree tree,
			boolean enableReevaluation) throws InterruptedException {
		try {
			processNode(analysisRun, tree, enableReevaluation);
		} catch (FileStoreException | DirectoryStoreException
				| ArrayStoreException | EvaluationStoreException e) {
			logger.error("Evaluation result could not be stored.", e);
		} catch (UniversalSyntaxTreeEvaluationException e) {
			logger.error("Evaluation failed.", e);
		}
	}

	private void processNode(AnalysisRun analysisRun, AnalysisFileTree node,
			boolean enableReevaluation) throws FileStoreException,
			InterruptedException, UniversalSyntaxTreeEvaluationException,
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
	private void processAsFile(AnalysisRun analysisRun,
			AnalysisFileTree fileNode, boolean enableReevaluation)
			throws FileStoreException, InterruptedException,
			UniversalSyntaxTreeEvaluationException, EvaluationStoreException {
		HashId hashId = fileNode.getHashId();
		if (fileStore.wasAnalyzed(hashId)) {
			List<CodeAnalysis> fileAnalyses = fileStore.loadAnalyses(hashId);
			for (CodeAnalysis fileAnalysis : fileAnalyses) {
				if ((!hasFileResults(hashId)) || (enableReevaluation)) {
					FileMetrics fileResults = processFile(analysisRun,
							fileAnalysis);
					if (fileResults != null) {
						GenericFileMetrics metrics = new GenericFileMetrics(
								getInformation().getId(), hashId,
								fileResults.getSourceCodeLocation(),
								new Date(), fileResults.getParameters(),
								fileResults.getValues());
						storeFileResults(analysisRun, fileAnalysis, metrics);
					}
				} else {
					FileMetrics fileResults = readFileResults(hashId);
					if (fileResults != null) {
						storeMetricsInBigTable(analysisRun, fileAnalysis, this,
								fileResults);
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
			AnalysisFileTree directoryNode, boolean enableReevaluation)
			throws FileStoreException, InterruptedException,
			UniversalSyntaxTreeEvaluationException, DirectoryStoreException,
			EvaluationStoreException {
		HashId hashId = directoryNode.getHashId();
		if (directoryStore.isAvailable(hashId)) {
			for (AnalysisFileTree child : directoryNode.getChildren()) {
				processNode(analysisRun, child, enableReevaluation);
			}
			if ((!store.hasDirectoryResults(hashId, getInformation().getId()))
					|| (enableReevaluation)) {
				DirectoryMetrics directoryResults = processDirectory(
						analysisRun, directoryNode);
				if (directoryResults != null) {
					GenericDirectoryMetrics metrics = new GenericDirectoryMetrics(
							getInformation().getId(), hashId, new Date(),
							directoryResults.getParameters(),
							directoryResults.getValues());
					storeDirectoryResults(analysisRun, directoryNode, metrics);
				}
			} else {
				DirectoryMetrics directoryResults = readDirectoryResults(hashId);
				if (directoryResults != null) {
					storeMetricsInBigTable(analysisRun, directoryNode, this,
							directoryResults);
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

	protected final void storeMetricsInBigTable(AnalysisRun analysisRun,
			CodeAnalysis fileAnalysis, AbstractEvaluator evaluator,
			FileMetrics fileResults) {
		GenericFileMetrics metrics = new GenericFileMetrics(getInformation()
				.getId(), fileResults.getHashId(),
				fileResults.getSourceCodeLocation(), new Date(),
				fileResults.getParameters(), fileResults.getValues());
		getEvaluatorStore().storeMetricsInBigTable(analysisRun, fileAnalysis,
				metrics);
	}

	protected final void storeMetricsInBigTable(AnalysisRun analysisRun,
			AnalysisFileTree directoryNode, AbstractEvaluator evaluator,
			DirectoryMetrics directoryResults) {
		GenericDirectoryMetrics metrics = new GenericDirectoryMetrics(
				getInformation().getId(), directoryResults.getHashId(),
				new Date(), directoryResults.getParameters(),
				directoryResults.getValues());
		getEvaluatorStore().storeMetricsInBigTable(analysisRun, directoryNode,
				metrics);
	}

}
