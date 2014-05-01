package com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.sloc;

import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.sloc.SLOCEvaluatorParameter.ALL;

import java.util.Map;
import java.util.Set;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricDirectoryResults;

public class SLOCDirectoryResults extends AbstractSLOCResults implements
		MetricDirectoryResults {

	private static final long serialVersionUID = -3995835459935284595L;

	private final SLOCResult result;

	public SLOCDirectoryResults(SLOCResult result) {
		super();
		this.result = result;
	}

	public SLOCResult getResult() {
		return result;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return ALL;
	}

	@Override
	public Map<String, Value<?>> getValues() {
		return convertToRow(result);
	}

}
