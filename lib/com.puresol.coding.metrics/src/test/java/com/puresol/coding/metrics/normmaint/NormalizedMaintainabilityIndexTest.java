package com.puresol.coding.metrics.normmaint;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.lang.test.TestLanguage;
import com.puresol.uhura.parser.ParserTree;

public class NormalizedMaintainabilityIndexTest {

    @Test
    public void testInstance() {
	assertNotNull(new NormalizedMaintainabilityIndex(
		TestLanguage.getInstance(), new CodeRange("FILE",
			CodeRangeType.FILE, new ParserTree("FILE"))));
    }

}
