package com.puresol.utils;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.puresol.trees.FileTree;

/**
 * This class checks the FileSearch class.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FileSearchTest {

    @Test
    public void testFileSearch() {
	FileTree fileTree = FileSearch.getFileTree(new File("."),
		new FileSearchConfiguration());

	final List<File> fileList = FileSearch.find(new File("."), "*");

	for (FileTree file : fileTree) {
	    File treeFile = file.getPathFile();
	    if (treeFile.isFile()) {
		File expected = new File(treeFile.getPath().substring(1));
		assertTrue(treeFile.getPath()
			+ " was in tree, but not found in list!",
			fileList.contains(expected));
	    }
	}

	for (File file : fileList) {
	    boolean found = false;
	    for (FileTree file2 : fileTree) {
		File treeFile = file2.getPathFile();
		if (treeFile.isFile()) {
		    File expected = new File(treeFile.getPath().substring(1));
		    if (expected.equals(file)) {
			found = true;
			break;
		    }
		}
	    }
	    if ((!file.isHidden()) && (!file.getPath().contains("/."))) {
		assertTrue(file.getPath()
			+ " was in list, but not found in tree.", found);
	    }
	}
    }
}
