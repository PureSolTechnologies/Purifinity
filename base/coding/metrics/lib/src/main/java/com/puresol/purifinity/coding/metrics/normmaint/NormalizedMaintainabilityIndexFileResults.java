package com.puresol.purifinity.coding.metrics.normmaint;

import static com.puresol.purifinity.coding.metrics.normmaint.NormalizedMaintainabilityIndexEvaluatorParameter.ALL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.puresol.purifinity.coding.analysis.api.CodeRangeType;
import com.puresol.purifinity.coding.evaluation.api.CodeRangeNameParameter;
import com.puresol.purifinity.coding.evaluation.api.CodeRangeTypeParameter;
import com.puresol.purifinity.coding.evaluation.api.MetricFileResults;
import com.puresol.purifinity.coding.evaluation.api.SourceCodeLocationParameter;
import com.puresol.purifinity.coding.evaluation.api.SourceCodeQuality;
import com.puresol.purifinity.coding.evaluation.api.SourceCodeQualityParameter;
import com.puresol.purifinity.uhura.source.CodeLocation;
import com.puresol.purifinity.utils.math.GeneralValue;
import com.puresol.purifinity.utils.math.LevelOfMeasurement;
import com.puresol.purifinity.utils.math.Parameter;
import com.puresol.purifinity.utils.math.ParameterWithArbitraryUnit;
import com.puresol.purifinity.utils.math.Value;

public class NormalizedMaintainabilityIndexFileResults implements MetricFileResults {

	private static final long serialVersionUID = 7667134885288322378L;

	private final List<NormalizedMaintainabilityIndexFileResult> results = new ArrayList<NormalizedMaintainabilityIndexFileResult>();

	private final ParameterWithArbitraryUnit<CodeLocation> sourceCodeLocationParameter = SourceCodeLocationParameter
			.getInstance();
	private final ParameterWithArbitraryUnit<CodeRangeType> codeRangeTypeParameter = CodeRangeTypeParameter
			.getInstance();
	private final ParameterWithArbitraryUnit<String> codeRangeNameParameter = CodeRangeNameParameter
			.getInstance();
	private final ParameterWithArbitraryUnit<Double> nMiwocParameter = new ParameterWithArbitraryUnit<Double>(
			"nMIwoc", "", LevelOfMeasurement.ORDINAL,
			"Normalized maintainability index without comments", Double.class);
	private final ParameterWithArbitraryUnit<Double> nMicwParameter = new ParameterWithArbitraryUnit<Double>(
			"nMIcw", "", LevelOfMeasurement.ORDINAL,
			"Normalized maintainability index comment weight", Double.class);
	private final ParameterWithArbitraryUnit<Double> nMiParameter = new ParameterWithArbitraryUnit<Double>(
			"nMI", "", LevelOfMeasurement.ORDINAL,
			"Normalized maintainability index including comments", Double.class);
	private final ParameterWithArbitraryUnit<SourceCodeQuality> qualityParameter = SourceCodeQualityParameter
			.getInstance();

	public void add(NormalizedMaintainabilityIndexFileResult result) {
		results.add(result);
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return ALL;
	}

	@Override
	public List<Map<String, Value<?>>> getValues() {
		List<Map<String, Value<?>>> values = new ArrayList<Map<String, Value<?>>>();

		for (NormalizedMaintainabilityIndexFileResult result : results) {
			NormalizedMaintainabilityIndexResult mi = result
					.getNormalizedMaintainabilityIndexResult();
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
			row.put(nMiwocParameter.getName(),
					new GeneralValue<Double>(mi.getNMIwoc(), nMiwocParameter));
			row.put(nMicwParameter.getName(),
					new GeneralValue<Double>(mi.getNMIcw(), nMicwParameter));
			row.put(nMiParameter.getName(),
					new GeneralValue<Double>(mi.getNMI(), nMiParameter));
			row.put(qualityParameter.getName(),
					new GeneralValue<SourceCodeQuality>(result.getQuality(),
							qualityParameter));
			values.add(row);
		}

		return values;
	}
}
