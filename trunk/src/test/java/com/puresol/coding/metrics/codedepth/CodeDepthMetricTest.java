package com.puresol.coding.metrics.codedepth;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.lang.test.TestLanguage;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.PersistenceTester;

public class CodeDepthMetricTest {

	@Test
	public void testInstance() {
		assertNotNull(new CodeDepthMetric(TestLanguage.getInstance(),
				new CodeRange("FILE", CodeRangeType.FILE,
						new ParserTree("FILE"))));
	}

	@Test
	public void testPersistence() {
		File file = new File("test", FileUtilities.classToRelativePackagePath(
				CodeDepthMetric.class).toString()
				+ ".persist");

		CodeDepthMetric metric = new CodeDepthMetric(
				TestLanguage.getInstance(), new CodeRange("FILE",
						CodeRangeType.FILE, new ParserTree("FILE")));
		PersistenceTester.test(metric, file);
	}

}
