package com.puresol.purifinity.coding.metrics.codedepth;

import static com.puresol.purifinity.coding.metrics.codedepth.CodeDepthMetricEvaluatorParameter.ALL;

import java.util.Map;
import java.util.Set;

import com.puresol.commons.utils.math.Parameter;
import com.puresol.commons.utils.math.Value;
import com.puresol.purifinity.coding.evaluation.api.AbstractEvaluatorResult;
import com.puresol.purifinity.coding.evaluation.api.MetricDirectoryResults;
import com.puresol.purifinity.coding.evaluation.api.QualityLevel;

public class CodeDepthDirectoryResults extends AbstractEvaluatorResult
		implements MetricDirectoryResults {

	private static final long serialVersionUID = 5885874850811986090L;

	@Override
	public Set<Parameter<?>> getParameters() {
		return ALL;
	}

	public void addQualityLevel(QualityLevel level) {
		QualityLevel qualityLevel = getQualityLevel();
		if (qualityLevel == null) {
			setQualityLevel(level);
		} else {
			qualityLevel.add(level);
		}
	}

	@Override
	public Map<String, Value<?>> getValues() {
		// TODO Auto-generated method stub
		return null;
	}

}
