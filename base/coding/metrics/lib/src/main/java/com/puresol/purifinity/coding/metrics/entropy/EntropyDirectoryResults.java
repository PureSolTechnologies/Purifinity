package com.puresol.purifinity.coding.metrics.entropy;

import static com.puresol.purifinity.coding.metrics.entropy.EntropyMetricEvaluatorParameter.ALL;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.puresol.commons.utils.math.GeneralValue;
import com.puresol.commons.utils.math.Parameter;
import com.puresol.commons.utils.math.Value;
import com.puresol.purifinity.coding.evaluation.api.AbstractEvaluatorResult;
import com.puresol.purifinity.coding.evaluation.api.MetricDirectoryResults;
import com.puresol.purifinity.coding.evaluation.api.QualityLevel;
import com.puresol.purifinity.coding.evaluation.api.QualityLevelParameter;

public class EntropyDirectoryResults extends AbstractEvaluatorResult implements
		MetricDirectoryResults {

	private static final long serialVersionUID = 4585034044953318000L;

	@Override
	public Set<Parameter<?>> getParameters() {
		return ALL;
	}

	@Override
	public Map<String, Value<?>> getValues() {
		Map<String, Value<?>> values = new HashMap<>();
		values.put(QualityLevelParameter.NAME, new GeneralValue<QualityLevel>(
				getQualityLevel(), QualityLevelParameter.getInstance()));
		return values;
	}

}
