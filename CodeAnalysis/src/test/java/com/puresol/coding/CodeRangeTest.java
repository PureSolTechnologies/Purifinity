package com.puresol.coding;

import java.io.File;

import org.junit.Test;

import com.puresol.utils.FileUtilities;
import com.puresol.utils.PersistenceTester;

public class CodeRangeTest {

	@Test
	public void testPersistence() {
		File file = new File("test", FileUtilities.classToRelativePackagePath(
				CodeRange.class).toString()
				+ ".persist");
		PersistenceTester.test(new CodeRange("String", CodeRangeType.CLASS,
				null), file);
	}

}
