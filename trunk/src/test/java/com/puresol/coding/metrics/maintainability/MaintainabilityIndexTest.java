package com.puresol.coding.metrics.maintainability;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.lang.test.TestLanguage;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.PersistenceTester;

public class MaintainabilityIndexTest {

	@Test
	public void testInstance() {
		assertNotNull(new MaintainabilityIndex(TestLanguage.getInstance(),
				new CodeRange("FILE", CodeRangeType.FILE,
						new ParserTree("FILE"))));
	}

	@Test
	public void testPersistence() {
		File file = new File("test", FileUtilities.classToRelativePackagePath(
				MaintainabilityIndex.class).toString()
				+ ".persist");

		MaintainabilityIndex metric = new MaintainabilityIndex(
				TestLanguage.getInstance(), new CodeRange("FILE",
						CodeRangeType.FILE, new ParserTree("FILE")));
		PersistenceTester.test(metric, file);
	}

}
