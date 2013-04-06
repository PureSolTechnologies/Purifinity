package com.puresol.coding.metrics.halstead;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.evaluation.api.CodeRangeNameParameter;
import com.puresol.coding.evaluation.api.CodeRangeTypeParameter;
import com.puresol.coding.evaluation.api.SourceCodeLocationParameter;
import com.puresol.coding.evaluation.api.SourceCodeQuality;
import com.puresol.coding.evaluation.api.SourceCodeQualityParameter;
import com.puresol.uhura.source.CodeLocation;
import com.puresol.utils.math.LevelOfMeasurement;
import com.puresol.utils.math.Parameter;
import com.puresol.utils.math.ParameterWithArbitraryUnit;

public class HalsteadMetricEvaluatorParameter {

	public static final ParameterWithArbitraryUnit<CodeLocation> SOURCE_CODE_LOCATION = SourceCodeLocationParameter
			.getInstance();
	public static final ParameterWithArbitraryUnit<CodeRangeType> CODE_RANGE_TYPE = CodeRangeTypeParameter
			.getInstance();
	public static final ParameterWithArbitraryUnit<String> CODE_RANGE_NAME = CodeRangeNameParameter
			.getInstance();
	public static final ParameterWithArbitraryUnit<Integer> DIFFERENT_OPERATORS = new ParameterWithArbitraryUnit<Integer>(
			"n1", "", LevelOfMeasurement.RATIO, "Number of unique operators",
			Integer.class);
	public static final ParameterWithArbitraryUnit<Integer> DIFFERENT_OPERANDS = new ParameterWithArbitraryUnit<Integer>(
			"n2", "", LevelOfMeasurement.RATIO, "Number of unique operands",
			Integer.class);
	public static final ParameterWithArbitraryUnit<Integer> TOTAL_OPERATORS = new ParameterWithArbitraryUnit<Integer>(
			"N1", "", LevelOfMeasurement.RATIO, "Total number of operators",
			Integer.class);
	public static final ParameterWithArbitraryUnit<Integer> TOTAL_OPERANDS = new ParameterWithArbitraryUnit<Integer>(
			"N2", "", LevelOfMeasurement.RATIO, "Total number of operands",
			Integer.class);
	public static final ParameterWithArbitraryUnit<Integer> VOCABULARY_SIZE = new ParameterWithArbitraryUnit<Integer>(
			"N", "", LevelOfMeasurement.RATIO, "Program length", Integer.class);
	public static final ParameterWithArbitraryUnit<Integer> PROGRAM_LENGTH = new ParameterWithArbitraryUnit<Integer>(
			"n", "", LevelOfMeasurement.RATIO, "Vocabulary Size", Integer.class);
	public static final ParameterWithArbitraryUnit<Double> HALSTEAD_LENGTH = new ParameterWithArbitraryUnit<Double>(
			"V", "", LevelOfMeasurement.RATIO, "Halstead volume", Double.class);
	public static final ParameterWithArbitraryUnit<Double> HALSTEAD_VOLUMNE = new ParameterWithArbitraryUnit<Double>(
			"Hl", "", LevelOfMeasurement.RATIO, "Halstead length", Double.class);
	public static final ParameterWithArbitraryUnit<Double> DIFFICULTY = new ParameterWithArbitraryUnit<Double>(
			"D", "", LevelOfMeasurement.RATIO, "Difficulty", Double.class);
	public static final ParameterWithArbitraryUnit<Double> PROGRAM_LEVEL = new ParameterWithArbitraryUnit<Double>(
			"L", "", LevelOfMeasurement.RATIO, "Program level", Double.class);
	public static final ParameterWithArbitraryUnit<Double> IMPLEMENTATION_EFFORT = new ParameterWithArbitraryUnit<Double>(
			"E", "", LevelOfMeasurement.RATIO, "Implementation effort",
			Double.class);
	public static final ParameterWithArbitraryUnit<Double> IMPLEMENTATION_TIME = new ParameterWithArbitraryUnit<Double>(
			"T", "Seconds", LevelOfMeasurement.RATIO, "Implementation time",
			Double.class);
	public static final ParameterWithArbitraryUnit<Double> ESTIMATED_BUGS = new ParameterWithArbitraryUnit<Double>(
			"B", "", LevelOfMeasurement.RATIO, "Number of delivered bugs",
			Double.class);
	public static final ParameterWithArbitraryUnit<SourceCodeQuality> QUALITY = SourceCodeQualityParameter
			.getInstance();

	public static final List<Parameter<?>> ALL = new ArrayList<Parameter<?>>();
	static {
		ALL.add(SOURCE_CODE_LOCATION);
		ALL.add(CODE_RANGE_TYPE);
		ALL.add(CODE_RANGE_NAME);
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
		ALL.add(QUALITY);
	}
}
