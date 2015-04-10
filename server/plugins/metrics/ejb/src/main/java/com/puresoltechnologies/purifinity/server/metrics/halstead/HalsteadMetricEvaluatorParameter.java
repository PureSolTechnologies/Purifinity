package com.puresoltechnologies.purifinity.server.metrics.halstead;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.commons.domain.LevelOfMeasurement;
import com.puresoltechnologies.commons.domain.ParameterWithArbitraryUnit;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.CodeRangeNameParameter;
import com.puresoltechnologies.purifinity.evaluation.api.CodeRangeTypeParameter;
import com.puresoltechnologies.purifinity.evaluation.api.QualityLevelParameter;
import com.puresoltechnologies.purifinity.evaluation.api.SourceCodeLocationParameter;
import com.puresoltechnologies.purifinity.evaluation.api.SourceCodeQualityParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.QualityLevel;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;

public class HalsteadMetricEvaluatorParameter {

	public static final ParameterWithArbitraryUnit<SourceCodeLocation> SOURCE_CODE_LOCATION = SourceCodeLocationParameter
			.getInstance();
	public static final ParameterWithArbitraryUnit<CodeRangeType> CODE_RANGE_TYPE = CodeRangeTypeParameter
			.getInstance();
	public static final ParameterWithArbitraryUnit<String> CODE_RANGE_NAME = CodeRangeNameParameter
			.getInstance();
	public static final MetricParameter<Integer> DIFFERENT_OPERATORS = new MetricParameter<Integer>(
			"n1", "", LevelOfMeasurement.RATIO, "Number of unique operators",
			Integer.class);
	public static final MetricParameter<Integer> DIFFERENT_OPERANDS = new MetricParameter<Integer>(
			"n2", "", LevelOfMeasurement.RATIO, "Number of unique operands",
			Integer.class);
	public static final MetricParameter<Integer> TOTAL_OPERATORS = new MetricParameter<Integer>(
			"N1", "", LevelOfMeasurement.RATIO, "Total number of operators",
			Integer.class);
	public static final MetricParameter<Integer> TOTAL_OPERANDS = new MetricParameter<Integer>(
			"N2", "", LevelOfMeasurement.RATIO, "Total number of operands",
			Integer.class);
	public static final MetricParameter<Integer> VOCABULARY_SIZE = new MetricParameter<Integer>(
			"N", "", LevelOfMeasurement.RATIO, "Program length", Integer.class);
	public static final MetricParameter<Integer> PROGRAM_LENGTH = new MetricParameter<Integer>(
			"n", "", LevelOfMeasurement.RATIO, "Vocabulary Size", Integer.class);
	public static final MetricParameter<Double> HALSTEAD_LENGTH = new MetricParameter<Double>(
			"V", "", LevelOfMeasurement.RATIO, "Halstead volume", Double.class);
	public static final MetricParameter<Double> HALSTEAD_VOLUMNE = new MetricParameter<Double>(
			"Hl", "", LevelOfMeasurement.RATIO, "Halstead length", Double.class);
	public static final MetricParameter<Double> DIFFICULTY = new MetricParameter<Double>(
			"D", "", LevelOfMeasurement.RATIO, "Difficulty", Double.class);
	public static final MetricParameter<Double> PROGRAM_LEVEL = new MetricParameter<Double>(
			"L", "", LevelOfMeasurement.RATIO, "Program level", Double.class);
	public static final MetricParameter<Double> IMPLEMENTATION_EFFORT = new MetricParameter<Double>(
			"E", "", LevelOfMeasurement.RATIO, "Implementation effort",
			Double.class);
	public static final MetricParameter<Double> IMPLEMENTATION_TIME = new MetricParameter<Double>(
			"T", "Seconds", LevelOfMeasurement.RATIO, "Implementation time",
			Double.class);
	public static final MetricParameter<Double> ESTIMATED_BUGS = new MetricParameter<Double>(
			"B", "", LevelOfMeasurement.RATIO, "Number of delivered bugs",
			Double.class);
	public static final ParameterWithArbitraryUnit<SourceCodeQuality> QUALITY = SourceCodeQualityParameter
			.getInstance();
	public static final ParameterWithArbitraryUnit<QualityLevel> QUALITY_LEVEL = QualityLevelParameter
			.getInstance();

	public static final Set<MetricParameter<?>> ALL = new HashSet<>();
	static {
		ALL.add(DIFFERENT_OPERATORS);
		ALL.add(DIFFERENT_OPERANDS);
		ALL.add(TOTAL_OPERATORS);
		ALL.add(TOTAL_OPERANDS);
		ALL.add(VOCABULARY_SIZE);
		ALL.add(PROGRAM_LENGTH);
		ALL.add(HALSTEAD_LENGTH);
		ALL.add(HALSTEAD_VOLUMNE);
		ALL.add(DIFFICULTY);
		ALL.add(PROGRAM_LEVEL);
		ALL.add(IMPLEMENTATION_EFFORT);
		ALL.add(IMPLEMENTATION_TIME);
		ALL.add(ESTIMATED_BUGS);
	}
}
