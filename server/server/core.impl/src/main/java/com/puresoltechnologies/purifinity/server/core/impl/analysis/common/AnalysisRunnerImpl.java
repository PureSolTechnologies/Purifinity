package com.puresoltechnologies.purifinity.server.core.impl.analysis.common;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Matcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.commons.misc.HashAlgorithm;
import com.puresoltechnologies.commons.misc.HashCodeGenerator;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.misc.HashUtilities;
import com.puresoltechnologies.commons.misc.StopWatch;
import com.puresoltechnologies.commons.trees.TreeVisitor;
import com.puresoltechnologies.commons.trees.TreeWalker;
import com.puresoltechnologies.commons.trees.WalkingAction;
import com.puresoltechnologies.parsers.source.RepositoryLocation;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectException;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.FileStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisStoreService;
import com.puresoltechnologies.purifinity.server.core.api.analysis.FileStoreService;

public class AnalysisRunnerImpl implements Callable<Boolean> {

	private static final Logger logger = LoggerFactory
			.getLogger(AnalysisRunnerImpl.class);

	private static final int NUMBER_OF_PARALLEL_THREADS = Runtime.getRuntime()
			.availableProcessors();
	private static final int WAITTIME_IN_SECONDS_FOR_THREAD_POLL = 1000;

	private AnalysisFileTree fileTree = null;
	private AnalysisRunInformation analysisRun = null;
	private StopWatch stopWatch = null;

	private final Map<SourceCodeLocation, AnalysisInformation> analyzedFiles = new HashMap<>();
	private final Map<HashId, SourceCodeLocation> sourceCodeLocations = new HashMap<>();

	private final UUID projectUUID;
	private final AnalysisStoreService analysisStore;
	private final FileStoreService fileStore;
	private final FileSearchConfiguration searchConfig;

	public AnalysisRunnerImpl(AnalysisStoreService analysisStore,
			FileStoreService fileStore, UUID analysisProjectUUID)
			throws AnalysisStoreException {
		super();
		this.analysisStore = analysisStore;
		this.fileStore = fileStore;
		this.projectUUID = analysisProjectUUID;
		AnalysisProjectSettings settings = analysisStore
				.readAnalysisProjectSettings(analysisProjectUUID);
		searchConfig = settings.getFileSearchConfiguration();
	}

	@Override
	public Boolean call() throws Exception {
		if (stopWatch != null) {
			throw new IllegalStateException("Runner was run already.");
		}
		stopWatch = new StopWatch();
		stopWatch.start();
		boolean analysisSuccessful = analyzeFiles();
		stopWatch.stop();
		if (analysisSuccessful) {
			analysisRun = analysisStore.createAnalysisRun(projectUUID,
					stopWatch.getStartTime(), stopWatch.getMilliseconds(), "",
					searchConfig);
			buildCodeLocationTree();
			analysisStore.storeAnalysisFileTree(projectUUID,
					analysisRun.getRunUUID(), fileTree);
			return true;
		} else {
			return false;
		}
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
		Map<SourceCodeLocation, Future<AnalysisInformation>> futures = startAllAnalysisThreads();
		return waitForAnalysisThreads(futures);
	}

	/**
	 * This method starts all analysis threads.
	 * 
	 * @return A {@link List} of {@link Future} objects is returned representing
	 *         the running (or finished) analysis threads.
	 * @throws AnalysisStoreException
	 */
	private Map<SourceCodeLocation, Future<AnalysisInformation>> startAllAnalysisThreads()
			throws AnalysisStoreException {
		AnalysisProjectSettings analysisProjectSettings = analysisStore
				.readAnalysisProjectSettings(projectUUID);
		RepositoryLocation repositoryLocation = RepositoryLocationCreator
				.createFromSerialization(analysisProjectSettings
						.getRepositoryLocation());
		List<SourceCodeLocation> sourceFiles = repositoryLocation
				.getSourceCodes(searchConfig);

		ExecutorService threadPool = Executors
				.newFixedThreadPool(NUMBER_OF_PARALLEL_THREADS);
		Map<SourceCodeLocation, Future<AnalysisInformation>> futures = new HashMap<SourceCodeLocation, Future<AnalysisInformation>>();
		for (int index = 0; index < sourceFiles.size(); index++) {
			SourceCodeLocation sourceFile = sourceFiles.get(index);
			Callable<AnalysisInformation> callable = new AnalysisRunCallable(
					fileStore, sourceFile);
			Future<AnalysisInformation> future = threadPool.submit(callable);
			futures.put(sourceFile, future);
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
	private boolean waitForAnalysisThreads(
			Map<SourceCodeLocation, Future<AnalysisInformation>> futures) {
		boolean interrupted = false;
		while (futures.size() > 0) {
			try {
				Iterator<SourceCodeLocation> iterator = futures.keySet()
						.iterator();
				while (iterator.hasNext()) {
					SourceCodeLocation sourceCodeLocation = iterator.next();
					Future<AnalysisInformation> future = futures
							.get(sourceCodeLocation);
					if (future.isDone()) {
						iterator.remove();
						try {
							if (!future.isCancelled()) {
								AnalysisInformation result = future.get();
								analyzedFiles.put(sourceCodeLocation, result);
								sourceCodeLocations.put(result.getHashId(),
										sourceCodeLocation);
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
				for (Entry<SourceCodeLocation, Future<AnalysisInformation>> futureEntries : futures
						.entrySet()) {
					futureEntries.getValue().cancel(true);
				}
			}
		}
		return (!interrupted);
	}

	private void buildCodeLocationTree() throws AnalysisStoreException {
		AnalysisFileTree intermediate = createIntermediateTree();
		createFinalTree(intermediate);
	}

	/**
	 * This method creates an intermediate tree with {@link HashId} objects as
	 * present and a classification of file or directory. From this intermediate
	 * tree the final tree can be generated.
	 * 
	 * @throws AnalysisStoreException
	 */
	private AnalysisFileTree createIntermediateTree()
			throws AnalysisStoreException {
		AnalysisProjectSettings analysisProjectSettings = analysisStore
				.readAnalysisProjectSettings(projectUUID);
		RepositoryLocation repositoryLocation = RepositoryLocationCreator
				.createFromSerialization(analysisProjectSettings
						.getRepositoryLocation());
		AnalysisFileTree intermediate = new AnalysisFileTree(null,
				repositoryLocation.getHumanReadableLocationString(), null,
				false, null, null);
		for (SourceCodeLocation sourceCodeLocation : analyzedFiles.keySet()) {
			addToIntermediateTree(intermediate, sourceCodeLocation);
		}
		return intermediate;
	}

	private void addToIntermediateTree(AnalysisFileTree rootNode,
			SourceCodeLocation location) {
		AnalysisInformation analysis = analyzedFiles.get(location);
		HashId hashId = analysis.getHashId();
		String internalLocation = location.getInternalLocation();
		String[] directories = internalLocation.split(Matcher
				.quoteReplacement(File.separator));
		AnalysisFileTree node = rootNode;
		for (int i = 0; i < directories.length; i++) {
			String directory = directories[i];
			AnalysisFileTree child = node.getChild(directory);
			if (child == null) {
				boolean isFile = (i == directories.length - 1);
				if (isFile) {
					List<AnalysisInformation> analyses = new ArrayList<AnalysisInformation>();
					analyses.add(analysis);
					child = new AnalysisFileTree(node, directory, hashId,
							isFile, location, analyses);
				} else {
					child = new AnalysisFileTree(node, directory, null, isFile,
							null, null);
				}
			}
			node = child;
		}
	}

	private void createFinalTree(AnalysisFileTree intermediate) {
		Map<File, HashId> hashes = generateModuleHashes(intermediate);
		fileTree = new AnalysisFileTree(null, intermediate.getName(),
				hashes.get(intermediate.getPathFile(false)), false,
				sourceCodeLocations.get(intermediate.getHashId()), null);
		for (AnalysisFileTree child : intermediate.getChildren()) {
			addToFinalTree(fileTree, child, hashes);
		}
	}

	private void addToFinalTree(AnalysisFileTree parentNode,
			AnalysisFileTree refNode, Map<File, HashId> hashes) {
		AnalysisFileTree newNode = new AnalysisFileTree(parentNode,
				refNode.getName(), hashes.get(refNode.getPathFile(false)),
				refNode.isFile(), sourceCodeLocations.get(refNode.getHashId()),
				refNode.isFile() ? refNode.getAnalyses() : null);
		for (AnalysisFileTree child : refNode.getChildren()) {
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
	private Map<File, HashId> generateModuleHashes(AnalysisFileTree intermediate) {
		final Map<File, HashId> hashes = new HashMap<File, HashId>();
		TreeVisitor<AnalysisFileTree> visitor = new TreeVisitor<AnalysisFileTree>() {

			@Override
			public WalkingAction visit(AnalysisFileTree tree) {
				if (tree.getHashId() != null) {
					hashes.put(tree.getPathFile(false), tree.getHashId());
				} else {
					List<String> hashList = new ArrayList<String>();
					for (AnalysisFileTree child : tree.getChildren()) {
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

	public AnalysisRun getAnalysisRun() throws AnalysisProjectException {
		return new AnalysisRun(analysisRun, fileTree);
	}

}
