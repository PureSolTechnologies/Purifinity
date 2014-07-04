package com.puresoltechnologies.purifinity.server.core.impl.analysis.store;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import com.buschmais.xo.api.ResultIterable;
import com.buschmais.xo.api.ResultIterator;
import com.buschmais.xo.api.XOManager;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.misc.Version;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.framework.commons.utils.PropertiesUtils;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisRunFileTree;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.common.SourceCodeLocationCreator;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.AnalysisRunVertex;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.ContentTreeDirectoryVertex;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.ContentTreeFileVertex;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.FileTreeDirectoryVertex;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.FileTreeFileVertex;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.FileTreeRootVertex;
import com.puresoltechnologies.purifinity.server.database.cassandra.AnalysisStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraElementNames;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraPreparedStatements;

/**
 * This class contains functionality for {@link AnalysisFileTree}s in Titan
 * graph database.
 * 
 * @author Rick-Rainer Ludwig -
 */
public class AnalysisStoreFileTreeUtils {

    @Inject
    @AnalysisStoreKeyspace
    private Session session;

    @Inject
    private CassandraPreparedStatements preparedStatements;

    /**
     * This method adds a new file tree to a Analysis Run vertex.
     * 
     * @param analysisStore
     * @param progressObserver
     * @param graph
     * @param fileTree
     * @param analysisRunVertex
     * @return
     * @throws AnalysisStoreException
     */
    public void addFileTree(XOManager xoManager, AnalysisRunFileTree fileTree,
	    AnalysisRunVertex analysisRunVertex) throws AnalysisStoreException {
	ResultIterable<FileTreeRootVertex> contentRootResult = xoManager.find(
		FileTreeRootVertex.class, fileTree.getHashId().toString());
	ResultIterator<FileTreeRootVertex> fileRootIterator = contentRootResult
		.iterator();
	if (fileRootIterator.hasNext()) {
	    analysisRunVertex.setRootDirectory(fileRootIterator.next());
	    return;
	}
	FileTreeRootVertex fileRoot = xoManager
		.create(FileTreeRootVertex.class);
	fileRoot.setHashId(fileTree.getHashId().toString());
	fileRoot.setName(fileTree.getName());
	analysisRunVertex.setRootDirectory(fileRoot);
	for (AnalysisRunFileTree child : fileTree.getChildren()) {
	    if (child.isFile()) {
		addFileTreeFileVertex(xoManager, child, fileRoot);
	    } else {
		addFileTreeDirectoryVertex(xoManager, child, fileRoot);
	    }
	}
    }

    /**
     * This method adds a new file tree node to a parent vertex.
     * 
     * @param analysisStore
     * @param progressObserver
     * @param graph
     * @param fileTreeNode
     * @param parentVertex
     * @param edgeLabel
     * @return
     * @throws AnalysisStoreException
     */
    private FileTreeFileVertex addFileTreeFileVertex(XOManager xoManager,
	    AnalysisRunFileTree fileTreeNode,
	    FileTreeDirectoryVertex parentVertex) throws AnalysisStoreException {
	FileTreeFileVertex file = xoManager.create(FileTreeFileVertex.class);
	file.setHashId(fileTreeNode.getHashId().toString());
	file.setName(fileTreeNode.getName());
	file.setSourceLocation(PropertiesUtils.toString(fileTreeNode
		.getSourceCodeLocation().getSerialization()));
	parentVertex.getFiles().add(file);

	ResultIterable<ContentTreeFileVertex> directoryContentResult = xoManager
		.find(ContentTreeFileVertex.class, file.getHashId().toString());
	Iterator<ContentTreeFileVertex> fileContentIterator = directoryContentResult
		.iterator();
	if (!fileContentIterator.hasNext()) {
	    throw new AnalysisStoreException(
		    "Could not find content node for file tree node '"
			    + fileTreeNode.toString() + "'.");
	}
	ContentTreeFileVertex fileContent = fileContentIterator.next();
	if (fileContentIterator.hasNext()) {
	    throw new AnalysisStoreException(
		    "Multiple content nodes found for file tree node '"
			    + fileTreeNode.toString() + "'.");
	}

	file.setContent(fileContent);
	file.setSize(fileContent.getSize());

	return file;
    }

    /**
     * This method adds a new file tree node to a parent vertex.
     * 
     * @param analysisStore
     * @param progressObserver
     * @param graph
     * @param fileTreeNode
     * @param parentVertex
     * @param edgeLabel
     * @return
     * @throws AnalysisStoreException
     */
    private FileTreeDirectoryVertex addFileTreeDirectoryVertex(
	    XOManager xoManager, AnalysisRunFileTree fileTreeNode,
	    FileTreeDirectoryVertex parentVertex) throws AnalysisStoreException {
	FileTreeDirectoryVertex directory = xoManager
		.create(FileTreeDirectoryVertex.class);
	directory.setHashId(fileTreeNode.getHashId().toString());
	directory.setName(fileTreeNode.getName());
	parentVertex.getDirectories().add(directory);

	ResultIterable<ContentTreeDirectoryVertex> directoryContentResult = xoManager
		.find(ContentTreeDirectoryVertex.class, directory.getHashId()
			.toString());
	Iterator<ContentTreeDirectoryVertex> directoryContentIterator = directoryContentResult
		.iterator();
	if (!directoryContentIterator.hasNext()) {
	    throw new AnalysisStoreException(
		    "Could not find content node for directory tree node '"
			    + fileTreeNode.toString() + "'.");
	}
	ContentTreeDirectoryVertex directoryContent = directoryContentIterator
		.next();
	if (directoryContentIterator.hasNext()) {
	    throw new AnalysisStoreException(
		    "Multiple content nodes found for directory tree node '"
			    + fileTreeNode.toString() + "'.");
	}

	directory.setContent(directoryContent);
	directory.setSize(directoryContent.getSize());
	directory.setSizeRecursive(directoryContent.getSizeRecursive());
	directory.setFileNum(directoryContent.getFileNum());
	directory.setFileNumRecursive(directoryContent.getFileNumRecursive());
	directory.setDirectoryNum(directoryContent.getDirectoryNum());
	directory.setDirectoryNumRecursive(directoryContent
		.getDirectoryNumRecursive());

	for (AnalysisRunFileTree child : fileTreeNode.getChildren()) {
	    if (child.isFile()) {
		addFileTreeFileVertex(xoManager, child, directory);
	    } else {
		addFileTreeDirectoryVertex(xoManager, child, directory);
	    }
	}
	return directory;
    }

    /**
     * This method reads the analysis file tree from a analysis run.
     * 
     * @param projectUUID
     * @param runUUID
     * @return
     * @throws AnalysisStoreException
     */
    public AnalysisFileTree createAnalysisFileTree(
	    AnalysisRunVertex analysisRunVertex) throws AnalysisStoreException {
	FileTreeRootVertex rootDirectory = analysisRunVertex.getRootDirectory();
	if (rootDirectory == null) {
	    return null;
	}
	return createAnalysisFileTreeNode(rootDirectory, null);
    }

    /**
     * This method creates a new {@link AnalysisFileTree} object out of a single
     * file tree node.
     * 
     * @param fileTreeVertex
     * @param parent
     * @return
     * @throws AnalysisStoreException
     */
    private AnalysisFileTree createAnalysisFileTreeNode(
	    FileTreeDirectoryVertex fileTreeDirectory, AnalysisFileTree parent)
	    throws AnalysisStoreException {
	String name = fileTreeDirectory.getName();
	SourceCodeLocation sourceCodeLocation = null;
	long size = fileTreeDirectory.getSize();
	long sizeRecursive = fileTreeDirectory.getSizeRecursive();

	HashId hashId = HashId.valueOf(fileTreeDirectory.getHashId());

	AnalysisFileTree hashIdFileTree = new AnalysisFileTree(parent, name,
		hashId, false, size, sizeRecursive, sourceCodeLocation, null);

	List<FileTreeDirectoryVertex> directories = fileTreeDirectory
		.getDirectories();
	if (directories.size() > 0) {
	    for (FileTreeDirectoryVertex directory : directories) {
		createAnalysisFileTreeNode(directory, hashIdFileTree);
	    }
	}
	for (FileTreeFileVertex file : fileTreeDirectory.getFiles()) {
	    createAnalysisFileTreeNode(file, hashIdFileTree);
	}

	return hashIdFileTree;
    }

    /**
     * This method creates a new {@link AnalysisFileTree} object out of a single
     * file tree node.
     * 
     * @param fileTreeVertex
     * @param parent
     * @return
     * @throws AnalysisStoreException
     */
    private AnalysisFileTree createAnalysisFileTreeNode(
	    FileTreeFileVertex fileTreeFile, AnalysisFileTree parent)
	    throws AnalysisStoreException {
	String name = fileTreeFile.getName();
	boolean isFile = true;
	String serializedSourceCodeLocation = fileTreeFile.getSourceLocation();
	SourceCodeLocation sourceCodeLocation = SourceCodeLocationCreator
		.createFromSerialization(PropertiesUtils
			.fromString(serializedSourceCodeLocation));
	long size = fileTreeFile.getSize();

	HashId hashId = HashId.valueOf(fileTreeFile.getContent().getHashId());

	List<AnalysisInformation> analyses = readAnalyses(hashId);
	return new AnalysisFileTree(parent, name, hashId, isFile, size, size,
		sourceCodeLocation, analyses);
    }

    /**
     * This method reads all analyzes which are attached to a file tree node.
     * 
     * @param fileTreeVertex
     * @param hash
     * @return
     */
    private List<AnalysisInformation> readAnalyses(HashId hashId) {
	List<AnalysisInformation> analyses = new ArrayList<AnalysisInformation>();

	PreparedStatement preparedStatement = preparedStatements
		.getPreparedStatement(session, "SELECT * FROM "
			+ CassandraElementNames.ANALYSIS_ANALYZES_TABLE
			+ " WHERE hashId=?;");
	BoundStatement boundStatement = preparedStatement.bind(hashId
		.toString());
	ResultSet resultSet = session.execute(boundStatement);
	while (!resultSet.isExhausted()) {
	    Row row = resultSet.one();
	    Date time = row.getDate("time");
	    long duration = row.getLong("duration");
	    String language = row.getString("language");
	    String languageVersion = row.getString("language_version");
	    Version pluginVersion = Version.valueOf(row
		    .getString("plugin_version"));
	    boolean successful = row.getBool("successful");
	    String message = row.getString("analyzer_message");
	    AnalysisInformation information = new AnalysisInformation(hashId,
		    time, duration, successful, language, languageVersion,
		    pluginVersion, message);
	    analyses.add(information);
	}
	return analyses;
    }

    /**
     * This method deletes a file tree from an Analysis Run. This should always
     * be accompanied with a deletion of the Analsysis Run, because an Analysis
     * Run without a File Tree is meaningless.
     * 
     * @param fileTreeVertex
     *            is the vertex of the file to be deleted.
     * @throws AnalysisStoreException
     */
    public void deleteFileTree(XOManager xoManager,
	    FileTreeDirectoryVertex rootDirectory)
	    throws AnalysisStoreException {
	for (FileTreeDirectoryVertex directoryVertex : rootDirectory
		.getDirectories()) {
	    deleteFileTree(xoManager, directoryVertex);
	}
	for (FileTreeFileVertex fileVertex : rootDirectory.getFiles()) {
	    xoManager.delete(fileVertex);
	}
	xoManager.delete(rootDirectory);

    }

}
