package com.puresoltechnologies.purifinity.framework.analysis.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.commons.misc.HashAlgorithm;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.misc.HashUtilities;
import com.puresoltechnologies.commons.trees.api.TreeVisitor;
import com.puresoltechnologies.commons.trees.api.TreeWalker;
import com.puresoltechnologies.commons.trees.api.WalkingAction;
import com.puresoltechnologies.parsers.api.source.RepositoryLocation;
import com.puresoltechnologies.parsers.api.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectException;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRunner;
import com.puresoltechnologies.purifinity.analysis.domain.AnalyzedCode;
import com.puresoltechnologies.purifinity.analysis.domain.HashIdFileTree;
import com.puresoltechnologies.purifinity.framework.commons.utils.StopWatch;
import com.puresoltechnologies.purifinity.framework.commons.utils.data.HashCodeGenerator;
import com.puresoltechnologies.purifinity.framework.commons.utils.progress.AbstractProgressObservable;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreFactory;
import com.puresoltechnologies.purifinity.framework.store.api.FileStoreException;

public class AnalysisRunnerImpl extends AbstractProgressObservable<AnalysisRun>
		implements AnalysisRunner {

	private static final Logger logger = LoggerFactory
			.getLogger(AnalysisRunnerImpl.class);

	private static final int NUMBER_OF_PARALLEL_THREADS = Runtime.getRuntime()
			.availableProcessors();
	private static final int WAITTIME_IN_SECONDS_FOR_THREAD_POLL = 1000;

	private final List<AnalyzedCode> analyzedFiles = new ArrayList<>();
	private final List<AnalyzedCode> failedSources = new ArrayList<>();
	private HashIdFileTree fileTree;
	private FileSearchConfiguration searchConfig;
	private long timeOfRun;

	private final UUID analysisProjectUUID;
	private final UUID uuid;
	private final Date creationTime;
	private final AnalysisStore analysisStore;

	public AnalysisRunnerImpl(UUID analysisProjectUUID) {
		super();
		this.analysisProjectUUID = analysisProjectUUID;
		uuid = UUID.randomUUID();
		creationTime = new Date();
		analysisStore = AnalysisStoreFactory.getFactory().getInstance();
	}

	@Override
	public Boolean call() throws Exception {
		try {
			reset();
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			if (analyzeFiles()) {
				AnalysisProject analysisProject = analysisStore
						.readAnalysisProjectInformation(analysisProjectUUID);
				UUID projectUUID = analysisProject.getInformation().getUUID();
				analysisStore.saveAnalysisRunInformation(projectUUID, uuid,
						creationTime, timeOfRun);
				buildCodeLocationTree();
				analysisStore.storeFileTree(fileTree);
				stopWatch.stop();
				timeOfRun = stopWatch.getMilliseconds();
				analysisStore.storeAnalysisResultInformation(projectUUID, uuid,
						analyzedFiles, failedSources, fileTree);
				fireDone("Finished successfully.", true);
				return true;
			} else {
				fireDone("Run aborted.", false);
				return false;
			}
		} catch (RuntimeException e) {
			fireDone("Finished with runtime exception: '" + e.getMessage()
					+ "'", false);
			throw e;
		} catch (Exception e) {
			fireDone("Finished with error: '" + e.getMessage() + "'", false);
			throw e;
		}
	}

	/**
	 * This method resets the values for a reanalysis.
	 */
	private void reset() {
		analyzedFiles.clear();
		failedSources.clear();
	}

	/**
	 * This method starts all evaluation threads and puts them into the thread
	 * pool. Afterwards the finalization is awaited.
	 * 
	 * @return True is returned if the analysis was successful. False is
	 *         returned otherwise.
	 * @throws FileStoreException
	 *             is thrown if the file store had an exception.
	 * @throws AnalysisStoreException
	 */
	private boolean analyzeFiles() throws AnalysisStoreException {
		List<Future<AnalyzedCode>> futures = startAllAnalysisThreads();
		return waitForAnalysisThreads(futures);
	}

	/**
	 * This method starts all analysis threads.
	 * 
	 * @return A {@link List} of {@link Future} objects is returned representing
	 *         the running (or finished) analysis threads.
	 * @throws AnalysisStoreException
	 */
	private List<Future<AnalyzedCode>> startAllAnalysisThreads()
			throws AnalysisStoreException {
		AnalysisProject analysisProject = analysisStore
				.readAnalysisProjectInformation(analysisProjectUUID);
		RepositoryLocation repositoryLocation = analysisProject.getSettings()
				.getRepositoryLocation();
		repositoryLocation.setCodeSearchConfiguration(searchConfig);
		List<SourceCodeLocation> sourceFiles = repositoryLocation
				.getSourceCodes();

		ExecutorService threadPool = Executors
				.newFixedThreadPool(NUMBER_OF_PARALLEL_THREADS);
		fireStarted("Analyze files", sourceFiles.size());
		List<Future<AnalyzedCode>> futures = new ArrayList<Future<AnalyzedCode>>();
		for (int index = 0; index < sourceFiles.size(); index++) {
			SourceCodeLocation sourceFile = sourceFiles.get(index);
			Callable<AnalyzedCode> callable = new AnalysisRunCallable(
					sourceFile);
			futures.add(threadPool.submit(callable));
		}
		threadPool.shutdown();
		return futures;
	}

	/**
	 * This method waits for all analysis threads to be finished.
	 * 
	 * @param futures
	 *            is a {@link List} of {@link Future} object which represent the
	 *            running (or finished) threads. All futures need to signal done
	 *            for this method to return.
	 * @return True is returned if the analysis was successful. False is
	 *         returned otherwise.
	 */
	private boolean waitForAnalysisThreads(List<Future<AnalyzedCode>> futures) {
		boolean interrupted = false;
		while (futures.size() > 0) {
			try {
				Iterator<Future<AnalyzedCode>> iterator = futures.iterator();
				while (iterator.hasNext()) {
					Future<AnalyzedCode> future = iterator.next();
					if (future.isDone()) {
						iterator.remove();
						fireUpdateWork("Finished a file.", 1);
						try {
							if (!future.isCancelled()) {
								AnalyzedCode result = future.get();
								if (result.wasAnalyzed()) {
									analyzedFiles.add(result);
								} else {
									failedSources.add(result);
								}
								fireUpdateWork(
										"Finished '"
												+ result.getSourceLocation()
														.getHumanReadableLocationString()
												+ "'.", 0);
							}
						} catch (CancellationException e) {
							logger.debug("Job was cancelled.", e);
						} catch (ExecutionException e) {
							logger.error(
									"Job was aborted with execution exception.",
									e);
						}
					}
				}
				Thread.sleep(WAITTIME_IN_SECONDS_FOR_THREAD_POLL);
			} catch (InterruptedException e) {
				logger.warn("Job was interrupted.", e);
				interrupted = true;
				for (Future<AnalyzedCode> future : futures) {
					future.cancel(true);
				}
			}
		}
		return (!interrupted);
	}

	private void buildCodeLocationTree() throws AnalysisStoreException {
		HashIdFileTree intermediate = createIntermediateTree();
		createFinalTree(intermediate);
	}

	/**
	 * This method creates an intermediate tree with {@link HashId} objects as
	 * present and a classification of file or directory. From this intermediate
	 * tree the final tree can be generated.
	 * 
	 * @throws AnalysisStoreException
	 */
	private HashIdFileTree createIntermediateTree()
			throws AnalysisStoreException {
		AnalysisProject analysisProject = analysisStore
				.readAnalysisProjectInformation(analysisProjectUUID);
		RepositoryLocation repositoryLocation = analysisProject.getSettings()
				.getRepositoryLocation();
		HashIdFileTree intermediate = new HashIdFileTree(null,
				repositoryLocation.getHumanReadableLocationString(), null,
				false);
		for (AnalyzedCode analyzedFile : analyzedFiles) {
			addToIntermediateTree(intermediate,
					analyzedFile.getSourceLocation(), analyzedFile.getHashId());
		}
		for (AnalyzedCode failedFile : failedSources) {
			addToIntermediateTree(intermediate, failedFile.getSourceLocation(),
					failedFile.getHashId());
		}
		return intermediate;
	}

	private void addToIntermediateTree(HashIdFileTree intermediate,
			SourceCodeLocation location, HashId hashId) {
		String internalLocation = location.getInternalLocation();
		String[] directories = internalLocation.split("/");
		HashIdFileTree node = intermediate;
		for (int i = 0; i < directories.length; i++) {
			String directory = directories[i];
			HashIdFileTree child = node.getChild(directory);
			if (child == null) {
				boolean isFile = (i == directories.length - 1);
				if (isFile) {
					child = new HashIdFileTree(node, directory, hashId, isFile);
				} else {
					child = new HashIdFileTree(node, directory, null, isFile);
				}
			}
			node = child;
		}
	}

	private void createFinalTree(HashIdFileTree intermediate) {
		Map<File, HashId> hashes = generateModuleHashes(intermediate);
		fileTree = new HashIdFileTree(null, intermediate.getName(),
				hashes.get(intermediate.getPathFile(false)), false);
		for (HashIdFileTree child : intermediate.getChildren()) {
			addToFinalTree(fileTree, child, hashes);
		}
	}

	private void addToFinalTree(HashIdFileTree parentNode,
			HashIdFileTree refNode, Map<File, HashId> hashes) {
		HashIdFileTree newNode = new HashIdFileTree(parentNode,
				refNode.getName(), hashes.get(refNode.getPathFile(false)),
				refNode.isFile());
		for (HashIdFileTree child : refNode.getChildren()) {
			addToFinalTree(newNode, child, hashes);
		}
	}

	/**
	 * This method runs the intermediate tree backwards and generates module
	 * hashes.
	 * 
	 * @param intermediate
	 * @return
	 */
	private Map<File, HashId> generateModuleHashes(HashIdFileTree intermediate) {
		final Map<File, HashId> hashes = new HashMap<File, HashId>();
		TreeVisitor<HashIdFileTree> visitor = new TreeVisitor<HashIdFileTree>() {

			@Override
			public WalkingAction visit(HashIdFileTree tree) {
				if (tree.getHashId() != null) {
					hashes.put(tree.getPathFile(false), tree.getHashId());
				} else {
					List<String> hashList = new ArrayList<String>();
					for (HashIdFileTree child : tree.getChildren()) {
						hashList.add(hashes.get(child.getPathFile(false))
								.toString());
					}
					Collections.sort(hashList);
					StringBuilder joinedHash = new StringBuilder();
					for (String hash : hashList) {
						if (joinedHash.length() > 0) {
							joinedHash.append(',');
						}
						joinedHash.append(hash);
					}
					HashAlgorithm algorithm = HashUtilities
							.getDefaultMessageDigestAlgorithm();
					String hash = HashCodeGenerator.get(algorithm,
							joinedHash.toString());
					hashes.put(tree.getPathFile(false), new HashId(algorithm,
							hash));
				}
				return WalkingAction.PROCEED;
			}
		};
		TreeWalker.walkBackward(visitor, intermediate);
		return hashes;
	}

	@Override
	public AnalysisRun getAnalysisRun() throws AnalysisProjectException {
		try {
			return analysisStore.loadAnalysisRun(analysisProjectUUID, uuid);
		} catch (AnalysisStoreException e) {
			logger.error("Could not load analysis.", e);
			throw new AnalysisProjectException("Could not load analysis.");
		}
	}

}
