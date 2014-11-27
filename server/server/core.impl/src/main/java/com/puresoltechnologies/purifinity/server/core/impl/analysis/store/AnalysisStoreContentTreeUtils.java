package com.puresoltechnologies.purifinity.server.core.impl.analysis.store;

import java.util.Iterator;

import javax.inject.Inject;

import com.buschmais.xo.api.ResultIterable;
import com.buschmais.xo.api.ResultIterator;
import com.buschmais.xo.api.XOManager;
import com.puresoltechnologies.commons.os.HashId;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisRunFileTree;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.AnalysisRunVertex;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.ContentTreeDirectoryVertex;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.ContentTreeFileVertex;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.ContentTreeRootVertex;

/**
 * This class contains methods to deal with the content tree in Titan graph
 * database.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisStoreContentTreeUtils {

	@Inject
	private AnalysisStoreCassandraUtils analysisStoreCassandraUtils;

	@Inject
	private AnalysisStoreDAO analysisStoreDAO;

	/**
	 * This method adds a new content tree node or content tree part to the
	 * database.
	 * 
	 * @param progressObserver
	 *            is an optional observer to be informed about progress (may be
	 *            null).
	 * @param graph
	 * @param fileTree
	 * @param parentVertex
	 * @param edgeLabel
	 * @return
	 * @throws AnalysisStoreException
	 */
	public void addContentTree(XOManager xoManager,
			AnalysisRunFileTree fileTree, AnalysisRunVertex analysisRunVertex)
			throws AnalysisStoreException {
		ResultIterable<ContentTreeRootVertex> contentRootResult = xoManager
				.find(ContentTreeRootVertex.class, fileTree.getHashId()
						.toString());
		ResultIterator<ContentTreeRootVertex> contentRootIterator = contentRootResult
				.iterator();
		if (contentRootIterator.hasNext()) {
			analysisRunVertex.setContentRoot(contentRootIterator.next());
			return;
		}
		ContentTreeRootVertex contentRoot = xoManager
				.create(ContentTreeRootVertex.class);
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
	 * @param progressObserver
	 *            is an optional observer to be informed about progress (may be
	 *            null).
	 * @param graph
	 * @param fileTreeNode
	 * @param parentDirectory
	 * @param edgeLabel
	 * @return
	 * @throws AnalysisStoreException
	 */
	private ContentTreeFileVertex addContentTreeFileVertex(XOManager xoManager,
			AnalysisRunFileTree fileTreeNode,
			ContentTreeDirectoryVertex parentDirectory)
			throws AnalysisStoreException {
		ResultIterable<ContentTreeFileVertex> find = xoManager.find(
				ContentTreeFileVertex.class, fileTreeNode.getHashId()
						.toString());
		Iterator<ContentTreeFileVertex> iterator = find.iterator();
		if (iterator.hasNext()) {
			ContentTreeFileVertex exstingFile = iterator.next();
			if (iterator.hasNext()) {
				throw new AnalysisStoreException(
						"Content tree vertex for '"
								+ fileTreeNode.getHashId()
								+ "' was found multiple times. Database is inconsistent.");
			}
			parentDirectory.getFiles().add(exstingFile);
			return exstingFile;
		} else {
			ContentTreeFileVertex file = xoManager
					.create(ContentTreeFileVertex.class);
			file.setHashId(fileTreeNode.getHashId().toString());
			int fileSize = analysisStoreDAO.getFileSize(fileTreeNode
					.getHashId());
			file.setSize(fileSize);

			parentDirectory.getFiles().add(file);
			return file;
		}
	}

	/**
	 * This method adds a new content tree node or content tree part to the
	 * database.
	 * 
	 * @param progressObserver
	 *            is an optional observer to be informed about progress (may be
	 *            null).
	 * @param graph
	 * @param fileTreeNode
	 * @param parentDirectory
	 * @param edgeLabel
	 * @return
	 * @throws AnalysisStoreException
	 */
	private ContentTreeDirectoryVertex addContentTreeDirectoryVertex(
			XOManager xoManager, AnalysisRunFileTree fileTreeNode,
			ContentTreeDirectoryVertex parentDirectory)
			throws AnalysisStoreException {
		ResultIterable<ContentTreeDirectoryVertex> find = xoManager.find(
				ContentTreeDirectoryVertex.class, fileTreeNode.getHashId()
						.toString());
		Iterator<ContentTreeDirectoryVertex> iterator = find.iterator();
		if (iterator.hasNext()) {
			ContentTreeDirectoryVertex exstingDirectory = iterator.next();
			if (iterator.hasNext()) {
				throw new AnalysisStoreException(
						"Content tree vertex for '"
								+ fileTreeNode.getHashId()
								+ "' was found multiple times. Database is inconsistent.");
			}
			parentDirectory.getDirectories().add(exstingDirectory);
			return exstingDirectory;
		} else {
			ContentTreeDirectoryVertex directory = xoManager
					.create(ContentTreeDirectoryVertex.class);
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
					ContentTreeFileVertex childFile = addContentTreeFileVertex(
							xoManager, child, directory);
					size += childFile.getSize();
					sizeRecursive += childFile.getSize();
					files++;
					filesRecursive++;
				} else {
					ContentTreeDirectoryVertex childDirectory = addContentTreeDirectoryVertex(
							xoManager, child, directory);
					sizeRecursive += childDirectory.getSizeRecursive();
					directories++;
					directoriesRecursive++;
					directoriesRecursive += childDirectory
							.getDirectoryNumRecursive();
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

	public void checkAndRemoveAnalysisRunContent(XOManager xoManager,
			ContentTreeRootVertex contentRoot) throws AnalysisStoreException {
		checkAndRemoveContentNode(xoManager, contentRoot);
	}

	private void checkAndRemoveContentNode(XOManager xoManager,
			ContentTreeDirectoryVertex contentVertex)
			throws AnalysisStoreException {
		if (contentVertex.getDirectoriesFromRuns().size() > 0) {
			// We have incoming edges, so this is a shared content. We are not
			// allowed to remove it.
			return;
		}

		for (ContentTreeDirectoryVertex directory : contentVertex
				.getDirectories()) {
			checkAndRemoveContentNode(xoManager, directory);
		}
		for (ContentTreeFileVertex file : contentVertex.getFiles()) {
			checkAndRemoveContentNode(xoManager, file);
		}

		HashId hashId = HashId.valueOf(contentVertex.getHashId());
		xoManager.delete(contentVertex);

		analysisStoreCassandraUtils.removeAnalysisFile(hashId);

		// FIXME: The evaluation values need to be removed!
		// boolean isFile = (Boolean) contentVertex
		// .getProperty(TitanElementNames.TREE_ELEMENT_IS_FILE);
		// if (isFile) {
		// EvaluatorStoreCassandraUtils.deleteFileEvaluation(hashId);
		// } else {
		// EvaluatorStoreCassandraUtils.deleteDirectoryEvaluation(hashId);
		// }
	}

	private void checkAndRemoveContentNode(XOManager xoManager,
			ContentTreeFileVertex contentVertex) throws AnalysisStoreException {
		if (contentVertex.getFilesFromRuns().size() > 0) {
			// We have incoming edges, so this is a shared content. We are not
			// allowed to remove it.
			return;
		}

		HashId hashId = HashId.valueOf(contentVertex.getHashId());
		xoManager.delete(contentVertex);

		analysisStoreCassandraUtils.removeAnalysisFile(hashId);

		// FIXME: The evaluation values need to be removed!
		// boolean isFile = (Boolean) contentVertex
		// .getProperty(TitanElementNames.TREE_ELEMENT_IS_FILE);
		// if (isFile) {
		// EvaluatorStoreCassandraUtils.deleteFileEvaluation(hashId);
		// } else {
		// EvaluatorStoreCassandraUtils.deleteDirectoryEvaluation(hashId);
		// }
	}
}
