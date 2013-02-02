package com.puresol.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

public class PathUtilsTest {

    @Test
    public void testClassToRelativePackagePath() {
	File relativePath = PathUtils
		.classToRelativePackagePath(PathUtilsTest.class);
	assertTrue(new File("src/test/java", relativePath.getPath()).exists());
    }

    @Test
    public void testNormalizePath() {
	assertEquals(
		"destination/to.txt",
		PathUtils.normalizePath(
			new File("redundant/../destination/to.txt")).getPath());
	assertEquals(
		"/destination/to.txt",
		PathUtils.normalizePath(
			new File("/redundant/../destination/to.txt")).getPath());
	assertEquals(
		"/root/destination/to.txt",
		PathUtils.normalizePath(
			new File("/root/redundant/../destination/to.txt"))
			.getPath());
	assertEquals(
		"/destination/to.txt",
		PathUtils.normalizePath(
			new File("/root/redundant/../../destination/to.txt"))
			.getPath());
	assertEquals(
		"/destination/to.txt",
		PathUtils.normalizePath(
			new File("/root//redundant/../../destination//to.txt"))
			.getPath());
	assertEquals("/a/b/c/d/e",
		PathUtils.normalizePath(new File("/a/b/././c/d/e/.")).getPath());
    }

    @Test
    public void testGetRelativePathsUnix() throws Exception {
	assertEquals("stuff/xyz.dat", PathUtils.getRelativePath("/var/data/",
		"/var/data/stuff/xyz.dat", "/"));
	assertEquals("../../b/c",
		PathUtils.getRelativePath("/a/x/y/", "/a/b/c", "/"));
	assertEquals("../../b/c",
		PathUtils.getRelativePath("/m/n/o/a/x/y/", "/m/n/o/a/b/c", "/"));
    }

    @Test
    public void testGetRelativePathFileToFile() throws Exception {
	String to = "C:\\Windows\\Boot\\Fonts\\chs_boot.ttf";
	String from = "C:\\Windows\\Speech\\Common\\sapisvr.exe";

	String relPath = PathUtils.getRelativePath(from, to, "\\");
	assertEquals("..\\..\\Boot\\Fonts\\chs_boot.ttf", relPath);
    }

    @Test
    public void testGetRelativePathDirectoryToFile() throws Exception {
	String to = "C:\\Windows\\Boot\\Fonts\\chs_boot.ttf";
	String from = "C:\\Windows\\Speech\\Common\\";

	String relPath = PathUtils.getRelativePath(from, to, "\\");
	assertEquals("..\\..\\Boot\\Fonts\\chs_boot.ttf", relPath);
    }

    @Test
    public void testGetRelativePathFileToDirectory() throws Exception {
	String to = "C:\\Windows\\Boot\\Fonts";
	String from = "C:\\Windows\\Speech\\Common\\foo.txt";

	String relPath = PathUtils.getRelativePath(from, to, "\\");
	assertEquals("..\\..\\Boot\\Fonts", relPath);
    }

    @Test
    public void testGetRelativePathDirectoryToDirectory() throws Exception {
	String to = "C:\\Windows\\Boot\\";
	String from = "C:\\Windows\\Speech\\Common\\";
	String expected = "..\\..\\Boot\\";

	String relPath = PathUtils.getRelativePath(from, to, "\\");
	assertEquals(expected, relPath);
    }

    @Test
    public void testGetRelativePathWithEqualsToAndFrom() throws Exception {
	String to = "/a/b/c";
	String from = "/a/b/c";
	String expected = "";

	String relPath = PathUtils.getRelativePath(from, to, "\\");
	assertEquals(expected, relPath);
    }

    @Test(expected = PathResolutionException.class)
    public void testGetRelativePathDifferentDriveLetters() throws Exception {
	String to = "D:\\sources\\recovery\\RecEnv.exe";
	String from = "C:\\Java\\workspace\\AcceptanceTests\\Standard test data\\geo\\";
	PathUtils.getRelativePath(from, to, "\\");
    }

}
