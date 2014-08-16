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
import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.buschmais.xo.api.XOManager;
import com.buschmais.xo.api.XOTransaction;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.commons.misc.HashAlgorithm;
import com.puresoltechnologies.commons.misc.HashCodeGenerator;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.trees.TreeVisitor;
import com.puresoltechnologies.commons.trees.TreeWalker;
import com.puresoltechnologies.commons.trees.WalkingAction;
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
import com.puresoltechnologies.purifinity.server.systemmonitor.events.Event;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventLogger;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventSeverity;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventType;
import com.puresoltechnologies.xo.titan.impl.TitanStoreSession;
import com.thinkaurelius.titan.core.TitanGraph;
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
	private EventLogger eventLogger;

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
	public AnalysisProjectInformation createAnalysisProject(
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
			UUID projectUUID = UUID.randomUUID();
			Date creationTime = new Date();
			createAnalysisProjectVertex(projectUUID, creationTime);
			storeProjectAnalysisSettings(projectUUID, settings);
			return new AnalysisProjectInformation(projectUUID, creationTime);
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

	private void createAnalysisProjectVertex(UUID projectUUID, Date creationTime) {
		AnalysisProjectVertex analysisProject = xoManager
				.create(AnalysisProjectVertex.class);
		analysisProject.setProjectUUID(projectUUID.toString());
		analysisProject.setCreationTime(creationTime);
	}

	private void storeProjectAnalysisSettings(UUID projectUUID,
			AnalysisProjectSettings settings) {
		String name = settings.getName();
		String description = settings.getDescription();
		FileSearchConfiguration fileSearchConfiguration = settings
				.getFileSearchConfiguration();

		PreparedStatement preparedStatement = cassandraPreparedStatements
				.getPreparedStatement(session, "INSERT INTO "
						+ CassandraElementNames.ANALYSIS_PROJECT_SETTINGS_TABLE
						+ " (project_uuid, name, description, "
						+ "file_includes, file_excludes, "
						+ "location_includes, location_excludes, "
						+ "ignore_hidden, repository_location) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		BoundStatement bound = preparedStatement.bind(projectUUID, name,
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
			Iterable<Vertex> vertices = graph
					.query()
					.has(TitanStoreSession.XO_DISCRIMINATORS_PROPERTY
							+ AnalysisProjectVertex.NAME,
							AnalysisProjectVertex.NAME).vertices();
			Iterator<Vertex> vertexIterator = vertices.iterator();
			while (vertexIterator.hasNext()) {
				Vertex vertex = vertexIterator.next();
				UUID uuid = UUID
						.fromString((String) vertex
								.getProperty(TitanElementNames.ANALYSIS_PROJECT_UUID_PROPERTY));
				Date creationTime = (Date) vertex
						.getProperty(TitanElementNames.CREATION_TIME_PROPERTY);
				projects.add(new AnalysisProjectInformation(uuid, creationTime));
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
			projects.add(readAnalysisProject(information.getUUID()));
		}
		return projects;
	}

	@Override
	public AnalysisProjectInformation readAnalysisProjectInformation(
			UUID projectUUID) throws AnalysisStoreException {
		XOTransaction currentTransaction = xoManager.currentTransaction();
		boolean active = currentTransaction.isActive();
		if (!active) {
			currentTransaction.begin();
		}
		try {
			AnalysisProjectVertex analysisProjectVertex = AnalysisStoreTitanUtils
					.findAnalysisProjectVertex(xoManager, projectUUID);
			Date creationTime = analysisProjectVertex.getCreationTime();
			return new AnalysisProjectInformation(projectUUID, creationTime);
		} finally {
			if (!active) {
				currentTransaction.rollback();
			}
		}
	}

	@Override
	public void removeAnalysisProject(UUID projectUUID)
			throws AnalysisStoreException {
		// delete analysis runs first
		List<AnalysisRunInformation> runs = readAllRunInformation(projectUUID);
		for (AnalysisRunInformation run : runs) {
			removeAnalysisRun(projectUUID, run.getRunUUID());
		}
		// delete project
		deleteProject(projectUUID);
	}

	private void deleteProject(UUID projectUUID) throws AnalysisStoreException {
		AnalysisProjectVertex analysisProjectVertex = AnalysisStoreTitanUtils
				.findAnalysisProjectVertex(xoManager, projectUUID);
		xoManager.delete(analysisProjectVertex);
		analysisStoreCassandraUtils.removeProjectSettings(projectUUID);
	}

	@Override
	public AnalysisProjectSettings readAnalysisProjectSettings(UUID projectUUID)
			throws AnalysisStoreException {
		PreparedStatement preparedStatement = cassandraPreparedStatements
				.getPreparedStatement(
						session,
						"SELECT name, description, file_includes, file_excludes, location_includes, location_excludes, ignore_hidden, repository_location FROM "
								+ CassandraElementNames.ANALYSIS_PROJECT_SETTINGS_TABLE
								+ " WHERE project_uuid=?");
		BoundStatement boundStatement = preparedStatement.bind(projectUUID);
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
	public void updateAnalysisProjectSettings(UUID projectUUID,
			AnalysisProjectSettings settings) throws AnalysisStoreException {
		storeProjectAnalysisSettings(projectUUID, settings);
	}

	@Override
	public List<AnalysisRunInformation> readAllRunInformation(UUID projectUUID)
			throws AnalysisStoreException {
		XOTransaction currentTransaction = xoManager.currentTransaction();
		boolean active = currentTransaction.isActive();
		if (!active) {
			currentTransaction.begin();
		}
		try {
			AnalysisProjectVertex analysisProjectVertex = AnalysisStoreTitanUtils
					.findAnalysisProjectVertex(xoManager, projectUUID);

			List<ProjectToRunEdge> analysisRuns = analysisProjectVertex
					.getAnalysisRuns();

			List<AnalysisRunInformation> allRunInformation = new ArrayList<>();
			for (ProjectToRunEdge edge : analysisRuns) {
				AnalysisRunVertex run = edge.getRun();
				UUID runUUID = UUID.fromString(run.getRunUUID());
				Date startTime = run.getStartTime();
				long duration = run.getDuration();
				String description = run.getDescription();
				FileSearchConfiguration fileSearchConfiguration = readSearchConfiguration(runUUID);
				allRunInformation.add(new AnalysisRunInformation(projectUUID,
						runUUID, startTime, duration, description,
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
	public AnalysisRunInformation createAnalysisRun(UUID analysisProjectUUID,
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
			UUID runUUID = UUID.randomUUID();

			AnalysisProjectVertex analysisProjectVertex = AnalysisStoreTitanUtils
					.findAnalysisProjectVertex(xoManager, analysisProjectUUID);

			AnalysisStoreTitanUtils.createAnalysisRunVertex(xoManager,
					analysisProjectVertex, runUUID, creationTime, startTime,
					duration, description);

			analysisStoreCassandraUtils.writeAnalysisRunSettings(runUUID,
					fileSearchConfiguration);

			return new AnalysisRunInformation(analysisProjectUUID, runUUID,
					startTime, duration, description, fileSearchConfiguration);
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
	public AnalysisRunInformation readAnalysisRunInformation(UUID projectUUID,
			UUID runUUID) throws AnalysisStoreException {
		XOTransaction currentTransaction = xoManager.currentTransaction();
		boolean active = currentTransaction.isActive();
		if (!active) {
			currentTransaction.begin();
		}
		try {
			AnalysisRunVertex run = AnalysisStoreTitanUtils
					.findAnalysisRunVertex(xoManager, runUUID);
			if (run == null) {
				return null;
			}
			UUID uuidRead = UUID.fromString(run.getRunUUID());
			if (!runUUID.equals(uuidRead)) {
				throw new AnalysisStoreException("Anaysis run for '" + runUUID
						+ "' was not found, but a vertex with run_uuid='"
						+ uuidRead + "'. Database is inconsistent!");
			}
			Date startTime = run.getStartTime();
			long duration = run.getDuration();
			String description = run.getDescription();
			FileSearchConfiguration fileSearchConfiguration = readSearchConfiguration(runUUID);

			return new AnalysisRunInformation(projectUUID, runUUID, startTime,
					duration, description, fileSearchConfiguration);
		} finally {
			if (!active) {
				currentTransaction.rollback();
			}
		}
	}

	@Override
	public AnalysisRunInformation readLastAnalysisRun(UUID projectUUID)
			throws AnalysisStoreException {
		List<AnalysisRunInformation> allRunInformation = readAllRunInformation(projectUUID);
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
	public void removeAnalysisRun(UUID projectUUID, UUID runUUID)
			throws AnalysisStoreException {
		XOTransaction currentTransaction = xoManager.currentTransaction();
		boolean active = currentTransaction.isActive();
		if (!active) {
			currentTransaction.begin();
		}
		try {
			AnalysisRunVertex analysisRunVertex = AnalysisStoreTitanUtils
					.findAnalysisRunVertex(xoManager, runUUID);
			FileTreeDirectoryVertex rootDirectory = analysisRunVertex
					.getRootDirectory();
			ContentTreeRootVertex contentRoot = analysisRunVertex
					.getContentRoot();
			analysisStoreFileTreeUtils.deleteFileTree(xoManager, rootDirectory);
			// remove analysis run vertex
			xoManager.delete(analysisRunVertex);
			// clear caches
			analysisStoreCacheUtils
					.clearAnalysisRunCaches(projectUUID, runUUID);
			// remove run settings
			analysisStoreCassandraUtils.removeAnalysisRunSettings(projectUUID,
					runUUID);
			// remove analysis run results
			bigTableUtils.removeAnalysisRunResults(projectUUID, runUUID);
			// cleanup content tree
			analysisStoreContentTreeUtils.checkAndRemoveAnalysisRunContent(
					xoManager, contentRoot);
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
	public FileSearchConfiguration readSearchConfiguration(UUID runUUID)
			throws AnalysisStoreException {
		PreparedStatement preparedStatement = cassandraPreparedStatements
				.getPreparedStatement(
						session,
						"SELECT file_includes, file_excludes, location_includes, location_excludes, ignore_hidden FROM "
								+ CassandraElementNames.ANALYSIS_RUN_SETTINGS_TABLE
								+ " WHERE run_uuid=?");
		BoundStatement boundStatement = preparedStatement.bind(runUUID);
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
	public AnalysisFileTree readAnalysisFileTree(UUID projectUUID, UUID runUUID)
			throws AnalysisStoreException {
		XOTransaction currentTransaction = xoManager.currentTransaction();
		boolean active = currentTransaction.isActive();
		if (!active) {
			currentTransaction.begin();
		}
		try {
			AnalysisFileTree analysisFileTree = analysisStoreCacheUtils
					.readCachedAnalysisFileTree(projectUUID, runUUID);
			if (analysisFileTree != null) {
				return analysisFileTree;
			}
			AnalysisRunVertex analysisRunVertex = AnalysisStoreTitanUtils
					.findAnalysisRunVertex(xoManager, runUUID);
			analysisFileTree = analysisStoreFileTreeUtils
					.createAnalysisFileTree(analysisRunVertex);
			analysisStoreCacheUtils.cacheAnalysisFileTree(projectUUID, runUUID,
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
	public AnalysisProject readAnalysisProject(UUID projectUUID)
			throws AnalysisStoreException {
		AnalysisProjectInformation information = readAnalysisProjectInformation(projectUUID);
		AnalysisProjectSettings settings = readAnalysisProjectSettings(projectUUID);
		return new AnalysisProject(information, settings);
	}

	@Override
	public AnalysisRun readAnalysisRun(AnalysisRunInformation information)
			throws AnalysisStoreException {
		AnalysisFileTree analysisFileTree = readAnalysisFileTree(
				information.getProjectUUID(), information.getRunUUID());
		return new AnalysisRun(information, analysisFileTree);
	}

	@Override
	public AnalysisRunFileTree createAndStoreFileAndContentTree(
			UUID projectUUID, UUID runUUID, String rootNodeName,
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
					.findAnalysisRunVertex(xoManager, runUUID);
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
