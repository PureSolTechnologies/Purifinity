package com.puresol.coding.analysis;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.puresol.coding.analysis.api.DirectoryStore;
import com.puresol.coding.analysis.api.DirectoryStoreException;
import com.puresol.coding.analysis.api.DirectoryStoreFactory;
import com.puresol.coding.analysis.api.FileStore;
import com.puresol.coding.analysis.api.FileStoreException;
import com.puresol.coding.analysis.api.FileStoreFactory;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.data.HashCodeGenerator;
import com.puresol.trees.FileTree;
import com.puresol.trees.TreeVisitor;
import com.puresol.trees.TreeWalker;
import com.puresol.trees.WalkingAction;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.HashAlgorithm;
import com.puresol.utils.HashId;

/**
 * This class provides some utilities for the analysis store.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class StoreUtilities {

    /**
     * This method takes a file tree from a recursive file system search and
     * creates a hash id file tree.
     * 
     * @param fileTree
     * @return
     */
    public static HashIdFileTree createHashIdFileTree(FileTree fileTree) {
	Map<FileTree, HashId> hashIds = createHashIds(fileTree);
	HashIdFileTree hashIdFileTree = new HashIdFileTree(null,
		fileTree.getName(), hashIds.get(fileTree), fileTree
			.getPathFile(true).isFile());
	return createHashIdFileTree(hashIdFileTree, fileTree, hashIds);
    }

    private static Map<FileTree, HashId> createHashIds(FileTree fileTree) {
	final Map<FileTree, HashId> hashIds = new HashMap<FileTree, HashId>();
	TreeVisitor<FileTree> visitor = new TreeVisitor<FileTree>() {
	    @Override
	    public WalkingAction visit(FileTree tree) {
		try {
		    File pathFile = tree.getPathFile(true);
		    if (pathFile.isFile()) {
			HashId hashId = FileUtilities.createHashId(pathFile,
				HashAlgorithm.SHA256);
			hashIds.put(tree, hashId);
		    } else {
			List<FileTree> children = tree.getChildren();
			// sorting is needed to get reproducible hash codes for
			// directories!
			Collections.sort(children);
			StringBuilder hashes = new StringBuilder();
			for (FileTree child : children) {
			    HashId hashId = hashIds.get(child);
			    hashes.append(hashId);
			    hashes.append("\n");
			}
			String hash = HashCodeGenerator.get(
				HashAlgorithm.SHA256, hashes.toString());
			hashIds.put(tree,
				new HashId(HashAlgorithm.SHA256, hash));
		    }
		    return WalkingAction.PROCEED;
		} catch (IOException e) {
		    return WalkingAction.ABORT;
		}
	    }
	};
	TreeWalker<FileTree> treeWalker = new TreeWalker<FileTree>(fileTree);
	treeWalker.walkBackward(visitor);
	return hashIds;
    }

    private static HashIdFileTree createHashIdFileTree(
	    HashIdFileTree hashIdFileTree, FileTree fileTree,
	    Map<FileTree, HashId> hashIds) {
	List<FileTree> children = fileTree.getChildren();
	// sorting is needed to get reproducible hash codes for
	// directories!
	Collections.sort(children);
	for (FileTree child : children) {
	    HashIdFileTree childHashIdFileTree = new HashIdFileTree(
		    hashIdFileTree, child.getName(), hashIds.get(child), child
			    .getPathFile(true).isFile());
	    createHashIdFileTree(childHashIdFileTree, child, hashIds);
	}
	return hashIdFileTree;
    }

    public static void storeFiles(HashIdFileTree hashIdFileTree) {
	final FileStore fileStore = FileStoreFactory.getInstance();
	final DirectoryStore directoryStore = DirectoryStoreFactory
		.getInstance();
	TreeVisitor<HashIdFileTree> visitor = new TreeVisitor<HashIdFileTree>() {
	    @Override
	    public WalkingAction visit(HashIdFileTree tree) {
		try {
		    if (tree.isFile()) {
			FileInputStream content = new FileInputStream(
				tree.getPathFile(true));
			try {
			    fileStore.storeFile(tree.getHashId(), content);
			} finally {
			    content.close();
			}
		    } else {
			directoryStore.createDirectory(tree.getHashId());
		    }
		    return WalkingAction.PROCEED;
		} catch (IOException e) {
		    return WalkingAction.ABORT;
		} catch (FileStoreException e) {
		    return WalkingAction.ABORT;
		} catch (DirectoryStoreException e) {
		    return WalkingAction.ABORT;
		}
	    }
	};
	TreeWalker<HashIdFileTree> walker = new TreeWalker<HashIdFileTree>(
		hashIdFileTree);
	walker.walk(visitor);
    }
}
