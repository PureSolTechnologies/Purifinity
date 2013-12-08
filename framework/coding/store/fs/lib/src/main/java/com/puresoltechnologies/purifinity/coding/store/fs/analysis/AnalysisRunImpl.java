package com.puresoltechnologies.purifinity.coding.store.fs.analysis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.commons.trees.impl.TreePrinter;
import com.puresoltechnologies.commons.trees.impl.TreeVisitor;
import com.puresoltechnologies.commons.trees.impl.TreeWalker;
import com.puresoltechnologies.commons.trees.impl.WalkingAction;
import com.puresoltechnologies.commons.utils.DirectoryUtilities;
import com.puresoltechnologies.commons.utils.FileSearchConfiguration;
import com.puresoltechnologies.commons.utils.HashAlgorithm;
import com.puresoltechnologies.commons.utils.HashId;
import com.puresoltechnologies.commons.utils.StopWatch;
import com.puresoltechnologies.commons.utils.data.HashCodeGenerator;
import com.puresoltechnologies.commons.utils.progress.AbstractProgressObservable;
import com.puresoltechnologies.parsers.impl.source.CodeLocation;
import com.puresoltechnologies.purifinity.coding.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.coding.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.coding.analysis.api.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.coding.analysis.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.coding.analysis.api.AnalyzedCode;
import com.puresoltechnologies.purifinity.coding.analysis.api.DirectoryStore;
import com.puresoltechnologies.purifinity.coding.analysis.api.DirectoryStoreException;
import com.puresoltechnologies.purifinity.coding.analysis.api.DirectoryStoreFactory;
import com.puresoltechnologies.purifinity.coding.analysis.api.FileStoreException;
import com.puresoltechnologies.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresoltechnologies.purifinity.coding.analysis.api.RepositoryLocation;

/**
 * This class is an implementation of {@link AnalysisRun}.
 * 
 * @author Rick-Rainer Ludwig
 */
public class AnalysisRunImpl extends AbstractProgressObservable<AnalysisRun>
		implements AnalysisRun {

	private static final long serialVersionUID = 6413809660830217670L;

	private static final Logger logger = LoggerFactory
			.getLogger(AnalysisRunImpl.class);

	private static final String DIRECTORY_FLAG = ".analysis_run";

	private static final String ANALYZED_FILES_FILE = "analyzed_files.persist";
	private static final String FAILED_FILES_FILE = "failed_files.persist";
	private static final String TREE_FILE = "code_tree.persist";
	private static final String SEARCH_CONFIGURATION_FILE = "search_configuration.persist";
	private static final String ANALYSIS_RUN_PROPERTIES_FILE = "analysis_run.properties";
	private static final String ANALYSIS_INFORMATION_FILE = "analysis_information.persist";
	private static final String REPOSITORY_LOCATION__FILE = "repository_location.persist";

	private static final int NUMBER_OF_PARALLEL_THREADS = Runtime.getRuntime()
			.availableProcessors();
	private static final int WAITTIME_IN_SECONDS_FOR_THREAD_POLL = 1000;

	/**
	 * This method returns the storage directory of the given analysis run.
	 * 
	 * @param analysisRun
	 * @return
	 */
	public static File getStorageDirectory(AnalysisRun analysisRun) {
		File analysisStorageDirectory = AnalysisStoreImpl
				.getStorageDirectory(analysisRun.getInformation()
						.getAnalysisProject().getInformation().getUUID());
		return new File(analysisStorageDirectory, analysisRun.getInformation()
				.getUUID().toString());
	}

	/**
	 * This method opens an existing project analyzer via its workspace
	 * directory.
	 * 
	 * @param runDirectory
	 *            is the directory where the persisted results can be found.
	 * @return
	 * @throws DirectoryStoreException
	 */
	public static AnalysisRun open(File runDirectory)
			throws AnalysisStoreException {
		AnalysisRunImpl projectAnalyser = new AnalysisRunImpl(runDirectory);
		projectAnalyser.open();
		return projectAnalyser;
	}

	/**
	 * This method creates a new project analyzer with a new workspace
	 * associated.
	 * 
	 * @param projectDirectory
	 *            is the directory which is to scan for files and to be
	 *            analyzed.
	 * @param runDirectory
	 *            is the directory to put the persisted results to.
	 * @throws AnalysisStoreException
	 * @throws IOException
	 */
	public static AnalysisRun create(File runDirectory,
			AnalysisProject analysisProject, UUID uuid,
			RepositoryLocation repositorySource,
			FileSearchConfiguration searchConfiguration)
			throws AnalysisStoreException {
		AnalysisRunImpl projectAnalyser = new AnalysisRunImpl(runDirectory);
		projectAnalyser.create(repositorySource, analysisProject, uuid,
				searchConfiguration);
		return projectAnalyser;
	}

	private final File runDirectory;

	private final List<AnalyzedCode> analyzedFiles = new ArrayList<>();
	private final List<AnalyzedCode> failedSources = new ArrayList<>();

	private HashIdFileTree fileTree;

	private FileSearchConfiguration searchConfig;

	private AnalysisProject analysisProject;
	private UUID uuid;
	private Date creationTime;
	private long timeOfRun;
	private RepositoryLocation repositoryLocation;

	/**
	 * This constructor is used to create a new analysis run. All setup
	 * information is set and is immutable.
	 * 
	 * @param name
	 * @param runDirectory
	 * @param searchConfiguration
	 */
	private AnalysisRunImpl(File runDirectory) {
		super();
		this.runDirectory = runDirectory;
	}

	/**
	 * This methods creates a new project directory.
	 * 
	 * @param repositorySource
	 * @param analysisProject
	 * @param uuid
	 * @param searchConfiguration
	 * @return
	 * @throws AnalysisStoreException
	 */
	void create(RepositoryLocation repositorySource,
			AnalysisProject analysisProject, UUID uuid,
			FileSearchConfiguration searchConfiguration)
			throws AnalysisStoreException {
		try {
			this.repositoryLocation = repositorySource;
			this.analysisProject = analysisProject;
			this.uuid = uuid;
			this.searchConfig = searchConfiguration;
			this.creationTime = new Date();
			this.timeOfRun = 0;
			DirectoryUtilities.checkAndCreateDirectory(runDirectory);
			new File(runDirectory, DIRECTORY_FLAG).createNewFile();
			saveAnalysisRunInformation();
			writeSearchConfiguration();
			storeAnalysisResultInformation();
		} catch (IOException e) {
			throw new AnalysisStoreException("Could not create analysis run!",
					e);
		}
	}

	/**
	 * This method opens the project directory and loads all information files.
	 * 
	 * @return
	 * @throws DirectoryStoreException
	 */
	void open() throws AnalysisStoreException {
		try {
			if (!runDirectory.exists()) {
				throw new IOException("Analysis run directory '" + runDirectory
						+ "' does not exist!");
			}
			loadAnalysisRunInformation();
			readSearchConfiguration();
			loadAnalysisResultInformation();
		} catch (IOException e) {
			throw new AnalysisStoreException("Could not open analysis run!", e);
		}
	}

	private void readSearchConfiguration() throws IOException {
		try {
			searchConfig = new FileSearchConfiguration();
			FileInputStream fileInputStream = new FileInputStream(new File(
					runDirectory, SEARCH_CONFIGURATION_FILE));
			try {
				ObjectInputStream objectOutputStream = new ObjectInputStream(
						fileInputStream);
				try {
					FileSearchConfiguration config = (FileSearchConfiguration) objectOutputStream
							.readObject();
					searchConfig.setLocationExcludes(config
							.getLocationExcludes());
					searchConfig.setLocationIncludes(config
							.getLocationIncludes());
					searchConfig.setFileExcludes(config.getFileExcludes());
					searchConfig.setFileIncludes(config.getFileIncludes());
				} finally {
					objectOutputStream.close();
				}
			} finally {
				fileInputStream.close();
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	private void writeSearchConfiguration() throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream(new File(
				runDirectory, SEARCH_CONFIGURATION_FILE));
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					fileOutputStream);
			try {
				objectOutputStream.writeObject(searchConfig);
			} finally {
				objectOutputStream.close();
			}
		} finally {
			fileOutputStream.close();
		}
	}

	private void loadAnalysisRunInformation() throws IOException {
		Properties properties = new Properties();
		FileReader reader = new FileReader(new File(runDirectory,
				ANALYSIS_RUN_PROPERTIES_FILE));
		try {
			properties.load(reader);
			uuid = UUID.fromString(properties.get("uuid").toString());
			creationTime = new Date(Long.valueOf(properties
					.get("creation.time").toString()));
			timeOfRun = Long.valueOf(properties.get("run.duration").toString());
		} finally {
			reader.close();
		}
		analysisProject = restore(new File(runDirectory,
				ANALYSIS_INFORMATION_FILE));
		repositoryLocation = restore(new File(runDirectory,
				REPOSITORY_LOCATION__FILE));
	}

	private void saveAnalysisRunInformation() throws IOException {
		Properties properties = new Properties();
		properties.put("uuid", uuid.toString());
		properties.put("creation.time", String.valueOf(creationTime.getTime()));
		properties.put("run.duration", String.valueOf(timeOfRun));
		File propertiesFile = new File(runDirectory,
				ANALYSIS_RUN_PROPERTIES_FILE);
		FileWriter writer = new FileWriter(propertiesFile);
		try {
			properties.store(writer, "Analysis run properties");
		} finally {
			writer.close();
		}
		persist(repositoryLocation, new File(runDirectory,
				REPOSITORY_LOCATION__FILE));
		persist(analysisProject, new File(runDirectory,
				ANALYSIS_INFORMATION_FILE));
	}

	private void loadAnalysisResultInformation() throws IOException {
		@SuppressWarnings("unchecked")
		List<AnalyzedCode> analyzed = (List<AnalyzedCode>) restore(new File(
				runDirectory, ANALYZED_FILES_FILE));
		analyzedFiles.addAll(analyzed);
		@SuppressWarnings("unchecked")
		List<AnalyzedCode> failed = (List<AnalyzedCode>) restore(new File(
				runDirectory, FAILED_FILES_FILE));
		failedSources.addAll(failed);
		fileTree = (HashIdFileTree) restore(new File(runDirectory, TREE_FILE));
	}

	private void storeAnalysisResultInformation() {
		try {
			persist(analyzedFiles, new File(runDirectory, ANALYZED_FILES_FILE));
			persist(failedSources, new File(runDirectory, FAILED_FILES_FILE));
			persist(fileTree, new File(runDirectory, TREE_FILE));
			if (fileTree != null) {
				File contentFile = new File(runDirectory, "content.txt");
				PrintStream stream = new PrintStream(contentFile);
				try {
					TreePrinter printer = new TreePrinter(stream);
					printer.println(fileTree);
				} finally {
					stream.close();
				}
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public Boolean call() throws Exception {
		try {
			reset();
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			if (analyzeFiles()) {
				saveAnalysisRunInformation();
				buildCodeLocationTree();
				storeAnalysisResultInformation();
				storeModules();
				stopWatch.stop();
				timeOfRun = stopWatch.getMilliseconds();
				storeAnalysisResultInformation();
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
	 */
	private boolean analyzeFiles() throws FileStoreException {
		List<Future<AnalyzedCode>> futures = startAllAnalysisThreads();
		return waitForAnalysisThreads(futures);
	}

	/**
	 * This method starts all analysis threads.
	 * 
	 * @return A {@link List} of {@link Future} objects is returned representing
	 *         the running (or finished) analysis threads.
	 */
	private List<Future<AnalyzedCode>> startAllAnalysisThreads() {
		repositoryLocation.setCodeSearchConfiguration(searchConfig);
		List<CodeLocation> sourceFiles = repositoryLocation.getSourceCodes();

		ExecutorService threadPool = Executors
				.newFixedThreadPool(NUMBER_OF_PARALLEL_THREADS);
		fireStarted("Analyze files", sourceFiles.size());
		List<Future<AnalyzedCode>> futures = new ArrayList<Future<AnalyzedCode>>();
		for (int index = 0; index < sourceFiles.size(); index++) {
			CodeLocation sourceFile = sourceFiles.get(index);
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

	private void buildCodeLocationTree() {
		HashIdFileTree intermediate = createIntermediateTree();
		createFinalTree(intermediate);
	}

	private void storeModules() {
		DirectoryStoreFactory moduleStoreFactory = DirectoryStoreFactory
				.getFactory();
		final DirectoryStore moduleStore = moduleStoreFactory.getInstance();
		TreeVisitor<HashIdFileTree> visitor = new TreeVisitor<HashIdFileTree>() {

			@Override
			public WalkingAction visit(HashIdFileTree tree) {
				try {
					if (!moduleStore.isAvailable(tree.getHashId())) {
						List<HashId> files = new ArrayList<HashId>();
						List<HashId> directories = new ArrayList<HashId>();
						for (HashIdFileTree child : tree.getChildren()) {
							if (child.isFile()) {
								files.add(child.getHashId());
							} else {
								directories.add(child.getHashId());
							}
						}
						Collections.sort(files);
						Collections.sort(directories);
						moduleStore.createPackage(tree.getHashId(), files,
								directories);
					}
					return WalkingAction.PROCEED;
				} catch (DirectoryStoreException e) {
					logger.error("Could not create module store entry for '"
							+ tree.getHashId() + "'.");
					return WalkingAction.ABORT;
				}
			}
		};
		TreeWalker.walk(visitor, fileTree);
	}

	/**
	 * This method creates an intermediate tree with {@link HashId} objects as
	 * present and a classification of file or directory. From this intermediate
	 * tree the final tree can be generated.
	 */
	private HashIdFileTree createIntermediateTree() {
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
			CodeLocation location, HashId hashId) {
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
					HashAlgorithm algorithm = StoreUtilities
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
	public List<AnalyzedCode> getAnalyzedFiles() {
		return analyzedFiles;
	}

	@Override
	public HashIdFileTree getFileTree() {
		return fileTree;
	}

	@Override
	public List<AnalyzedCode> getFailedFiles() {
		return failedSources;
	}

	@Override
	public AnalyzedCode findAnalyzedCode(String internalPath) {
		for (AnalyzedCode analyzedFile : analyzedFiles) {
			if (analyzedFile.getSourceLocation().getInternalLocation()
					.equals(internalPath)) {
				return analyzedFile;
			}
		}
		return null;
	}

	private static <T> void persist(T object, File file) throws IOException {
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				new FileOutputStream(file));
		try {
			objectOutputStream.writeObject(object);
		} finally {
			objectOutputStream.close();
		}
	}

	private static <T> T restore(File file) throws IOException {
		ObjectInputStream objectOutputStream = new ObjectInputStream(
				new FileInputStream(file));
		try {
			@SuppressWarnings("unchecked")
			T t = (T) objectOutputStream.readObject();
			return t;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Could not restore class from file '"
					+ file + "'!", e);
		} finally {
			objectOutputStream.close();
		}
	}

	@Override
	public AnalysisRunInformation getInformation() {
		return new AnalysisRunInformation(analysisProject, uuid, creationTime,
				timeOfRun, "<Not implemented, yet!>");
	}

	public static boolean isAnalysisRunDirectory(File runDirectory) {
		return new File(runDirectory, DIRECTORY_FLAG).exists();
	}

}