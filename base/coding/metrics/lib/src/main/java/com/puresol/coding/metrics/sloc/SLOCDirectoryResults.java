package com.puresol.coding.metrics.sloc;

import static com.puresol.coding.metrics.sloc.SLOCEvaluatorParameter.ALL;

import java.util.Map;
import java.util.Set;

import com.puresol.coding.evaluation.api.MetricDirectoryResults;
import com.puresol.utils.math.Parameter;
import com.puresol.utils.math.Value;

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
