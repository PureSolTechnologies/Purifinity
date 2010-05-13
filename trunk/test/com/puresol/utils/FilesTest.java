package com.puresol.utils;

import java.io.File;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

public class FilesTest extends TestCase {

	@Test
	public void testClassToRelativePackagePath() {
		File relativePath = Files.classToRelativePackagePath(FilesTest.class);
		Assert.assertTrue(new File("test/" + relativePath).exists());
	}

	@Test
	public void testAddPaths() {
		File file = Files.addPaths(new File("/testdir"), new File("test.file"));
		Assert.assertNotNull(file);
		Assert.assertEquals("/testdir/test.file", file.getPath());
	}

	@Test
	public void testGetRelativePath() {
		Assert.assertEquals("../destination/to.txt", Files.getRelativePath(
				new File("source/from.txt"), new File("destination/to.txt"))
				.getPath());
		System.out.println(Files.getRelativePath(
				new File("/dir1/dir2/source/from.txt"),
				new File("/dir1/dir2/destination/to.txt")).getPath());
		Assert.assertEquals("../destination/to.txt", Files.getRelativePath(
				new File("/dir1/dir2/source/from.txt"),
				new File("/dir1/dir2/destination/to.txt")).getPath());
		Assert.assertEquals("../../destination/to.txt", Files.getRelativePath(
				new File("/dir1/dir2/test/source/from.txt"),
				new File("/dir1/dir2/destination/to.txt")).getPath());
		Assert
				.assertEquals("destination/to.txt", Files.getRelativePath(
						new File("from.txt"), new File("destination/to.txt"))
						.getPath());
	}

	@Test
	public void testNormalizePath() {
		Assert.assertEquals("destination/to.txt", Files.normalizePath(
				new File("redundant/../destination/to.txt")).getPath());
		Assert.assertEquals("/destination/to.txt", Files.normalizePath(
				new File("/redundant/../destination/to.txt")).getPath());
		Assert.assertEquals("/root/destination/to.txt", Files.normalizePath(
				new File("/root/redundant/../destination/to.txt")).getPath());
		Assert
				.assertEquals("/destination/to.txt", Files.normalizePath(
						new File("/root/redundant/../../destination/to.txt"))
						.getPath());
		Assert.assertEquals("/destination/to.txt", Files.normalizePath(
				new File("/root//redundant/../../destination//to.txt"))
				.getPath());
	}
}
