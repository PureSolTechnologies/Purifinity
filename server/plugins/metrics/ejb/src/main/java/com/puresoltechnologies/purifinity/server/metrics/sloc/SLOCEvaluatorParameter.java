package com.puresoltechnologies.purifinity.server.metrics.sloc;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.ParameterWithArbitraryUnit;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.CodeRangeNameParameter;
import com.puresoltechnologies.purifinity.evaluation.api.CodeRangeTypeParameter;
import com.puresoltechnologies.purifinity.evaluation.api.QualityLevelParameter;
import com.puresoltechnologies.purifinity.evaluation.api.SourceCodeLocationParameter;
import com.puresoltechnologies.purifinity.evaluation.api.SourceCodeQualityParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.QualityLevel;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;

public class SLOCEvaluatorParameter {

	public static final ParameterWithArbitraryUnit<SourceCodeLocation> SOURCE_CODE_LOCATION = SourceCodeLocationParameter
			.getInstance();
	public static final ParameterWithArbitraryUnit<CodeRangeType> CODE_RANGE_TYPE = CodeRangeTypeParameter
			.getInstance();
	public static final ParameterWithArbitraryUnit<String> CODE_RANGE_NAME = CodeRangeNameParameter
			.getInstance();

	public static final ParameterWithArbitraryUnit<Integer> PHY_LOC = new ParameterWithArbitraryUnit<Integer>(
			"phyLOC", "", LevelOfMeasurement.RATIO,
			"Number pf physical lines of code.", Integer.class);
	public static final ParameterWithArbitraryUnit<Integer> PRO_LOC = new ParameterWithArbitraryUnit<Integer>(
			"proLOC", "", LevelOfMeasurement.RATIO,
			"Number of productive lines of code.", Integer.class);
	public static final ParameterWithArbitraryUnit<Integer> COM_LOC = new ParameterWithArbitraryUnit<Integer>(
			"comLOC", "", LevelOfMeasurement.RATIO,
			"Number of comments lines of code.", Integer.class);
	public static final ParameterWithArbitraryUnit<Integer> BL_LOC = new ParameterWithArbitraryUnit<Integer>(
			"blLOC", "", LevelOfMeasurement.RATIO,
			"Number of blank lines of code.", Integer.class);

	public static final ParameterWithArbitraryUnit<Integer> MIN = new ParameterWithArbitraryUnit<Integer>(
			"minLineLength", "", LevelOfMeasurement.RATIO,
			"Minimal source code line length.", Integer.class);
	public static final ParameterWithArbitraryUnit<Integer> MAX = new ParameterWithArbitraryUnit<Integer>(
			"maxLineLength", "", LevelOfMeasurement.RATIO,
			"Maximal source code line length.", Integer.class);
	public static final ParameterWithArbitraryUnit<Double> AVG = new ParameterWithArbitraryUnit<Double>(
			"avgLineLength", "", LevelOfMeasurement.RATIO,
			"Average of source code line lengths.", Double.class);
	public static final ParameterWithArbitraryUnit<Double> MEDIAN = new ParameterWithArbitraryUnit<Double>(
			"medLineLength", "", LevelOfMeasurement.RATIO,
			"Median of source code line lengths.", Double.class);
	public static final ParameterWithArbitraryUnit<Double> STD_DEV = new ParameterWithArbitraryUnit<Double>(
			"stdDevLineLength", "", LevelOfMeasurement.RATIO,
			"Standard deviation of source code line length.", Double.class);

	public static final ParameterWithArbitraryUnit<SourceCodeQuality> QUALITY = SourceCodeQualityParameter
			.getInstance();
	public static final ParameterWithArbitraryUnit<QualityLevel> QUALITY_LEVEL = QualityLevelParameter
			.getInstance();

	public static final Set<Parameter<?>> ALL = new HashSet<Parameter<?>>();
	static {
		ALL.add(SOURCE_CODE_LOCATION);
		ALL.add(CODE_RANGE_TYPE);
		ALL.add(CODE_RANGE_NAME);
		ALL.add(PHY_LOC);
		ALL.add(PRO_LOC);
		ALL.add(COM_LOC);
		ALL.add(BL_LOC);
		ALL.add(MIN);
		ALL.add(MAX);
		ALL.add(AVG);
		ALL.add(MEDIAN);
		ALL.add(STD_DEV);
		ALL.add(QUALITY);
		ALL.add(QUALITY_LEVEL);
	}

	/**
	 * Private constructor to avoid instantiation.
	 */
	private SLOCEvaluatorParameter() {
	}

}