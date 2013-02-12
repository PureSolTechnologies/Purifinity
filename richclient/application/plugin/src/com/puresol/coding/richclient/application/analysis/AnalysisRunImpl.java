package com.puresol.coding.richclient.application.analysis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.analysis.api.AnalysisInformation;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalysisRunInformation;
import com.puresol.coding.analysis.api.AnalyzedCode;
import com.puresol.coding.analysis.api.CodeAnalysis;
import com.puresol.coding.analysis.api.CodeStore;
import com.puresol.coding.analysis.api.CodeStoreException;
import com.puresol.coding.analysis.api.CodeStoreFactory;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.analysis.api.ModuleStoreException;
import com.puresol.coding.analysis.api.RepositoryLocation;
import com.puresol.uhura.source.CodeLocation;
import com.puresol.uhura.source.SourceCode;
import com.puresol.utils.DirectoryUtilities;
import com.puresol.utils.FileSearchConfiguration;
import com.puresol.utils.HashId;

public class AnalysisRunImpl extends Job implements Serializable, AnalysisRun {

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
						.getAnalysisInformation().getUUID());
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
			throws ModuleStoreException {
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
	 * @throws ModuleStoreException
	 * @throws IOException
	 */
	public static AnalysisRun create(File runDirectory,
			AnalysisInformation analysisInformation, UUID uuid,
			RepositoryLocation repositorySource,
			FileSearchConfiguration searchConfiguration)
			throws ModuleStoreException {
		AnalysisRunImpl projectAnalyser = new AnalysisRunImpl(runDirectory);
		projectAnalyser.create(repositorySource, analysisInformation, uuid,
				searchConfiguration);
		return projectAnalyser;
	}

	private static final String ANALYZED_FILES_FILE = "analyzed_files.persist";
	private static final String FAILED_FILES_FILE = "failed_files.persist";
	private static final String SEARCH_CONFIGURATION_FILE = "search_configuration.persist";
	private static final String ANALYSIS_RUN_PROPERTIES_FILE = "analysis_run.properties";
	private static final String ANALYSIS_INFORMATION_FILE = "analysis_information.persist";
	private static final String FILE_TREE = "file_tree.persist";
	private static final String REPOSITORY_LOCATION__FILE = "repository_location.persist";

	private final File runDirectory;

	private final List<AnalyzedCode> analyzedFiles = new ArrayList<AnalyzedCode>();
	private final List<CodeLocation> failedSources = new ArrayList<CodeLocation>();
	private final CodeStore fileStore = CodeStoreFactory.getInstance();

	private HashIdFileTree hashIdFileTree = null;
	private FileSearchConfiguration searchConfig;

	private AnalysisInformation analysisInformation;
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
		super("Analysis Run");
		this.runDirectory = runDirectory;
	}

	/**
	 * This methods creates a new project directory.
	 * 
	 * @param repositorySource
	 * @param searchConfiguration
	 * @param name
	 * @param uuid2
	 * @return
	 * @throws ModuleStoreException
	 * @throws IOException
	 */
	void create(RepositoryLocation repositorySource,
			AnalysisInformation analysisInformation, UUID uuid,
			FileSearchConfiguration searchConfiguration)
			throws ModuleStoreException {
		try {
			this.repositoryLocation = repositorySource;
			this.analysisInformation = analysisInformation;
			this.uuid = uuid;
			this.searchConfig = searchConfiguration;
			this.creationTime = new Date();
			this.timeOfRun = 0;
			DirectoryUtilities.checkAndCreateDirectory(runDirectory);
			new File(runDirectory, DIRECTORY_FLAG).createNewFile();
			saveProperties();
			writeSearchConfiguration();
			storeProjectInformation();
		} catch (IOException e) {
			throw new ModuleStoreException("Could not create analysis run!", e);
		}
	}

	private void saveProperties() throws IOException {
		Properties properties = new Properties();
		properties.put("uuid", uuid.toString());
		properties.put("creation.time", String.valueOf(creationTime.getTime()));
		properties.put("run.time", String.valueOf(timeOfRun));
		properties.put(AnalysisRunImpl.class.getSimpleName() + ".name",
				getName());
		FileWriter writer = new FileWriter(new File(runDirectory,
				ANALYSIS_RUN_PROPERTIES_FILE));
		try {
			properties.store(writer, "");
		} finally {
			writer.close();
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

	/**
	 * This method opens the project directory and loads all information files.
	 * 
	 * @return
	 * @throws ModuleStoreException
	 */
	void open() throws ModuleStoreException {
		try {
			if (!runDirectory.exists()) {
				throw new IOException("Analysis run directory '" + runDirectory
						+ "' does not exist!");
			}
			loadProperties();
			readSearchConfiguration();
			loadProjectInformation();
		} catch (IOException e) {
			throw new ModuleStoreException("Could not open analysis run!", e);
		}
	}

	private void loadProperties() throws IOException {
		Properties properties = new Properties();
		FileReader reader = new FileReader(new File(runDirectory,
				ANALYSIS_RUN_PROPERTIES_FILE));
		try {
			properties.load(reader);
			uuid = UUID.fromString(properties.get("uuid").toString());
			creationTime = new Date(Long.valueOf(properties
					.get("creation.time").toString()));
			timeOfRun = Long.valueOf(properties.get("run.time").toString());
			setName(properties.getProperty(
					AnalysisRunImpl.class.getSimpleName() + ".name", "unnamed"));
		} finally {
			reader.close();
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

	private void loadProjectInformation() throws IOException {
		@SuppressWarnings("unchecked")
		List<AnalyzedCode> analyzed = (List<AnalyzedCode>) restore(new File(
				runDirectory, ANALYZED_FILES_FILE));
		analyzedFiles.addAll(analyzed);
		@SuppressWarnings("unchecked")
		List<CodeLocation> failed = (List<CodeLocation>) restore(new File(
				runDirectory, FAILED_FILES_FILE));
		failedSources.addAll(failed);
		analysisInformation = restore(new File(runDirectory,
				ANALYSIS_INFORMATION_FILE));
		hashIdFileTree = restore(new File(runDirectory, FILE_TREE));

		repositoryLocation = restore(new File(runDirectory,
				REPOSITORY_LOCATION__FILE));
	}

	private void storeProjectInformation() {
		try {
			persist(repositoryLocation, new File(runDirectory,
					REPOSITORY_LOCATION__FILE));
			persist(analysisInformation, new File(runDirectory,
					ANALYSIS_INFORMATION_FILE));
			persist(analyzedFiles, new File(runDirectory, ANALYZED_FILES_FILE));
			persist(failedSources, new File(runDirectory, FAILED_FILES_FILE));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		try {
			reset();
			IStatus retVal = analyzeFiles(monitor);
			saveProperties();
			storeProjectInformation();
			persist(hashIdFileTree, new File(runDirectory, FILE_TREE));
			monitor.done();
			return retVal;
		} catch (IOException e) {
			return new Status(IStatus.ERROR, AnalysisRunImpl.class.getName(),
					"Could not finish analysis run!", e);
		} catch (CodeStoreException e) {
			return new Status(IStatus.ERROR, AnalysisRunImpl.class.getName(),
					"Could not store the source code!", e);
		}
	}

	/**
	 * This method resets the values for a reanalysis.
	 */
	private void reset() {
		analyzedFiles.clear();
		failedSources.clear();
	}

	private IStatus analyzeFiles(final IProgressMonitor monitor)
			throws CodeStoreException {
		repositoryLocation.setCodeSearchConfiguration(searchConfig);
		List<CodeLocation> sources = repositoryLocation.getSourceCodes();
		StoreUtilities.storeFiles(sources);

		int processors = Runtime.getRuntime().availableProcessors();
		ExecutorService threadPool = Executors.newFixedThreadPool(processors);
		monitor.beginTask("Analyze files", sources.size());
		for (int index = 0; index < sources.size(); index++) {
			final CodeLocation source = sources.get(index);
			Runnable callable = new Runnable() {

				@Override
				public void run() {
					analyzeCode(source);
					logger.info("Finsihed " + source);
					monitor.worked(1);
				}
			};
			threadPool.execute(callable);
			if (monitor.isCanceled()) {
				threadPool.shutdownNow();
				try {
					while (!threadPool.awaitTermination(10, TimeUnit.SECONDS)) {
					}
				} catch (InterruptedException e) {
				}
				return Status.CANCEL_STATUS;
			}
		}
		threadPool.shutdown();
		try {
			while (!threadPool.awaitTermination(10, TimeUnit.SECONDS)) {
			}
		} catch (InterruptedException e) {
		}
		return Status.OK_STATUS;
	}

	/**
	 * This method analyzes a single file. The file is added to faildFiles if
	 * there was a analyzer found, but the analyzer had issues to analyze the
	 * file, which might have two reasons:
	 * 
	 * 1) The file is not valid.
	 * 
	 * 2) The analyzer is buggy.
	 * 
	 * In either way, these files needs to be tracked and recorded.
	 * 
	 * @param file
	 *            is the file to be analyzed.
	 */
	private void analyzeCode(CodeLocation source) {
		try {
			SourceCode sourceCode = source.load();
			HashId hashId = sourceCode.getHashId();
			if (fileStore.isAvailable(hashId) && fileStore.wasAnalyzed(hashId)) {
				CodeAnalysis analysis = fileStore.loadAnalysis(hashId);
				analyzedFiles.add(new AnalyzedCode(hashId, source, analysis
						.getStartTime(), analysis.getDuration(), analysis
						.getLanguageName(), analysis.getLanguageVersion()));
			} else {
				CodeAnalyzerImpl fileAnalyzer = new CodeAnalyzerImpl(source,
						hashId);
				fileAnalyzer.analyze();
				if (fileAnalyzer.isAnalyzed()) {
					fileStore.storeAnalysis(hashId, fileAnalyzer.getAnalyzer()
							.getAnalysis());
					AnalyzedCode analyzedFile = fileAnalyzer.getAnalysis()
							.getAnalyzedFile();
					analyzedFiles.add(analyzedFile);
				} else {
					failedSources.add(source);
					logger.warn("File "
							+ source.getHumanReadableLocationString()
							+ " could be analyzed.");
				}
			}
		} catch (Exception e) {
			failedSources.add(source);
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public List<AnalyzedCode> getAnalyzedCodes() {
		return analyzedFiles;
	}

	@Override
	public HashIdFileTree getFileTree() {
		return hashIdFileTree;
	}

	@Override
	public List<CodeLocation> getFailedCodeLocations() {
		return failedSources;
	}

	@Override
	public AnalyzedCode findAnalyzedCode(File file) {
		for (AnalyzedCode analyzedFile : analyzedFiles) {
			if (analyzedFile.getLocation().equals(file)) {
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
		return new AnalysisRunInformation(analysisInformation, uuid,
				creationTime, timeOfRun, "<Not implemented, yet!>");
	}

	public static boolean isAnalysisRunDirectory(File runDirectory) {
		return new File(runDirectory, DIRECTORY_FLAG).exists();
	}

}