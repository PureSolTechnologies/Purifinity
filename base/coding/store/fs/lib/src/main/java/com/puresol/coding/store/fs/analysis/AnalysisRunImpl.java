package com.puresol.coding.store.fs.analysis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

import com.puresol.coding.analysis.api.AnalysisProject;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalysisRunInformation;
import com.puresol.coding.analysis.api.AnalysisStoreException;
import com.puresol.coding.analysis.api.AnalyzedCode;
import com.puresol.coding.analysis.api.CodeStoreException;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.analysis.api.ModuleStore;
import com.puresol.coding.analysis.api.ModuleStoreException;
import com.puresol.coding.analysis.api.ModuleStoreFactory;
import com.puresol.coding.analysis.api.RepositoryLocation;
import com.puresol.trees.TreeVisitor;
import com.puresol.trees.TreeWalker;
import com.puresol.trees.WalkingAction;
import com.puresol.uhura.source.CodeLocation;
import com.puresol.utils.DirectoryUtilities;
import com.puresol.utils.FileSearchConfiguration;
import com.puresol.utils.HashAlgorithm;
import com.puresol.utils.HashId;
import com.puresol.utils.data.HashCodeGenerator;
import com.puresol.utils.progress.AbstractProgressObservable;

public class AnalysisRunImpl extends AbstractProgressObservable<AnalysisRun>
	implements AnalysisRun {

    private static final long serialVersionUID = 6413809660830217670L;

    private static final String DIRECTORY_FLAG = ".analysis_run";

    private static final Logger logger = LoggerFactory
	    .getLogger(AnalysisRunImpl.class);

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
     * @throws ModuleStoreException
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

    private static final String ANALYZED_FILES_FILE = "analyzed_files.persist";
    private static final String FAILED_FILES_FILE = "failed_files.persist";
    private static final String TREE_FILE = "code_tree.persist";
    private static final String SEARCH_CONFIGURATION_FILE = "search_configuration.persist";
    private static final String ANALYSIS_RUN_PROPERTIES_FILE = "analysis_run.properties";
    private static final String ANALYSIS_INFORMATION_FILE = "analysis_information.persist";
    private static final String REPOSITORY_LOCATION__FILE = "repository_location.persist";

    private final File runDirectory;

    private final List<AnalyzedCode> analyzedFiles = new ArrayList<AnalyzedCode>();
    private final List<AnalyzedCode> failedSources = new ArrayList<AnalyzedCode>();

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
     * @throws ModuleStoreException
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
	    timeOfRun = Long.valueOf(properties.get("run.time").toString());
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
	properties.put("run.time", String.valueOf(timeOfRun));
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
	failedSources.addAll(failed);
    }

    private void storeAnalysisResultInformation() {
	try {
	    persist(analyzedFiles, new File(runDirectory, ANALYZED_FILES_FILE));
	    persist(failedSources, new File(runDirectory, FAILED_FILES_FILE));
	    persist(fileTree, new File(runDirectory, TREE_FILE));
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	}
    }

    @Override
    public Boolean call() throws Exception {
	try {
	    reset();
	    saveAnalysisRunInformation();
	    boolean retVal = analyzeFiles();
	    buildCodeLocationTree();
	    storeAnalysisResultInformation();
	    storeModules();
	    fireDone("Finished successfully.", retVal);
	    return retVal;
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

    private boolean analyzeFiles() throws CodeStoreException {
	List<Future<AnalyzedCode>> futures = startAllAnalysisThreads();
	waitForAnalysisThreads(futures);
	return true;
    }

    private List<Future<AnalyzedCode>> startAllAnalysisThreads() {
	repositoryLocation.setCodeSearchConfiguration(searchConfig);
	List<CodeLocation> sourceFiles = repositoryLocation.getSourceCodes();

	int processors = Runtime.getRuntime().availableProcessors();
	ExecutorService threadPool = Executors.newFixedThreadPool(processors);
	fireStarted("Analyze files", sourceFiles.size());
	List<Future<AnalyzedCode>> futures = new ArrayList<Future<AnalyzedCode>>();
	for (int index = 0; index < sourceFiles.size(); index++) {
	    final CodeLocation sourceFile = sourceFiles.get(index);
	    Callable<AnalyzedCode> callable = new AnalysisRunCallable(
		    sourceFile);
	    futures.add(threadPool.submit(callable));
	}
	threadPool.shutdown();
	return futures;
    }

    private void waitForAnalysisThreads(List<Future<AnalyzedCode>> futures) {
	while (futures.size() > 0) {
	    Iterator<Future<AnalyzedCode>> iterator = futures.iterator();
	    while (iterator.hasNext()) {
		Future<AnalyzedCode> future = iterator.next();
		if (future.isDone()) {
		    iterator.remove();
		    fireUpdateWork("Finished a file.", 1);
		    try {
			AnalyzedCode result = future.get();
			analyzedFiles.add(result);
			fireUpdateWork("Finished '"
				+ result.getSourceLocation()
					.getHumanReadableLocationString()
				+ "'.", 0);
		    } catch (CancellationException e) {
			logger.debug("Job was cancelled.", e);
		    } catch (InterruptedException e) {
			logger.warn("Job was interrupted.", e);
		    } catch (ExecutionException e) {
			logger.error(
				"Job was aborted with execution exception.", e);
		    }
		}
	    }
	    try {
		Thread.sleep(1000);
	    } catch (InterruptedException e) {
		// intentionally left blank
	    }
	}
    }

    private void buildCodeLocationTree() {
	HashIdFileTree intermediate = createIntermediateTree();
	createFinalTree(intermediate);
    }

    private void storeModules() {
	ModuleStoreFactory moduleStoreFactory = ModuleStoreFactory.getFactory();
	final ModuleStore moduleStore = moduleStoreFactory.getInstance();
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
		} catch (ModuleStoreException e) {
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
		child = new HashIdFileTree(node, directory, hashId, isFile);
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
			hashList.add(child.getHashId().toString());
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
    public List<AnalyzedCode> getAnalyzedCodes() {
	return analyzedFiles;
    }

    @Override
    public HashIdFileTree getFileTree() {
	return fileTree;
    }

    @Override
    public List<AnalyzedCode> getFailedCodes() {
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