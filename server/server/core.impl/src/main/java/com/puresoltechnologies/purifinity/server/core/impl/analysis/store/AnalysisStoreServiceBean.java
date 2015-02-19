package com.puresoltechnologies.purifinity.server.core.impl.analysis.store;

import java.io.File;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

import com.buschmais.xo.api.XOException;
import com.buschmais.xo.api.XOManager;
import com.buschmais.xo.api.XOTransaction;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.misc.hash.HashAlgorithm;
import com.puresoltechnologies.commons.misc.hash.HashCodeGenerator;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.commons.misc.io.FileSearchConfiguration;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisRunFileTree;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreService;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.AnalysisProjectVertex;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.AnalysisRunVertex;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.ContentTreeRootVertex;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.FileTreeDirectoryVertex;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.ProjectToRunEdge;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.TitanXOManager;
import com.puresoltechnologies.purifinity.server.database.cassandra.AnalysisStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraElementNames;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraPreparedStatements;
import com.puresoltechnologies.purifinity.server.database.titan.TitanElementNames;
import com.puresoltechnologies.server.systemmonitor.core.api.events.Event;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventLoggerRemote;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventSeverity;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventType;
import com.puresoltechnologies.trees.TreeVisitor;
import com.puresoltechnologies.trees.TreeWalker;
import com.puresoltechnologies.trees.WalkingAction;
import com.puresoltechnologies.xo.titan.impl.TitanStoreSession;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.TitanGraphQuery;
import com.tinkerpop.blueprints.Vertex;

@Stateless
public class AnalysisStoreServiceBean implements AnalysisStoreService {

    public static final HashAlgorithm DEFAULT_HASH_ALGORITHM = HashAlgorithm.SHA256;
    public static final MessageDigest DEFAULT_HASH;
    static {
	try {
	    DEFAULT_HASH = MessageDigest.getInstance(DEFAULT_HASH_ALGORITHM
		    .getAlgorithmName());
	} catch (NoSuchAlgorithmException e) {
	    throw new RuntimeException(
		    "Could not initialize default hash algorithm for analysis store.",
		    e);
	}
    }

    public static final String COMPONENT_NAME = "AnalysisStoreService";

    @Inject
    private EventLoggerRemote eventLogger;

    @Inject
    @AnalysisStoreKeyspace
    private Session session;

    @Inject
    private AnalysisStoreCassandraUtils analysisStoreCassandraUtils;

    @Inject
    private AnalysisStoreFileTreeUtils analysisStoreFileTreeUtils;

    @Inject
    private AnalysisStoreContentTreeUtils analysisStoreContentTreeUtils;

    @Inject
    private AnalysisStoreCacheUtils analysisStoreCacheUtils;

    @Inject
    private CassandraPreparedStatements cassandraPreparedStatements;

    @Inject
    private BigTableUtils bigTableUtils;

    @Inject
    private TitanGraph graph;

    @Inject
    @TitanXOManager
    private XOManager xoManager;

    @Override
    public AnalysisProjectInformation createAnalysisProject(String projectId,
	    AnalysisProjectSettings settings) throws AnalysisStoreException {
	XOTransaction currentTransaction = xoManager.currentTransaction();
	boolean active = currentTransaction.isActive();
	if (!active) {
	    currentTransaction.begin();
	}
	try {
	    eventLogger.logEvent(new Event(COMPONENT_NAME, 0x01,
		    EventType.USER_ACTION, EventSeverity.INFO,
		    "Create project..."));
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
	AnalysisProjectVertex analysisProject = xoManager
		.create(AnalysisProjectVertex.class);
	analysisProject.setProjectId(projectId);
	analysisProject.setCreationTime(creationTime);
    }

    private void storeProjectAnalysisSettings(String projectId,
	    AnalysisProjectSettings settings) {
	String name = settings.getName();
	String description = settings.getDescription();
	FileSearchConfiguration fileSearchConfiguration = settings
		.getFileSearchConfiguration();

	PreparedStatement preparedStatement = cassandraPreparedStatements
		.getPreparedStatement(session, "INSERT INTO "
			+ CassandraElementNames.ANALYSIS_PROJECT_SETTINGS_TABLE
			+ " (project_id, name, description, "
			+ "file_includes, file_excludes, "
			+ "location_includes, location_excludes, "
			+ "ignore_hidden, repository_location) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
	BoundStatement bound = preparedStatement.bind(projectId, name,
		description, fileSearchConfiguration.getFileIncludes(),
		fileSearchConfiguration.getFileExcludes(),
		fileSearchConfiguration.getLocationIncludes(),
		fileSearchConfiguration.getLocationExcludes(),
		fileSearchConfiguration.isIgnoreHidden(),
		settings.getRepositoryLocation());
	session.execute(bound);
    }

    @Override
    public List<AnalysisProjectInformation> readAllAnalysisProjectInformation()
	    throws AnalysisStoreException {
	List<AnalysisProjectInformation> projects = new ArrayList<>();
	if (graph == null) {
	    return projects;
	}
	try {
	    TitanGraphQuery<?> query = graph.query().has(
		    TitanStoreSession.XO_DISCRIMINATORS_PROPERTY
			    + AnalysisProjectVertex.NAME,
		    AnalysisProjectVertex.NAME);
	    Iterable<Vertex> vertices = query.vertices();
	    Iterator<Vertex> vertexIterator = vertices.iterator();
	    while (vertexIterator.hasNext()) {
		Vertex vertex = vertexIterator.next();
		String projectId = vertex
			.getProperty(TitanElementNames.ANALYSIS_PROJECT_ID_PROPERTY);
		Date creationTime = (Date) vertex
			.getProperty(TitanElementNames.CREATION_TIME_PROPERTY);
		projects.add(new AnalysisProjectInformation(projectId,
			creationTime));
	    }
	    return projects;
	} finally {
	    graph.rollback();
	}
    }

    @Override
    public List<AnalysisProject> readAllAnalysisProjects()
	    throws AnalysisStoreException {
	List<AnalysisProject> projects = new ArrayList<>();
	List<AnalysisProjectInformation> allProjectInformation = readAllAnalysisProjectInformation();
	for (AnalysisProjectInformation information : allProjectInformation) {
	    projects.add(readAnalysisProject(information.getProjectId()));
	}
	return projects;
    }

    @Override
    public AnalysisProjectInformation readAnalysisProjectInformation(
	    String projectId) throws AnalysisStoreException {
	XOTransaction currentTransaction = xoManager.currentTransaction();
	boolean active = currentTransaction.isActive();
	if (!active) {
	    currentTransaction.begin();
	}
	try {
	    AnalysisProjectVertex analysisProjectVertex = AnalysisStoreTitanUtils
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
    public void removeAnalysisProject(String projectId)
	    throws AnalysisStoreException {
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
	    AnalysisProjectVertex analysisProjectVertex = AnalysisStoreTitanUtils
		    .findAnalysisProjectVertex(xoManager, projectId);
	    xoManager.delete(analysisProjectVertex);
	    analysisStoreCassandraUtils.removeProjectSettings(projectId);
	    eventLogger.logEvent(new Event(COMPONENT_NAME, 0x01,
		    EventType.USER_ACTION, EventSeverity.INFO,
		    "Deleted project '" + projectId + "'"));
	    xoManager.currentTransaction().commit();
	} catch (XOException e) {
	    xoManager.currentTransaction().rollback();
	}
    }

    @Override
    public AnalysisProjectSettings readAnalysisProjectSettings(String projectId)
	    throws AnalysisStoreException {
	PreparedStatement preparedStatement = cassandraPreparedStatements
		.getPreparedStatement(
			session,
			"SELECT name, description, file_includes, file_excludes, location_includes, location_excludes, ignore_hidden, repository_location FROM "
				+ CassandraElementNames.ANALYSIS_PROJECT_SETTINGS_TABLE
				+ " WHERE project_id=?");
	BoundStatement boundStatement = preparedStatement.bind(projectId);
	ResultSet resultSet = session.execute(boundStatement);
	Row result = resultSet.one();
	if (result == null) {
	    return null;
	}
	String name = result.getString("name");
	String description = result.getString("description");
	List<String> fileIncludes = result.getList("file_includes",
		String.class);
	List<String> fileExcludes = result.getList("file_excludes",
		String.class);
	List<String> locationIncludes = result.getList("location_includes",
		String.class);
	List<String> locationExcludes = result.getList("location_excludes",
		String.class);
	boolean ignoreHidden = result.getBool("ignore_hidden");
	FileSearchConfiguration fileSearchConfiguration = new FileSearchConfiguration(
		locationIncludes, locationExcludes, fileIncludes, fileExcludes,
		ignoreHidden);
	Map<String, String> repositoryLocationMap = result.getMap(
		"repository_location", String.class, String.class);
	Properties repositoryLocation = new Properties();
	for (Object key : repositoryLocationMap.keySet()) {
	    repositoryLocation.put(key.toString(),
		    repositoryLocationMap.get(key).toString());
	}
	return new AnalysisProjectSettings(name, description,
		fileSearchConfiguration, repositoryLocation);
    }

    @Override
    public void updateAnalysisProjectSettings(String projectId,
	    AnalysisProjectSettings settings) throws AnalysisStoreException {
	storeProjectAnalysisSettings(projectId, settings);
    }

    @Override
    public List<AnalysisRunInformation> readAllRunInformation(String projectId)
	    throws AnalysisStoreException {
	XOTransaction currentTransaction = xoManager.currentTransaction();
	boolean active = currentTransaction.isActive();
	if (!active) {
	    currentTransaction.begin();
	}
	try {
	    AnalysisProjectVertex analysisProjectVertex = AnalysisStoreTitanUtils
		    .findAnalysisProjectVertex(xoManager, projectId);

	    List<ProjectToRunEdge> analysisRuns = analysisProjectVertex
		    .getAnalysisRuns();

	    List<AnalysisRunInformation> allRunInformation = new ArrayList<>();
	    for (ProjectToRunEdge edge : analysisRuns) {
		AnalysisRunVertex run = edge.getRun();
		long runId = System.currentTimeMillis();
		Date startTime = run.getStartTime();
		long duration = run.getDuration();
		String description = run.getDescription();
		FileSearchConfiguration fileSearchConfiguration = readSearchConfiguration(
			projectId, runId);
		allRunInformation.add(new AnalysisRunInformation(projectId,
			runId, startTime, duration, description,
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
    public AnalysisRunInformation createAnalysisRun(String projectId,
	    Date startTime, long duration, String description,
	    FileSearchConfiguration fileSearchConfiguration)
	    throws AnalysisStoreException {
	XOTransaction currentTransaction = xoManager.currentTransaction();
	boolean active = currentTransaction.isActive();
	if (!active) {
	    currentTransaction.begin();
	}
	try {
	    Date creationTime = new Date();
	    long runId = System.currentTimeMillis();

	    AnalysisProjectVertex analysisProjectVertex = AnalysisStoreTitanUtils
		    .findAnalysisProjectVertex(xoManager, projectId);

	    AnalysisStoreTitanUtils.createAnalysisRunVertex(xoManager,
		    analysisProjectVertex, runId, creationTime, startTime,
		    duration, description);

	    analysisStoreCassandraUtils.writeAnalysisRunSettings(projectId,
		    runId, fileSearchConfiguration);

	    return new AnalysisRunInformation(projectId, runId, startTime,
		    duration, description, fileSearchConfiguration);
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
    public AnalysisRunInformation readAnalysisRunInformation(String projectId,
	    long runId) throws AnalysisStoreException {
	XOTransaction currentTransaction = xoManager.currentTransaction();
	boolean active = currentTransaction.isActive();
	if (!active) {
	    currentTransaction.begin();
	}
	try {
	    AnalysisRunVertex run = AnalysisStoreTitanUtils
		    .findAnalysisRunVertex(xoManager, runId);
	    if (run == null) {
		return null;
	    }
	    long runIdRead = run.getRunId();
	    if (runId != runIdRead) {
		throw new AnalysisStoreException("Anaysis run for '" + runId
			+ "' was not found, but a vertex with run_uuid='"
			+ runIdRead + "'. Database is inconsistent!");
	    }
	    Date startTime = run.getStartTime();
	    long duration = run.getDuration();
	    String description = run.getDescription();
	    FileSearchConfiguration fileSearchConfiguration = readSearchConfiguration(
		    projectId, runId);

	    return new AnalysisRunInformation(projectId, runId, startTime,
		    duration, description, fileSearchConfiguration);
	} finally {
	    if (!active) {
		currentTransaction.rollback();
	    }
	}
    }

    @Override
    public AnalysisRunInformation readLastAnalysisRun(String projectId)
	    throws AnalysisStoreException {
	List<AnalysisRunInformation> allRunInformation = readAllRunInformation(projectId);
	Date time = null;
	AnalysisRunInformation lastRun = null;
	for (AnalysisRunInformation runInformation : allRunInformation) {
	    if ((lastRun == null)
		    || (runInformation.getStartTime().compareTo(time) > 0)) {
		lastRun = runInformation;
		time = runInformation.getStartTime();
	    }
	}
	return lastRun;
    }

    @Override
    public void removeAnalysisRun(String projectId, long runId)
	    throws AnalysisStoreException {
	XOTransaction currentTransaction = xoManager.currentTransaction();
	boolean active = currentTransaction.isActive();
	if (!active) {
	    currentTransaction.begin();
	}
	try {
	    AnalysisRunVertex analysisRunVertex = AnalysisStoreTitanUtils
		    .findAnalysisRunVertex(xoManager, runId);
	    if (analysisRunVertex != null) {
		FileTreeDirectoryVertex rootDirectory = analysisRunVertex
			.getRootDirectory();
		ContentTreeRootVertex contentRoot = analysisRunVertex
			.getContentRoot();
		analysisStoreFileTreeUtils.deleteFileTree(xoManager,
			rootDirectory);
		// remove analysis run vertex
		xoManager.delete(analysisRunVertex);
		// clear caches
		analysisStoreCacheUtils
			.clearAnalysisRunCaches(projectId, runId);
		// remove run settings
		analysisStoreCassandraUtils.removeAnalysisRunSettings(
			projectId, runId);
		// remove analysis run results
		bigTableUtils.removeAnalysisRunResults(projectId, runId);
		// cleanup content tree
		analysisStoreContentTreeUtils.checkAndRemoveAnalysisRunContent(
			xoManager, contentRoot);
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
    public FileSearchConfiguration readSearchConfiguration(String projectId,
	    long runId) throws AnalysisStoreException {
	PreparedStatement preparedStatement = cassandraPreparedStatements
		.getPreparedStatement(
			session,
			"SELECT file_includes, file_excludes, location_includes, location_excludes, ignore_hidden FROM "
				+ CassandraElementNames.ANALYSIS_RUN_SETTINGS_TABLE
				+ " WHERE project_id=? AND run_id=?");
	BoundStatement boundStatement = preparedStatement
		.bind(projectId, runId);
	ResultSet resultSet = session.execute(boundStatement);
	Row result = resultSet.one();
	if (result == null) {
	    return null;
	}
	List<String> fileIncludes = result.getList("file_includes",
		String.class);
	List<String> fileExcludes = result.getList("file_excludes",
		String.class);
	List<String> locationIncludes = result.getList("location_includes",
		String.class);
	List<String> locationExcludes = result.getList("location_excludes",
		String.class);
	boolean ignoreHidden = result.getBool("ignore_hidden");
	return new FileSearchConfiguration(locationIncludes, locationExcludes,
		fileIncludes, fileExcludes, ignoreHidden);
    }

    @Override
    public AnalysisFileTree readAnalysisFileTree(String projectId, long runId)
	    throws AnalysisStoreException {
	XOTransaction currentTransaction = xoManager.currentTransaction();
	boolean active = currentTransaction.isActive();
	if (!active) {
	    currentTransaction.begin();
	}
	try {
	    AnalysisFileTree analysisFileTree = analysisStoreCacheUtils
		    .readCachedAnalysisFileTree(projectId, runId);
	    if (analysisFileTree != null) {
		return analysisFileTree;
	    }
	    AnalysisRunVertex analysisRunVertex = AnalysisStoreTitanUtils
		    .findAnalysisRunVertex(xoManager, runId);
	    analysisFileTree = analysisStoreFileTreeUtils
		    .createAnalysisFileTree(analysisRunVertex);
	    analysisStoreCacheUtils.cacheAnalysisFileTree(projectId, runId,
		    analysisFileTree);
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
    public AnalysisProject readAnalysisProject(String projectId)
	    throws AnalysisStoreException {
	AnalysisProjectInformation information = readAnalysisProjectInformation(projectId);
	AnalysisProjectSettings settings = readAnalysisProjectSettings(projectId);
	return new AnalysisProject(information, settings);
    }

    @Override
    public AnalysisRun readAnalysisRun(AnalysisRunInformation information)
	    throws AnalysisStoreException {
	AnalysisFileTree analysisFileTree = readAnalysisFileTree(
		information.getProjectId(), information.getRunId());
	return new AnalysisRun(information, analysisFileTree);
    }

    @Override
    public AnalysisRunFileTree createAndStoreFileAndContentTree(
	    String projectId, long runId, String rootNodeName,
	    Map<SourceCodeLocation, HashId> storedSources)
	    throws AnalysisStoreException {
	XOTransaction currentTransaction = xoManager.currentTransaction();
	boolean active = currentTransaction.isActive();
	if (!active) {
	    currentTransaction.begin();
	}
	try {
	    AnalysisRunFileTree fileTree = convertToAnalysisRunFileTree(
		    storedSources, rootNodeName);
	    AnalysisRunVertex analysisRunVertex = AnalysisStoreTitanUtils
		    .findAnalysisRunVertex(xoManager, runId);
	    analysisStoreContentTreeUtils.addContentTree(xoManager, fileTree,
		    analysisRunVertex);
	    analysisStoreFileTreeUtils.addFileTree(xoManager, fileTree,
		    analysisRunVertex);
	    return fileTree;
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

    private AnalysisRunFileTree convertToAnalysisRunFileTree(
	    Map<SourceCodeLocation, HashId> storedSources, String rootNodeName) {
	AnalysisRunFileTree fileTree = new AnalysisRunFileTree(null,
		rootNodeName, false, null);
	for (Entry<SourceCodeLocation, HashId> entry : storedSources.entrySet()) {
	    SourceCodeLocation sourceCodeLocation = entry.getKey();
	    HashId hashId = entry.getValue();
	    File internalPath = new File(
		    sourceCodeLocation.getInternalLocation());
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
			currentNode = new AnalysisRunFileTree(currentNode,
				name, isFile, sourceCodeLocation, hashId);
		    } else {
			currentNode = new AnalysisRunFileTree(currentNode,
				name, isFile, sourceCodeLocation, hashId);
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
