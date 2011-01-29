package com.puresol.coding.metrics.sloc;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.lang.test.TestLanguage;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.PersistenceTester;

public class SLOCMetricTest {

	@Test
	public void testInstance() {
		assertNotNull(new SLOCMetric(TestLanguage.getInstance(), new CodeRange(
				"FILE", CodeRangeType.FILE, new ParserTree("FILE"))));
	}

	@Test
	public void testPersistence() {
		File file = new File("test", FileUtilities.classToRelativePackagePath(
				SLOCMetric.class).toString()
				+ ".persist");

		SLOCMetric metric = new SLOCMetric(TestLanguage.getInstance(),
				new CodeRange("FILE", CodeRangeType.FILE,
						new ParserTree("FILE")));
		PersistenceTester.test(metric, file);
	}

}
