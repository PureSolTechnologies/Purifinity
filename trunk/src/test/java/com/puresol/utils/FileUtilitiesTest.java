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
		FileTree tree = FileUtilities.convertFileListToTree("/", files);

		TreePrinter printer = new TreePrinter(System.out);
		printer.println(tree);

		assertTrue(tree.hasChildren());
		assertEquals(3, tree.getChildren().size());
		assertEquals("File2.txt", tree.getChild("test1").getChild("test12")
				.getChildren().get(0).getName());
	}

	@Test
	public void testGetRelativePathsUnix() throws Exception {
		assertEquals("stuff/xyz.dat", FileUtilities.getRelativePath(
				"/var/data/stuff/xyz.dat", "/var/data/", "/"));
		assertEquals("../../b/c",
				FileUtilities.getRelativePath("/a/b/c", "/a/x/y/", "/"));
		assertEquals("../../b/c", FileUtilities.getRelativePath("/m/n/o/a/b/c",
				"/m/n/o/a/x/y/", "/"));
	}

	@Test
	public void testGetRelativePathFileToFile() throws Exception {
		String target = "C:\\Windows\\Boot\\Fonts\\chs_boot.ttf";
		String base = "C:\\Windows\\Speech\\Common\\sapisvr.exe";

		String relPath = FileUtilities.getRelativePath(target, base, "\\");
		assertEquals("..\\..\\Boot\\Fonts\\chs_boot.ttf", relPath);
	}

	@Test
	public void testGetRelativePathDirectoryToFile() throws Exception {
		String target = "C:\\Windows\\Boot\\Fonts\\chs_boot.ttf";
		String base = "C:\\Windows\\Speech\\Common\\";

		String relPath = FileUtilities.getRelativePath(target, base, "\\");
		assertEquals("..\\..\\Boot\\Fonts\\chs_boot.ttf", relPath);
	}

	@Test
	public void testGetRelativePathFileToDirectory() throws Exception {
		String target = "C:\\Windows\\Boot\\Fonts";
		String base = "C:\\Windows\\Speech\\Common\\foo.txt";

		String relPath = FileUtilities.getRelativePath(target, base, "\\");
		assertEquals("..\\..\\Boot\\Fonts", relPath);
	}

	@Test
	public void testGetRelativePathDirectoryToDirectory() throws Exception {
		String target = "C:\\Windows\\Boot\\";
		String base = "C:\\Windows\\Speech\\Common\\";
		String expected = "..\\..\\Boot\\";

		String relPath = FileUtilities.getRelativePath(target, base, "\\");
		assertEquals(expected, relPath);
	}

	@Test(expected = PathResolutionException.class)
	public void testGetRelativePathDifferentDriveLetters() throws Exception {
		String target = "D:\\sources\\recovery\\RecEnv.exe";
		String base = "C:\\Java\\workspace\\AcceptanceTests\\Standard test data\\geo\\";
		FileUtilities.getRelativePath(target, base, "\\");
	}

}
