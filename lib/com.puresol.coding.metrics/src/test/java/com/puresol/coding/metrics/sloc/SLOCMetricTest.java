package com.puresol.coding.metrics.sloc;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.lang.test.TestLanguage;
import com.puresol.uhura.parser.ParserTree;

public class SLOCMetricTest {

    @Test
    public void testInstance() {
	assertNotNull(new SLOCMetric(TestLanguage.getInstance(), new CodeRange(
		"FILE", CodeRangeType.FILE, new ParserTree("FILE"))));
    }

}
