package com.puresol.utils;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.puresol.trees.FileTree;
import com.puresol.trees.TreePrinter;

public class FileUtilitiesTest {

	@Test
	public void testClassToRelativePackagePath() {
		File relativePath = FileUtilities
				.classToRelativePackagePath(FileUtilitiesTest.class);
		assertTrue(new File("src/test/java", relativePath.getPath()).exists());
	}

	@Test
	public void testGetRelativePath() {
		assertEquals(
				"../destination/to.txt",
				FileUtilities.getRelativePath(new File("source/from.txt"),
						new File("destination/to.txt")).getPath());
		// System.out.println(FileUtilities.getRelativePath(
		// new File("/dir1/dir2/source/from.txt"),
		// new File("/dir1/dir2/destination/to.txt")).getPath());
		assertEquals(
				"../destination/to.txt",
				FileUtilities.getRelativePath(
						new File("/dir1/dir2/source/from.txt"),
						new File("/dir1/dir2/destination/to.txt")).getPath());
		assertEquals(
				"../../destination/to.txt",
				FileUtilities.getRelativePath(
						new File("/dir1/dir2/test/source/from.txt"),
						new File("/dir1/dir2/destination/to.txt")).getPath());
		assertEquals(
				"destination/to.txt",
				FileUtilities.getRelativePath(new File("from.txt"),
						new File("destination/to.txt")).getPath());
	}

	@Test
	public void testNormalizePath() {
		assertEquals(
				"destination/to.txt",
				FileUtilities.normalizePath(
						new File("redundant/../destination/to.txt")).getPath());
		assertEquals(
				"/destination/to.txt",
				FileUtilities.normalizePath(
						new File("/redundant/../destination/to.txt")).getPath());
		assertEquals(
				"/root/destination/to.txt",
				FileUtilities.normalizePath(
						new File("/root/redundant/../destination/to.txt"))
						.getPath());
		assertEquals(
				"/destination/to.txt",
				FileUtilities.normalizePath(
						new File("/root/redundant/../../destination/to.txt"))
						.getPath());
		assertEquals(
				"/destination/to.txt",
				FileUtilities.normalizePath(
						new File("/root//redundant/../../destination//to.txt"))
						.getPath());
	}

	@Test
	public void testConvertFileListToTree() {
		List<File> files = new ArrayList<File>();
		files.add(new File("/test1/test11/File1.txt"));
		files.add(new File("/test1/test12/File2.txt"));
		files.add(new File("/test1/test13/File3.txt"));
		files.add(new File("/test2/test21/File4.txt"));
		files.add(new File("/test3/test31/File5.txt"));
		assertEquals(5, files.size());
		FileTree tree = FileUtilities.convertFileListToTree(files);

		TreePrinter printer = new TreePrinter(System.out);
		printer.println(tree);

		assertTrue(tree.hasChildren());
		assertEquals(3, tree.getChildren().size());
		assertEquals("File2.txt", tree.getChild("test1").getChild("test12")
				.getChildren().get(0).getName());
	}
}
