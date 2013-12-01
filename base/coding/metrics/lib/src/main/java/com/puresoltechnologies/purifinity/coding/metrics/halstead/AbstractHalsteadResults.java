package com.puresoltechnologies.purifinity.coding.metrics.halstead;

import static com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetricEvaluatorParameter.CODE_RANGE_NAME;
import static com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetricEvaluatorParameter.CODE_RANGE_TYPE;
import static com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetricEvaluatorParameter.DIFFERENT_OPERANDS;
import static com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetricEvaluatorParameter.DIFFERENT_OPERATORS;
import static com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetricEvaluatorParameter.DIFFICULTY;
import static com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetricEvaluatorParameter.ESTIMATED_BUGS;
import static com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetricEvaluatorParameter.HALSTEAD_LENGTH;
import static com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetricEvaluatorParameter.HALSTEAD_VOLUMNE;
import static com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetricEvaluatorParameter.IMPLEMENTATION_EFFORT;
import static com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetricEvaluatorParameter.IMPLEMENTATION_TIME;
import static com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetricEvaluatorParameter.PROGRAM_LENGTH;
import static com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetricEvaluatorParameter.PROGRAM_LEVEL;
import static com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetricEvaluatorParameter.QUALITY;
import static com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetricEvaluatorParameter.QUALITY_LEVEL;
import static com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetricEvaluatorParameter.SOURCE_CODE_LOCATION;
import static com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetricEvaluatorParameter.TOTAL_OPERANDS;
import static com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetricEvaluatorParameter.TOTAL_OPERATORS;
import static com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetricEvaluatorParameter.VOCABULARY_SIZE;

import java.util.HashMap;
import java.util.Map;

import com.puresoltechnologies.commons.math.GeneralValue;
import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.purifinity.coding.analysis.api.CodeRangeType;
import com.puresoltechnologies.purifinity.coding.evaluation.api.AbstractEvaluatorResult;
import com.puresoltechnologies.purifinity.coding.evaluation.api.QualityLevel;
import com.puresoltechnologies.purifinity.coding.evaluation.api.SourceCodeQuality;
import com.puresoltechnologies.purifinity.uhura.source.CodeLocation;

public abstract class AbstractHalsteadResults extends AbstractEvaluatorResult {

	private static final long serialVersionUID = 7911283186514371553L;

	protected Map<String, Value<?>> convertToRow(HalsteadMetricResult result) {
		Map<String, Value<?>> row = new HashMap<String, Value<?>>();
		if (result != null) {
			HalsteadResult halstead = result.getHalsteadResult();
			row.put(SOURCE_CODE_LOCATION.getName(),
					new GeneralValue<CodeLocation>(result
							.getSourceCodeLocation(), SOURCE_CODE_LOCATION));
			row.put(CODE_RANGE_TYPE.getName(), new GeneralValue<CodeRangeType>(
					result.getCodeRangeType(), CODE_RANGE_TYPE));
			row.put(CODE_RANGE_NAME.getName(),
					new GeneralValue<String>(result.getCodeRangeName(),
							CODE_RANGE_NAME));
			row.put(DIFFERENT_OPERATORS.getName(), new GeneralValue<Integer>(
					halstead.getDifferentOperators(), DIFFERENT_OPERATORS));
			row.put(DIFFERENT_OPERANDS.getName(), new GeneralValue<Integer>(
					halstead.getDifferentOperands(), DIFFERENT_OPERANDS));
			row.put(TOTAL_OPERATORS.getName(), new GeneralValue<Integer>(
					halstead.getTotalOperators(), TOTAL_OPERATORS));
			row.put(TOTAL_OPERANDS.getName(), new GeneralValue<Integer>(
					halstead.getTotalOperands(), TOTAL_OPERANDS));
			row.put(VOCABULARY_SIZE.getName(), new GeneralValue<Integer>(
					halstead.getVocabularySize(), VOCABULARY_SIZE));
			row.put(PROGRAM_LENGTH.getName(), new GeneralValue<Integer>(
					halstead.getProgramLength(), PROGRAM_LENGTH));
			row.put(HALSTEAD_LENGTH.getName(), new GeneralValue<Double>(
					halstead.getHalsteadLength(), HALSTEAD_LENGTH));
			row.put(HALSTEAD_VOLUMNE.getName(), new GeneralValue<Double>(
					halstead.getHalsteadVolume(), HALSTEAD_VOLUMNE));
			row.put(DIFFICULTY.getName(),
					new GeneralValue<Double>(halstead.getDifficulty(),
							DIFFICULTY));
			row.put(PROGRAM_LEVEL.getName(),
					new GeneralValue<Double>(halstead.getProgramLevel(),
							PROGRAM_LEVEL));
			row.put(IMPLEMENTATION_EFFORT.getName(), new GeneralValue<Double>(
					halstead.getImplementationEffort(), IMPLEMENTATION_EFFORT));
			row.put(IMPLEMENTATION_TIME.getName(), new GeneralValue<Double>(
					halstead.getImplementationTime(), IMPLEMENTATION_TIME));
			row.put(ESTIMATED_BUGS.getName(),
					new GeneralValue<Double>(halstead.getEstimatedBugs(),
							ESTIMATED_BUGS));
			SourceCodeQuality quality = result.getQuality();
			if (quality != SourceCodeQuality.UNSPECIFIED) {
				row.put(QUALITY.getName(), new GeneralValue<SourceCodeQuality>(
						quality, QUALITY));
			} else {
				if (getQualityLevel() != null) {
					row.put(QUALITY.getName(),
							new GeneralValue<SourceCodeQuality>(
									getQualityLevel().getQuality(), QUALITY));
				}

			}
			row.put(QUALITY_LEVEL.getName(), new GeneralValue<QualityLevel>(
					getQualityLevel(), QUALITY_LEVEL));
		}
		return row;
	}

}
