package com.puresoltechnologies.purifinity.server.common.utils.io;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.Test;

import com.puresoltechnologies.commons.misc.io.FileSearchConfiguration;
import com.puresoltechnologies.purifinity.server.common.utils.io.FileSearch;
import com.puresoltechnologies.purifinity.server.common.utils.io.FileTree;

/**
 * This class checks the FileSearch class.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FileSearchTest {

    @Test
    public void testFileSearch() {
	FileTree fileTree = FileSearch.getFileTree(new File("src"),
		new FileSearchConfiguration(new ArrayList<String>(),
			new ArrayList<String>(), new ArrayList<String>(),
			new ArrayList<String>(), true));

	List<File> fileList = FileSearch.find(new File("src"), "*");

	for (FileTree file : fileTree) {
	    File treeFile = file.getPathFile(true);
	    if (treeFile.isFile()) {
		File expected = new File(treeFile.getPath());
		assertTrue(treeFile.getPath()
			+ " was in tree, but not found in list!",
			fileList.contains(new File(expected.getPath()
				.substring(3))));
	    }
	}

	for (File file : fileList) {
	    boolean found = false;
	    for (FileTree file2 : fileTree) {
		File treeFile = file2.getPathFile(true);
		if (treeFile.isFile()) {
		    File expected = new File(treeFile.getPath().substring(3));
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

    @Test
    public void testPattern() {
	String regExpString = FileSearch.wildcardsToRegExp("*");
	System.out.println(regExpString);
	Pattern pattern = Pattern.compile(regExpString);
	assertTrue(pattern.matcher("abc.xyz").matches());
	assertFalse(pattern.matcher(File.separator + "abc.xyz").matches());
    }

    @Test
    public void testPattern2() {
	String regExpString = FileSearch.wildcardsToRegExp(".*");
	System.out.println(regExpString);
	Pattern pattern = Pattern.compile(regExpString);
	assertTrue(pattern.matcher(".abc.xyz").matches());
	assertFalse(pattern.matcher("abc.xyz").matches());
    }
}