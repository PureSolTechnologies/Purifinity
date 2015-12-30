package com.puresoltechnologies.purifinity.server.core.impl.analysis.store;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.slf4j.Logger;

import com.buschmais.xo.api.XOException;
import com.buschmais.xo.api.XOManager;
import com.buschmais.xo.api.XOTransaction;
import com.puresoltechnologies.commons.misc.hash.HashAlgorithm;
import com.puresoltechnologies.commons.misc.hash.HashCodeGenerator;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.commons.misc.io.FileSearchConfiguration;
import com.puresoltechnologies.ductiledb.tinkerpop.DuctileGraph;
import com.puresoltechnologies.ductiledb.tinkerpop.DuctileVertex;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisRunFileTree;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreService;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileInformation;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.AnalysisServiceConnection;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.AnalysisProjectVertex;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.AnalysisRunVertex;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.ContentTreeRootVertex;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.DuctileDBXOManager;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.FileTreeDirectoryVertex;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.ProjectToRunEdge;
import com.puresoltechnologies.purifinity.server.database.ductiledb.utils.DuctileDBElementNames;
import com.puresoltechnologies.purifinity.server.database.hbase.HBaseElementNames;
import com.puresoltechnologies.purifinity.server.database.hbase.HBaseHelper;
import com.puresoltechnologies.server.systemmonitor.core.api.events.Event;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventLoggerRemote;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventSeverity;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventType;
import com.puresoltechnologies.trees.TreeVisitor;
import com.puresoltechnologies.trees.TreeWalker;
import com.puresoltechnologies.trees.WalkingAction;

@Stateless
public class AnalysisStoreServiceBean implements AnalysisStoreService {

    public static final HashAlgorithm DEFAULT_HASH_ALGORITHM = HashAlgorithm.SHA256;
    public static final MessageDigest DEFAULT_HASH;

    static {
	try {
	    DEFAULT_HASH = MessageDigest.getInstance(DEFAULT_HASH_ALGORITHM.getAlgorithmName());
	} catch (NoSuchAlgorithmException e) {
	    throw new RuntimeException("Could not initialize default hash algorithm for analysis store.", e);
	}
    }

    public static final String COMPONENT_NAME = "AnalysisStoreService";

    @Inject
    private Logger logger;

    @Inject
    private EventLoggerRemote eventLogger;

    @Inject
    @AnalysisServiceConnection
    private Connection connection;

    @Inject
    private AnalysisStoreUtils analysisStoreCassandraUtils;

    @Inject
    private AnalysisStoreFileTreeUtils analysisStoreFileTreeUtils;

    @Inject
    private AnalysisStoreContentTreeUtils analysisStoreContentTreeUtils;

    @Inject
    private AnalysisStoreCacheUtils analysisStoreCacheUtils;

    @Inject
    private BigTableUtils bigTableUtils;

    @Inject
    private DuctileGraph graph;

    @Inject
    @DuctileDBXOManager
    private XOManager xoManager;

    @Override
    public AnalysisProjectInformation createAnalysisProject(String projectId, AnalysisProjectSettings settings)
	    throws AnalysisStoreException {
	XOTransaction currentTransaction = xoManager.currentTransaction();
	boolean active = currentTransaction.isActive();
	if (!active) {
	    currentTransaction.begin();
	}
	try {
	    eventLogger.logEvent(
		    new Event(COMPONENT_NAME, 0x01, EventType.USER_ACTION, EventSeverity.INFO, "Create project..."));
	    Date creationTime = new Date();
	    createAnalysisProjectVertex(projectId, creationTime);
	    storeProjectAnalysisSettings(projectId, settings);
	    return new AnalysisProjectInformation(projectId, creationTime);
	} catch (RuntimeException e) {
	    if (!active) {
		currentTransaction.rollback();
		active = true;
	    }
	    throw e;
	} finally {
	    if (!active) {
		currentTransaction.commit();
	    }
	}
    }

    private void createAnalysisProjectVertex(String projectId, Date creationTime) {
	AnalysisProjectVertex analysisProject = xoManager.create(AnalysisProjectVertex.class);
	analysisProject.setProjectId(projectId);
	analysisProject.setCreationTime(creationTime);
    }

    private void storeProjectAnalysisSettings(String projectId, AnalysisProjectSettings settings)
	    throws AnalysisStoreException {
	try (PreparedStatement preparedStatement = connection.prepareStatement(
		"UPSERT INTO " + HBaseElementNames.ANALYSIS_PROJECT_SETTINGS_TABLE + " (project_id, name, description, "
			+ "file_includes, file_excludes, " + "location_includes, location_excludes, "
			+ "ignore_hidden, repository_location_keys, repository_location_values) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
	    String name = settings.getName();
	    String description = settings.getDescription();
	    FileSearchConfiguration fileSearchConfiguration = settings.getFileSearchConfiguration();

	    preparedStatement.setString(1, projectId);
	    preparedStatement.setString(2, name);
	    preparedStatement.setString(3, description);
	    preparedStatement.setArray(4,
		    connection.createArrayOf("VARCHAR", fileSearchConfiguration.getFileIncludes().toArray()));
	    preparedStatement.setArray(5,
		    connection.createArrayOf("VARCHAR", fileSearchConfiguration.getFileExcludes().toArray()));
	    preparedStatement.setArray(6,
		    connection.createArrayOf("VARCHAR", fileSearchConfiguration.getLocationIncludes().toArray()));
	    preparedStatement.setArray(7,
		    connection.createArrayOf("VARCHAR", fileSearchConfiguration.getLocationExcludes().toArray()));
	    preparedStatement.setBoolean(8, fileSearchConfiguration.isIgnoreHidden());
	    HBaseHelper.writeProperties(connection, preparedStatement, 9, 10, settings.getRepository());
	    preparedStatement.execute();
	    connection.commit();
	} catch (SQLException e) {
	    try {
		connection.rollback();
	    } catch (SQLException e1) {
		logger.warn("Could not rollback project settings storage.", e1);
	    }
	    throw new AnalysisStoreException("Could not store project settings.", e);
	}
    }

    @Override
    public List<AnalysisProjectInformation> readAllAnalysisProjectInformation() throws AnalysisStoreException {
	List<AnalysisProjectInformation> projects = new ArrayList<>();
	if (graph == null) {
	    return projects;
	}
	try {
	    GraphTraversal<Vertex, Vertex> vertices = graph.traversal().V().hasLabel(AnalysisProjectVertex.NAME);

	    while (vertices.hasNext()) {
		DuctileVertex vertex = (DuctileVertex) vertices.next();
		String projectId = (String) vertex.property(DuctileDBElementNames.ANALYSIS_PROJECT_ID_PROPERTY).value();
		Date creationTime = (Date) vertex.property(DuctileDBElementNames.CREATION_TIME_PROPERTY);
		projects.add(new AnalysisProjectInformation(projectId, creationTime));
	    }
	    return projects;
	} finally {
	    graph.tx().rollback();
	}
    }

    @Override
    public List<AnalysisProject> readAllAnalysisProjects() throws AnalysisStoreException {
	List<AnalysisProject> projects = new ArrayList<>();
	List<AnalysisProjectInformation> allProjectInformation = readAllAnalysisProjectInformation();
	for (AnalysisProjectInformation information : allProjectInformation) {
	    projects.add(readAnalysisProject(information.getProjectId()));
	}
	return projects;
    }

    @Override
    public AnalysisProjectInformation readAnalysisProjectInformation(String projectId) throws AnalysisStoreException {
	XOTransaction currentTransaction = xoManager.currentTransaction();
	boolean active = currentTransaction.isActive();
	if (!active) {
	    currentTransaction.begin();
	}
	try {
	    AnalysisProjectVertex analysisProjectVertex = AnalysisStoreDuctileDBUtils
		    .findAnalysisProjectVertex(xoManager, projectId);
	    Date creationTime = analysisProjectVertex.getCreationTime();
	    return new AnalysisProjectInformation(projectId, creationTime);
	} finally {
	    if (!active) {
		currentTransaction.rollback();
	    }
	}
    }

    @Override
    public void removeAnalysisProject(String projectId) throws AnalysisStoreException {
	// delete analysis runs first
	List<AnalysisRunInformation> runs = readAllRunInformation(projectId);
	for (AnalysisRunInformation run : runs) {
	    removeAnalysisRun(projectId, run.getRunId());
	}
	// delete project
	deleteProject(projectId);
    }

    private void deleteProject(String projectId) throws AnalysisStoreException {
	xoManager.currentTransaction().begin();
	try {
	    AnalysisProjectVertex analysisProjectVertex = AnalysisStoreDuctileDBUtils
		    .findAnalysisProjectVertex(xoManager, projectId);
	    xoManager.delete(analysisProjectVertex);
	    analysisStoreCassandraUtils.removeProjectSettings(projectId);
	    eventLogger.logEvent(new Event(COMPONENT_NAME, 0x01, EventType.USER_ACTION, EventSeverity.INFO,
		    "Deleted project '" + projectId + "'"));
	    xoManager.currentTransaction().commit();
	} catch (XOException e) {
	    xoManager.currentTransaction().rollback();
	}
    }

    @Override
    public AnalysisProjectSettings readAnalysisProjectSettings(String projectId) throws AnalysisStoreException {
	try (PreparedStatement preparedStatement = connection.prepareStatement(
		"SELECT name, description, file_includes, file_excludes, location_includes, location_excludes, ignore_hidden, repository_location_keys, repository_location_values FROM "
			+ HBaseElementNames.ANALYSIS_PROJECT_SETTINGS_TABLE + " WHERE project_id=?")) {
	    preparedStatement.setString(1, projectId);
	    try (ResultSet resultSet = preparedStatement.executeQuery()) {
		if (!resultSet.next()) {
		    return null;
		}
		String name = resultSet.getString("name");
		String description = resultSet.getString("description");
		List<String> fileIncludes = HBaseHelper.getList(resultSet, "file_includes", String.class);
		List<String> fileExcludes = HBaseHelper.getList(resultSet, "file_excludes", String.class);
		List<String> locationIncludes = HBaseHelper.getList(resultSet, "location_includes", String.class);
		List<String> locationExcludes = HBaseHelper.getList(resultSet, "location_excludes", String.class);
		boolean ignoreHidden = resultSet.getBoolean("ignore_hidden");
		FileSearchConfiguration fileSearchConfiguration = new FileSearchConfiguration(locationIncludes,
			locationExcludes, fileIncludes, fileExcludes, ignoreHidden);
		String[] repositoryLocationKeys = HBaseHelper.getArray(resultSet, "repository_location_keys",
			String.class);
		String[] repositoryLocationValues = HBaseHelper.getArray(resultSet, "repository_location_values",
			String.class);
		if (repositoryLocationKeys.length != repositoryLocationValues.length) {
		    throw new AnalysisStoreException(
			    "Array lenght of keys and values for repository location are different.");
		}
		Properties repositoryLocation = new Properties();
		for (int i = 0; i < repositoryLocationKeys.length; ++i) {
		    repositoryLocation.put(repositoryLocationKeys[i], repositoryLocationValues[i]);
		}
		return new AnalysisProjectSettings(name, description, fileSearchConfiguration, repositoryLocation);
	    }
	} catch (SQLException e) {
	    throw new AnalysisStoreException("Could not read project settings.", e);
	}
    }

    @Override
    public void updateAnalysisProjectSettings(String projectId, AnalysisProjectSettings settings)
	    throws AnalysisStoreException {
	storeProjectAnalysisSettings(projectId, settings);
    }

    @Override
    public List<AnalysisRunInformation> readAllRunInformation(String projectId) throws AnalysisStoreException {
	XOTransaction currentTransaction = xoManager.currentTransaction();
	boolean active = currentTransaction.isActive();
	if (!active) {
	    currentTransaction.begin();
	}
	try {
	    AnalysisProjectVertex analysisProjectVertex = AnalysisStoreDuctileDBUtils
		    .findAnalysisProjectVertex(xoManager, projectId);

	    List<ProjectToRunEdge> analysisRuns = analysisProjectVertex.getAnalysisRuns();

	    List<AnalysisRunInformation> allRunInformation = new ArrayList<>();
	    for (ProjectToRunEdge edge : analysisRuns) {
		AnalysisRunVertex run = edge.getRun();
		long runId = run.getRunId();
		Date startTime = run.getStartTime();
		long duration = run.getDuration();
		String description = run.getDescription();
		FileSearchConfiguration fileSearchConfiguration = readSearchConfiguration(projectId, runId);
		allRunInformation.add(new AnalysisRunInformation(projectId, runId, startTime, duration, description,
			fileSearchConfiguration));
	    }
	    return allRunInformation;
	} finally {
	    if (!active) {
		currentTransaction.rollback();
	    }
	}
    }

    @Override
    public AnalysisRunInformation createAnalysisRun(String projectId, Date startTime, long duration, String description,
	    FileSearchConfiguration fileSearchConfiguration) throws AnalysisStoreException {
	XOTransaction currentTransaction = xoManager.currentTransaction();
	boolean active = currentTransaction.isActive();
	if (!active) {
	    currentTransaction.begin();
	}
	try {
	    Date creationTime = new Date();
	    long runId = System.currentTimeMillis();

	    AnalysisProjectVertex analysisProjectVertex = AnalysisStoreDuctileDBUtils
		    .findAnalysisProjectVertex(xoManager, projectId);

	    AnalysisStoreDuctileDBUtils.createAnalysisRunVertex(xoManager, analysisProjectVertex, runId, creationTime,
		    startTime, duration, description);

	    analysisStoreCassandraUtils.writeAnalysisRunSettings(projectId, runId, fileSearchConfiguration);

	    return new AnalysisRunInformation(projectId, runId, startTime, duration, description,
		    fileSearchConfiguration);
	} catch (AnalysisStoreException e) {
	    if (!active) {
		currentTransaction.rollback();
		active = true;
	    }
	    throw e;
	} finally {
	    if (!active) {
		currentTransaction.commit();
	    }
	}
    }

    @Override
    public void updateAnalysisRunInformation(String projectId, long runId, long duration)
	    throws AnalysisStoreException {
	XOTransaction currentTransaction = xoManager.currentTransaction();
	boolean active = currentTransaction.isActive();
	if (!active) {
	    currentTransaction.begin();
	}
	try {
	    AnalysisRunVertex run = AnalysisStoreDuctileDBUtils.findAnalysisRunVertex(xoManager, runId);
	    if (run == null) {
		return;
	    }
	    run.setDuration(duration);
	    if (!active) {
		currentTransaction.commit();
	    }
	} finally {
	    if (!active) {
		if (currentTransaction.isActive()) {
		    currentTransaction.rollback();
		}
	    }
	}

    }

    @Override
    public AnalysisRunInformation readAnalysisRunInformation(String projectId, long runId)
	    throws AnalysisStoreException {
	XOTransaction currentTransaction = xoManager.currentTransaction();
	boolean active = currentTransaction.isActive();
	if (!active) {
	    currentTransaction.begin();
	}
	try {
	    AnalysisRunVertex run = AnalysisStoreDuctileDBUtils.findAnalysisRunVertex(xoManager, runId);
	    if (run == null) {
		return null;
	    }
	    long runIdRead = run.getRunId();
	    if (runId != runIdRead) {
		throw new AnalysisStoreException("Anaysis run for '" + runId
			+ "' was not found, but a vertex with run_uuid='" + runIdRead + "'. Database is inconsistent!");
	    }
	    Date startTime = run.getStartTime();
	    long duration = run.getDuration();
	    String description = run.getDescription();
	    FileSearchConfiguration fileSearchConfiguration = readSearchConfiguration(projectId, runId);

	    return new AnalysisRunInformation(projectId, runId, startTime, duration, description,
		    fileSearchConfiguration);
	} finally {
	    if (!active) {
		currentTransaction.rollback();
	    }
	}
    }

    @Override
    public AnalysisRunInformation readLastAnalysisRun(String projectId) throws AnalysisStoreException {
	List<AnalysisRunInformation> allRunInformation = readAllRunInformation(projectId);
	Date time = null;
	AnalysisRunInformation lastRun = null;
	for (AnalysisRunInformation runInformation : allRunInformation) {
	    if ((lastRun == null) || (runInformation.getStartTime().compareTo(time) > 0)) {
		lastRun = runInformation;
		time = runInformation.getStartTime();
	    }
	}
	return lastRun;
    }

    @Override
    public void removeAnalysisRun(String projectId, long runId) throws AnalysisStoreException {
	XOTransaction currentTransaction = xoManager.currentTransaction();
	boolean active = currentTransaction.isActive();
	if (!active) {
	    currentTransaction.begin();
	}
	try {
	    AnalysisRunVertex analysisRunVertex = AnalysisStoreDuctileDBUtils.findAnalysisRunVertex(xoManager, runId);
	    if (analysisRunVertex != null) {
		FileTreeDirectoryVertex rootDirectory = analysisRunVertex.getRootDirectory();
		ContentTreeRootVertex contentRoot = analysisRunVertex.getContentRoot();
		analysisStoreFileTreeUtils.deleteFileTree(xoManager, rootDirectory);
		// remove analysis run vertex
		xoManager.delete(analysisRunVertex);
		// clear caches
		analysisStoreCacheUtils.clearAnalysisRunCaches(projectId, runId);
		// remove run settings
		analysisStoreCassandraUtils.removeAnalysisRunSettings(projectId, runId);
		// remove analysis run results
		bigTableUtils.removeAnalysisRunResults(projectId, runId);
		// cleanup content tree
		analysisStoreContentTreeUtils.checkAndRemoveAnalysisRunContent(xoManager, contentRoot);
	    }
	} catch (AnalysisStoreException e) {
	    if (!active) {
		currentTransaction.rollback();
	    }
	    throw e;
	} finally {
	    if (!active) {
		currentTransaction.commit();
	    }
	}
    }

    @Override
    public FileSearchConfiguration readSearchConfiguration(String projectId, long runId) throws AnalysisStoreException {
	try (PreparedStatement preparedStatement = connection.prepareStatement(
		"SELECT file_includes, file_excludes, location_includes, location_excludes, ignore_hidden FROM "
			+ HBaseElementNames.ANALYSIS_RUN_SETTINGS_TABLE + " WHERE project_id=? AND run_id=?")) {
	    preparedStatement.setString(1, projectId);
	    preparedStatement.setLong(2, runId);
	    try (ResultSet resultSet = preparedStatement.executeQuery()) {
		if (!resultSet.next()) {
		    return null;
		}
		List<String> fileIncludes = HBaseHelper.getList(resultSet, "file_includes", String.class);
		List<String> fileExcludes = HBaseHelper.getList(resultSet, "file_excludes", String.class);
		List<String> locationIncludes = HBaseHelper.getList(resultSet, "location_includes", String.class);
		List<String> locationExcludes = HBaseHelper.getList(resultSet, "location_excludes", String.class);
		boolean ignoreHidden = resultSet.getBoolean("ignore_hidden");
		return new FileSearchConfiguration(locationIncludes, locationExcludes, fileIncludes, fileExcludes,
			ignoreHidden);
	    }
	} catch (SQLException e) {
	    throw new AnalysisStoreException("Could not read search configuration.", e);
	}
    }

    @Override
    public AnalysisFileTree readAnalysisFileTree(String projectId, long runId) throws AnalysisStoreException {
	AnalysisFileTree analysisFileTree = analysisStoreCacheUtils.readCachedAnalysisFileTree(projectId, runId);
	if (analysisFileTree != null) {
	    return analysisFileTree;
	}
	XOTransaction currentTransaction = xoManager.currentTransaction();
	boolean active = currentTransaction.isActive();
	if (!active) {
	    currentTransaction.begin();
	}
	try {
	    AnalysisRunVertex analysisRunVertex = AnalysisStoreDuctileDBUtils.findAnalysisRunVertex(xoManager, runId);
	    analysisFileTree = analysisStoreFileTreeUtils.createAnalysisFileTree(analysisRunVertex);
	    analysisStoreCacheUtils.cacheAnalysisFileTree(projectId, runId, analysisFileTree);
	    return analysisFileTree;
	} catch (AnalysisStoreException e) {
	    if (!active) {
		currentTransaction.rollback();
	    }
	    throw e;
	} finally {
	    if (!active) {
		currentTransaction.commit();
	    }
	}
    }

    @Override
    public AnalysisProject readAnalysisProject(String projectId) throws AnalysisStoreException {
	AnalysisProjectInformation information = readAnalysisProjectInformation(projectId);
	AnalysisProjectSettings settings = readAnalysisProjectSettings(projectId);
	return new AnalysisProject(information, settings);
    }

    @Override
    public AnalysisRun readAnalysisRun(AnalysisRunInformation information) throws AnalysisStoreException {
	AnalysisFileTree analysisFileTree = readAnalysisFileTree(information.getProjectId(), information.getRunId());
	return new AnalysisRun(information, analysisFileTree);
    }

    @Override
    public AnalysisRunFileTree createAndStoreFileAndContentTree(String projectId, long runId, String rootNodeName,
	    Map<SourceCodeLocation, FileInformation> storedSources) throws AnalysisStoreException {
	XOTransaction currentTransaction = xoManager.currentTransaction();
	boolean active = currentTransaction.isActive();
	if (!active) {
	    currentTransaction.begin();
	}
	try {
	    AnalysisRunFileTree fileTree = convertToAnalysisRunFileTree(storedSources, rootNodeName);
	    AnalysisRunVertex analysisRunVertex = AnalysisStoreDuctileDBUtils.findAnalysisRunVertex(xoManager, runId);
	    analysisStoreContentTreeUtils.addContentTree(xoManager, fileTree, analysisRunVertex);
	    analysisStoreFileTreeUtils.storeAndSetFileTree(xoManager, fileTree, analysisRunVertex);
	    return fileTree;
	} catch (AnalysisStoreException e) {
	    if (!active) {
		currentTransaction.rollback();
	    }
	    throw e;
	} catch (IOException e) {
	    if (!active) {
		currentTransaction.rollback();
	    }
	    throw new AnalysisStoreException("Could not store tree.", e);
	} finally {
	    if (!active) {
		currentTransaction.commit();
	    }
	}
    }

    private AnalysisRunFileTree convertToAnalysisRunFileTree(Map<SourceCodeLocation, FileInformation> storedSources,
	    String rootNodeName) {
	AnalysisRunFileTree fileTree = new AnalysisRunFileTree(null, rootNodeName, false, null);
	for (Entry<SourceCodeLocation, FileInformation> entry : storedSources.entrySet()) {
	    SourceCodeLocation sourceCodeLocation = entry.getKey();
	    FileInformation fileInformation = entry.getValue();
	    HashId hashId = fileInformation.getHashId();
	    File internalPath = new File(sourceCodeLocation.getInternalLocation());
	    AnalysisRunFileTree currentNode = fileTree;
	    Iterator<Path> pathIterator = internalPath.toPath().iterator();
	    while (pathIterator.hasNext()) {
		Path pathElement = pathIterator.next();
		String name = pathElement.toFile().getName();
		AnalysisRunFileTree child = currentNode.findChild(name);
		if (child != null) {
		    currentNode = child;
		} else {
		    boolean isFile = !pathIterator.hasNext();
		    if (isFile) {
			currentNode = new AnalysisRunFileTree(currentNode, name, isFile, sourceCodeLocation, hashId);
		    } else {
			currentNode = new AnalysisRunFileTree(currentNode, name, isFile, sourceCodeLocation, hashId);
		    }
		}
	    }
	}
	TreeWalker.walkBackward(new TreeVisitor<AnalysisRunFileTree>() {

	    @Override
	    public WalkingAction visit(AnalysisRunFileTree tree) {
		if (tree.isFile()) {
		    return WalkingAction.PROCEED;
		}
		List<HashId> hashIds = new ArrayList<>();
		for (AnalysisRunFileTree child : tree.getChildren()) {
		    hashIds.add(child.getHashId());
		}
		HashId hashId = calculateDirectoryHashId(hashIds);
		tree.setHashId(hashId);
		return WalkingAction.PROCEED;
	    }

	}, fileTree);
	return fileTree;
    }

    /**
     * This method calculates a {@link HashId} for a directory with the given
     * list of hashIds of the children. The children are both: Files and
     * Directories contained in the directory for which this HashId is to be
     * calculated.
     * 
     * @param hashIds
     *            is {@link List} of {@link HashId} of the containing files and
     *            directories. This list might be empty, if the direcoty does
     *            not contain any children.
     * @return A {@link HashId} is returned for the directory.
     */
    public static HashId calculateDirectoryHashId(List<HashId> hashIds) {
	Collections.sort(hashIds, new Comparator<HashId>() {
	    @Override
	    public int compare(HashId o1, HashId o2) {
		return o1.toString().compareTo(o2.toString());
	    }
	});
	StringBuffer buffer = new StringBuffer();
	for (HashId hashId : hashIds) {
	    if (buffer.length() > 0) {
		buffer.append(',');
	    }
	    buffer.append(hashId.toString());
	}
	HashAlgorithm algorithm = AnalysisStoreServiceBean.DEFAULT_HASH_ALGORITHM;
	String hash = HashCodeGenerator.get(algorithm, buffer.toString());
	return new HashId(algorithm, hash);
    }

}
