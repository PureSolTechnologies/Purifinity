package com.puresol.utils;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.puresol.trees.FileTree;
import com.puresol.trees.TreeVisitor;
import com.puresol.trees.TreeWalker;
import com.puresol.trees.WalkingAction;

/**
 * This class checks the FileSearch class.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FileSearchTest {

    /**
     * This is a helper class for FileSearch test to check a tree for an
     * occurrence of a special file.
     * 
     * @author Rick-Rainer Ludwig
     * 
     */
    private static class FindTreeVisitor implements TreeVisitor<FileTree> {

	private boolean found = false;
	private final File file;

	public FindTreeVisitor(File file) {
	    super();
	    this.file = file;
	}

	@Override
	public WalkingAction visit(FileTree tree) {
	    File treeFile = tree.getPathFile();
	    if (treeFile.isFile()) {
		File expected = new File(treeFile.getPath().substring(1));
		if (expected.equals(file)) {
		    found = true;
		    return WalkingAction.ABORT;
		}
	    }
	    return WalkingAction.PROCEED;
	}

	boolean wasFound() {
	    return found;
	}

    }

    @Test
    public void test() {
	FileTree tree = FileSearch.getFileTree(new File("."),
		new ArrayList<String>(), new ArrayList<String>(),
		new ArrayList<String>(), new ArrayList<String>());

	final List<File> list = FileSearch.find(new File("."), "*");

	TreeWalker<FileTree> walker = new TreeWalker<FileTree>(tree);
	walker.walk(new TreeVisitor<FileTree>() {

	    @Override
	    public WalkingAction visit(FileTree tree) {
		File treeFile = tree.getPathFile();
		if (treeFile.isFile()) {
		    File expected = new File(treeFile.getPath().substring(1));
		    assertTrue(treeFile.getPath()
			    + " was in tree, but not found in list!",
			    list.contains(expected));
		}
		return WalkingAction.PROCEED;
	    }
	});

	for (final File file : list) {
	    FindTreeVisitor visitor = new FindTreeVisitor(file);
	    walker.walk(visitor);
	    if ((!file.isHidden()) && (!file.getPath().contains("/."))) {
		assertTrue(file.getPath()
			+ " was in list, but not found in tree.",
			visitor.wasFound());
	    }
	}
    }
}
