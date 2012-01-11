package com.puresol.coding.metrics.sloc;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.puresol.coding.CodeRangeType;
import com.puresol.coding.quality.SourceCodeQuality;
import com.puresol.math.statistics.Statistics;

public class SLOCQualityTest {

	@Test
	public void testCompleteness() {
		List<Double> list = new ArrayList<Double>();
		list.add(0.0);
		list.add(10.0);
		list.add(20.0);
		SLOCResult result = new SLOCResult(10, 5, 5, 5, new Statistics(list));
		for (CodeRangeType type : CodeRangeType.class.getEnumConstants()) {
			if (SLOCQuality.get(type, result) == SourceCodeQuality.UNSPECIFIED) {
				fail("No source code quality check for code range type '"
						+ type.name() + "' defined!");
			}
		}
	}
}
