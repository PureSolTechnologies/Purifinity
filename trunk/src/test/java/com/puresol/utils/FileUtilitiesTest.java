package com.puresol.utils;

import java.io.File;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

public class FileUtilitiesTest extends TestCase {

	@Test
	public void testClassToRelativePackagePath() {
		File relativePath = FileUtilities
				.classToRelativePackagePath(FileUtilitiesTest.class);
		Assert.assertTrue(new File("src/test/java", relativePath.getPath())
				.exists());
	}

	@Test
	public void testGetRelativePath() {
		Assert.assertEquals(
				"../destination/to.txt",
				FileUtilities.getRelativePath(new File("source/from.txt"),
						new File("destination/to.txt")).getPath());
		System.out.println(FileUtilities.getRelativePath(
				new File("/dir1/dir2/source/from.txt"),
				new File("/dir1/dir2/destination/to.txt")).getPath());
		Assert.assertEquals(
				"../destination/to.txt",
				FileUtilities.getRelativePath(
						new File("/dir1/dir2/source/from.txt"),
						new File("/dir1/dir2/destination/to.txt")).getPath());
		Assert.assertEquals(
				"../../destination/to.txt",
				FileUtilities.getRelativePath(
						new File("/dir1/dir2/test/source/from.txt"),
						new File("/dir1/dir2/destination/to.txt")).getPath());
		Assert.assertEquals(
				"destination/to.txt",
				FileUtilities.getRelativePath(new File("from.txt"),
						new File("destination/to.txt")).getPath());
	}

	@Test
	public void testNormalizePath() {
		Assert.assertEquals(
				"destination/to.txt",
				FileUtilities.normalizePath(
						new File("redundant/../destination/to.txt")).getPath());
		Assert.assertEquals(
				"/destination/to.txt",
				FileUtilities.normalizePath(
						new File("/redundant/../destination/to.txt")).getPath());
		Assert.assertEquals(
				"/root/destination/to.txt",
				FileUtilities.normalizePath(
						new File("/root/redundant/../destination/to.txt"))
						.getPath());
		Assert.assertEquals(
				"/destination/to.txt",
				FileUtilities.normalizePath(
						new File("/root/redundant/../../destination/to.txt"))
						.getPath());
		Assert.assertEquals(
				"/destination/to.txt",
				FileUtilities.normalizePath(
						new File("/root//redundant/../../destination//to.txt"))
						.getPath());
	}
}
