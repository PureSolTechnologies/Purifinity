package com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.puresoltechnologies.commons.math.statistics.Statistics;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc.SLOCMetric;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc.SLOCQuality;

public class SLOCQualityTest {

	@Test
	public void testCompleteness() {
		List<Double> list = new ArrayList<Double>();
		list.add(0.0);
		list.add(10.0);
		list.add(20.0);
		SLOCMetric result = new SLOCMetric(10, 5, 5, 5, new Statistics(list));
		for (CodeRangeType type : CodeRangeType.class.getEnumConstants()) {
			if (SLOCQuality.get(type, result) == SourceCodeQuality.UNSPECIFIED) {
				fail("No source code quality check for code range type '"
						+ type.name() + "' defined!");
			}
		}
	}
}
