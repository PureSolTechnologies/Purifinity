package com.puresol.coding.metrics.maintainability;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.lang.test.TestLanguage;
import com.puresol.uhura.parser.ParserTree;

public class MaintainabilityIndexTest {

    @Test
    public void testInstance() {
	assertNotNull(new MaintainabilityIndex(TestLanguage.getInstance(),
		new CodeRange("FILE", CodeRangeType.FILE,
			new ParserTree("FILE"))));
    }

}
