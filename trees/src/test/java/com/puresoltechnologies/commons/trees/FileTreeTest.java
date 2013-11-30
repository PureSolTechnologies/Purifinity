package com.puresoltechnologies.commons.trees;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.puresoltechnologies.commons.trees.FileTree;
import com.puresoltechnologies.commons.trees.FileTreeConverter;

public class FileTreeTest {

    private FileTree tree;
    private FileTree tree2;

    @Before
    public void setup() {
	List<File> files = new ArrayList<File>();
	files.add(new File("/test1/test11/File1.txt"));
	files.add(new File("/test1/test12/File2.txt"));
	files.add(new File("/test1/test13/File3.txt"));
	files.add(new File("/test2/test21/File4.txt"));
	files.add(new File("/test3/test31/File5.txt"));
	assertEquals(5, files.size());
	tree = FileTreeConverter.convertFileListToTree("/", files);
    }

    @Before
    public void setup2() {
	List<File> files = new ArrayList<File>();
	files.add(new File("/test1/test11/File1.txt"));
	files.add(new File("/test1/test12/File2.txt"));
	files.add(new File("/test1/test13/File3.txt"));
	files.add(new File("/test2/test21/File4.txt"));
	files.add(new File("/test3/test31/File5.txt"));
	assertEquals(5, files.size());
	tree2 = FileTreeConverter.convertFileListToTree("/home/test", files);
    }

    @Test
    public void testInstance() {
	assertNotNull(new FileTree(null, "/"));
    }

    @Test
    public void testGetPath() {
	FileTree node = tree.getChild("test1").getChild("test12").getChildren()
		.get(0);
	assertEquals("/test1/test12/File2.txt", node.getPath(true));
    }

    @Test
    public void testGetPathFile() {
	FileTree node = tree2.getChild("test1").getChild("test12")
		.getChildren().get(0);
	assertEquals(new File("/home/test/test1/test12/File2.txt"),
		node.getPathFile(true));
    }
}
