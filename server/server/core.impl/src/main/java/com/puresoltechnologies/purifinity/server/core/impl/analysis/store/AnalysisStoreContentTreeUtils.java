package com.puresoltechnologies.purifinity.server.core.impl.analysis.store;

import java.io.IOException;
import java.util.Iterator;

import javax.inject.Inject;

import com.buschmais.xo.api.ResultIterable;
import com.buschmais.xo.api.ResultIterator;
import com.buschmais.xo.api.XOManager;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisRunFileTree;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.AnalysisRunVertex;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.ContentTreeDirectoryVertex;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.ContentTreeFileVertex;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.ContentTreeRootVertex;
import com.puresoltechnologies.purifinity.server.database.hadoop.utils.bloob.BloobService;

/**
 * This class contains methods to deal with the content tree in Titan graph
 * database.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisStoreContentTreeUtils {

    @Inject
    private AnalysisStoreUtils analysisStoreUtils;

    @Inject
    private BloobService bloob;

    /**
     * This method adds a new content tree node or content tree part to the
     * database.
     * 
     * @param xoManager
     *            is the {@link XOManager} to use for graph access.
     * @param fileTree
     *            is the run tree to add.
     * @param analysisRunVertex
     *            is the run vertex to add the tree to.
     * @throws AnalysisStoreException
     *             is thrown in case of analysis store utilities.
     * @throws IOException
     *             is thrown in case of IO issues.
     */
    public void addContentTree(XOManager xoManager, AnalysisRunFileTree fileTree, AnalysisRunVertex analysisRunVertex)
	    throws AnalysisStoreException, IOException {
	ResultIterable<ContentTreeRootVertex> contentRootResult = xoManager.find(ContentTreeRootVertex.class,
		fileTree.getHashId().toString());
	ResultIterator<ContentTreeRootVertex> contentRootIterator = contentRootResult.iterator();
	if (contentRootIterator.hasNext()) {
	    analysisRunVertex.setContentRoot(contentRootIterator.next());
	    return;
	}
	ContentTreeRootVertex contentRoot = xoManager.create(ContentTreeRootVertex.class);
	contentRoot.setHashId(fileTree.getHashId().toString());
	analysisRunVertex.setContentRoot(contentRoot);
	for (AnalysisRunFileTree child : fileTree.getChildren()) {
	    if (child.isFile()) {
		addContentTreeFileVertex(xoManager, child, contentRoot);
	    } else {
		addContentTreeDirectoryVertex(xoManager, child, contentRoot);
	    }
	}
    }

    /**
     * This method adds a new content tree node or content tree part to the
     * database.
     * 
     * @param xoManager
     *            is the {@link XOManager} to use for graph access.
     * @param fileTreeNode
     *            is the run tree node.
     * @param parentDirectory
     *            is the content tree parent node.
     * @return A {@link ContentTreeFileVertex} is returned.
     * @throws AnalysisStoreException
     *             is thrown in case of analysis store utilities.
     * @throws IOException
     *             is thrown in case of IO issues.
     */
    private ContentTreeFileVertex addContentTreeFileVertex(XOManager xoManager, AnalysisRunFileTree fileTreeNode,
	    ContentTreeDirectoryVertex parentDirectory) throws AnalysisStoreException, IOException {
	ResultIterable<ContentTreeFileVertex> find = xoManager.find(ContentTreeFileVertex.class,
		fileTreeNode.getHashId().toString());
	Iterator<ContentTreeFileVertex> iterator = find.iterator();
	if (iterator.hasNext()) {
	    ContentTreeFileVertex exstingFile = iterator.next();
	    if (iterator.hasNext()) {
		throw new AnalysisStoreException("Content tree vertex for '" + fileTreeNode.getHashId()
			+ "' was found multiple times. Database is inconsistent.");
	    }
	    parentDirectory.getFiles().add(exstingFile);
	    return exstingFile;
	} else {
	    ContentTreeFileVertex file = xoManager.create(ContentTreeFileVertex.class);
	    file.setHashId(fileTreeNode.getHashId().toString());
	    long fileSize = bloob.getFileSize(fileTreeNode.getHashId());
	    file.setSize(fileSize);

	    parentDirectory.getFiles().add(file);
	    return file;
	}
    }

    /**
     * This method adds a new content tree node or content tree part to the
     * database.
     * 
     * @param xoManager
     *            is the {@link XOManager} to use for graph access.
     * @param fileTreeNode
     *            is the run tree node.
     * @param parentDirectory
     *            is the content tree parent node.
     * @return A {@link ContentTreeDirectoryVertex} object is returned.
     * @throws AnalysisStoreException
     *             is thrown in case of analysis store utilities.
     * @throws IOException
     *             is thrown in case of IO issues.
     */
    private ContentTreeDirectoryVertex addContentTreeDirectoryVertex(XOManager xoManager,
	    AnalysisRunFileTree fileTreeNode, ContentTreeDirectoryVertex parentDirectory)
		    throws AnalysisStoreException, IOException {
	ResultIterable<ContentTreeDirectoryVertex> find = xoManager.find(ContentTreeDirectoryVertex.class,
		fileTreeNode.getHashId().toString());
	Iterator<ContentTreeDirectoryVertex> iterator = find.iterator();
	if (iterator.hasNext()) {
	    ContentTreeDirectoryVertex exstingDirectory = iterator.next();
	    if (iterator.hasNext()) {
		throw new AnalysisStoreException("Content tree vertex for '" + fileTreeNode.getHashId()
			+ "' was found multiple times. Database is inconsistent.");
	    }
	    parentDirectory.getDirectories().add(exstingDirectory);
	    return exstingDirectory;
	} else {
	    ContentTreeDirectoryVertex directory = xoManager.create(ContentTreeDirectoryVertex.class);
	    directory.setHashId(fileTreeNode.getHashId().toString());
	    parentDirectory.getDirectories().add(directory);
	    long size = 0;
	    long sizeRecursive = 0;
	    long files = 0;
	    long directories = 0;
	    long filesRecursive = 0;
	    long directoriesRecursive = 0;
	    for (AnalysisRunFileTree child : fileTreeNode.getChildren()) {
		if (child.isFile()) {
		    ContentTreeFileVertex childFile = addContentTreeFileVertex(xoManager, child, directory);
		    size += childFile.getSize();
		    sizeRecursive += childFile.getSize();
		    files++;
		    filesRecursive++;
		} else {
		    ContentTreeDirectoryVertex childDirectory = addContentTreeDirectoryVertex(xoManager, child,
			    directory);
		    sizeRecursive += childDirectory.getSizeRecursive();
		    directories++;
		    directoriesRecursive++;
		    directoriesRecursive += childDirectory.getDirectoryNumRecursive();
		    filesRecursive += childDirectory.getFileNumRecursive();
		}
	    }
	    directory.setSize(size);
	    directory.setSizeRecursive(sizeRecursive);
	    directory.setFileNum(files);
	    directory.setFileNumRecursive(filesRecursive);
	    directory.setDirectoryNum(directories);
	    directory.setDirectoryNumRecursive(directoriesRecursive);
	    return directory;
	}
    }

    public void checkAndRemoveAnalysisRunContent(XOManager xoManager, ContentTreeRootVertex contentRoot)
	    throws AnalysisStoreException {
	checkAndRemoveContentNode(xoManager, contentRoot);
    }

    private void checkAndRemoveContentNode(XOManager xoManager, ContentTreeDirectoryVertex contentVertex)
	    throws AnalysisStoreException {
	if (contentVertex.getDirectoriesFromRuns().size() > 0) {
	    // We have incoming edges, so this is a shared content. We are not
	    // allowed to remove it.
	    return;
	}

	for (ContentTreeDirectoryVertex directory : contentVertex.getDirectories()) {
	    checkAndRemoveContentNode(xoManager, directory);
	}
	for (ContentTreeFileVertex file : contentVertex.getFiles()) {
	    checkAndRemoveContentNode(xoManager, file);
	}

	HashId hashId = HashId.valueOf(contentVertex.getHashId());
	xoManager.delete(contentVertex);

	analysisStoreUtils.removeAnalysisFile(hashId);

	// FIXME: The evaluation values need to be removed!
	// boolean isFile = (Boolean) contentVertex
	// .getProperty(TitanElementNames.TREE_ELEMENT_IS_FILE);
	// if (isFile) {
	// EvaluatorStoreHBaseUtils.deleteFileEvaluation(hashId);
	// } else {
	// EvaluatorStoreHBaseUtils.deleteDirectoryEvaluation(hashId);
	// }
    }

    private void checkAndRemoveContentNode(XOManager xoManager, ContentTreeFileVertex contentVertex)
	    throws AnalysisStoreException {
	if (contentVertex.getFilesFromRuns().size() > 0) {
	    // We have incoming edges, so this is a shared content. We are not
	    // allowed to remove it.
	    return;
	}

	HashId hashId = HashId.valueOf(contentVertex.getHashId());
	xoManager.delete(contentVertex);

	analysisStoreUtils.removeAnalysisFile(hashId);

	// FIXME: The evaluation values need to be removed!
	// boolean isFile = (Boolean) contentVertex
	// .getProperty(TitanElementNames.TREE_ELEMENT_IS_FILE);
	// if (isFile) {
	// EvaluatorStoreHBaseUtils.deleteFileEvaluation(hashId);
	// } else {
	// EvaluatorStoreHBaseUtils.deleteDirectoryEvaluation(hashId);
	// }
    }
}
