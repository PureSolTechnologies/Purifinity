package com.puresol.trees;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class FileUtilitiesTest {

    @Test
    public void testConvertFileListToTree() {
	List<File> files = new ArrayList<File>();
	files.add(new File("/test1/test11/File1.txt"));
	files.add(new File("/test1/test12/File2.txt"));
	files.add(new File("/test1/test13/File3.txt"));
	files.add(new File("/test2/test21/File4.txt"));
	files.add(new File("/test3/test31/File5.txt"));
	assertEquals(5, files.size());
	FileTree tree = FileTreeConverter.convertFileListToTree("/", files);

	TreePrinter printer = new TreePrinter(System.out);
	printer.println(tree);

	assertTrue(tree.hasChildren());
	assertEquals(3, tree.getChildren().size());
	assertEquals("File2.txt", tree.getChild("test1").getChild("test12")
		.getChildren().get(0).getName());
    }
}
