package com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc;

import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc.SLOCEvaluatorParameter.ALL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.purifinity.evaluation.api.MetricFileResults;
import com.puresoltechnologies.purifinity.evaluation.api.QualityLevel;

public class SLOCFileResults extends AbstractSLOCResults implements
		MetricFileResults {

	private static final long serialVersionUID = -3995835459935284595L;

	private final List<SLOCResult> results = new ArrayList<SLOCResult>();

	public void add(SLOCResult result) {
		results.add(result);
		QualityLevel qualityLevel = getQualityLevel();
		if (qualityLevel == null) {
			setQualityLevel(new QualityLevel(result.getQuality()));
		} else {
			qualityLevel.add(result.getQuality());
		}
	}

	public List<SLOCResult> getResults() {
		return results;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return ALL;
	}

	@Override
	public List<Map<String, Value<?>>> getValues() {
		List<Map<String, Value<?>>> values = new ArrayList<Map<String, Value<?>>>();

		for (SLOCResult result : results) {
			Map<String, Value<?>> row = convertToRow(result);
			values.add(row);
		}

		return values;
	}
}
