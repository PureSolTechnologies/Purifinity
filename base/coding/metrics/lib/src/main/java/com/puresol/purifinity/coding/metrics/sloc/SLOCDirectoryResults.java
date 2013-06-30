package com.puresol.purifinity.coding.metrics.sloc;

import static com.puresol.purifinity.coding.metrics.sloc.SLOCEvaluatorParameter.ALL;

import java.util.Map;
import java.util.Set;

import com.puresol.purifinity.coding.evaluation.api.MetricDirectoryResults;
import com.puresol.purifinity.utils.math.Parameter;
import com.puresol.purifinity.utils.math.Value;

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
