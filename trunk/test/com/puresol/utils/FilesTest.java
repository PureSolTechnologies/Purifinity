package com.puresol.utils;

import java.io.File;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

public class FilesTest extends TestCase {

    @Test
    public void testAddPaths() {
	File file =
		Files
			.addPaths(new File("/testdir"), new File(
				"test.file"));
	Assert.assertNotNull(file);
	Assert.assertEquals("/testdir/test.file", file.getPath());
    }
}
