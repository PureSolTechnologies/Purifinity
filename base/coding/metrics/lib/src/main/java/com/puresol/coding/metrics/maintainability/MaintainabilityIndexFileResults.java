package com.puresol.coding.metrics.maintainability;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.evaluation.api.CodeRangeNameParameter;
import com.puresol.coding.evaluation.api.CodeRangeTypeParameter;
import com.puresol.coding.evaluation.api.MetricResults;
import com.puresol.coding.evaluation.api.SourceCodeLocationParameter;
import com.puresol.coding.evaluation.api.SourceCodeQuality;
import com.puresol.coding.evaluation.api.SourceCodeQualityParameter;
import com.puresol.uhura.source.CodeLocation;
import com.puresol.utils.math.GeneralValue;
import com.puresol.utils.math.LevelOfMeasurement;
import com.puresol.utils.math.Parameter;
import com.puresol.utils.math.ParameterWithArbitraryUnit;
import com.puresol.utils.math.Value;

public class MaintainabilityIndexFileResults implements MetricResults {

	private static final long serialVersionUID = -5901342878584699006L;

	private final List<MaintainabilityIndexFileResult> results = new ArrayList<MaintainabilityIndexFileResult>();

	private final ParameterWithArbitraryUnit<CodeLocation> sourceCodeLocationParameter = SourceCodeLocationParameter
			.getInstance();
	private final ParameterWithArbitraryUnit<CodeRangeType> codeRangeTypeParameter = CodeRangeTypeParameter
			.getInstance();
	private final ParameterWithArbitraryUnit<String> codeRangeNameParameter = CodeRangeNameParameter
			.getInstance();
	private final ParameterWithArbitraryUnit<Double> miwocParameter = new ParameterWithArbitraryUnit<Double>(
			"MIwoc", "", LevelOfMeasurement.ORDINAL,
			"Maintainability index without comments", Double.class);
	private final ParameterWithArbitraryUnit<Double> micwParameter = new ParameterWithArbitraryUnit<Double>(
			"MIcw", "", LevelOfMeasurement.ORDINAL,
			"Maintainability index comment weight", Double.class);
	private final ParameterWithArbitraryUnit<Double> miParameter = new ParameterWithArbitraryUnit<Double>(
			"MI", "", LevelOfMeasurement.ORDINAL,
			"Maintainability index including comments", Double.class);
	private final ParameterWithArbitraryUnit<SourceCodeQuality> qualityParameter = SourceCodeQualityParameter
			.getInstance();

	public void add(MaintainabilityIndexFileResult result) {
		results.add(result);
	}

	public List<MaintainabilityIndexFileResult> getResults() {
		return results;
	}

	@Override
	public List<Parameter<?>> getParameters() {
		List<Parameter<?>> parameters = new ArrayList<Parameter<?>>();
		parameters.add(sourceCodeLocationParameter);
		parameters.add(codeRangeTypeParameter);
		parameters.add(codeRangeNameParameter);
		parameters.add(miwocParameter);
		parameters.add(micwParameter);
		parameters.add(miParameter);
		parameters.add(qualityParameter);
		return parameters;
	}

	@Override
	public List<Map<String, Value<?>>> getValues() {
		List<Map<String, Value<?>>> values = new ArrayList<Map<String, Value<?>>>();

		for (MaintainabilityIndexFileResult result : results) {
			MaintainabilityIndexResult mi = result
					.getMaintainabilityIndexResult();
			Map<String, Value<?>> row = new HashMap<String, Value<?>>();
			row.put(sourceCodeLocationParameter.getName(),
					new GeneralValue<CodeLocation>(result
							.getSourceCodeLocation(),
							sourceCodeLocationParameter));
			row.put(codeRangeTypeParameter.getName(),
					new GeneralValue<CodeRangeType>(result.getCodeRangeType(),
							codeRangeTypeParameter));
			row.put(codeRangeNameParameter.getName(), new GeneralValue<String>(
					result.getCodeRangeName(), codeRangeNameParameter));
			row.put(miwocParameter.getName(),
					new GeneralValue<Double>(mi.getMIwoc(), miwocParameter));
			row.put(micwParameter.getName(),
					new GeneralValue<Double>(mi.getMIcw(), micwParameter));
			row.put(miParameter.getName(), new GeneralValue<Double>(mi.getMI(),
					miParameter));
			row.put(qualityParameter.getName(),
					new GeneralValue<SourceCodeQuality>(result.getQuality(),
							qualityParameter));
			values.add(row);
		}

		return values;
	}
}
