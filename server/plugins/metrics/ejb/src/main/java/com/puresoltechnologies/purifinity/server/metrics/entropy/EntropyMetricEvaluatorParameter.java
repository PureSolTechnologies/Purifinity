package com.puresoltechnologies.purifinity.server.metrics.entropy;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.commons.math.LevelOfMeasurement;
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
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;

public class EntropyMetricEvaluatorParameter {

    public static final ParameterWithArbitraryUnit<SourceCodeLocation> SOURCE_CODE_LOCATION = SourceCodeLocationParameter
	    .getInstance();
    public static final ParameterWithArbitraryUnit<CodeRangeType> CODE_RANGE_TYPE = CodeRangeTypeParameter
	    .getInstance();
    public static final ParameterWithArbitraryUnit<String> CODE_RANGE_NAME = CodeRangeNameParameter
	    .getInstance();
    public static final MetricParameter<Integer> N_DIFF = new MetricParameter<Integer>(
	    "nDiff", "", LevelOfMeasurement.RATIO,
	    "Number of different operators", Integer.class);
    public static final MetricParameter<Integer> N_TOTAL = new MetricParameter<Integer>(
	    "nTotal", "", LevelOfMeasurement.RATIO,
	    "Total number of operators", Integer.class);
    public static final MetricParameter<Double> S = new MetricParameter<Double>(
	    "S", "", LevelOfMeasurement.RATIO, "Entropy", Double.class);
    public static final MetricParameter<Double> S_MAX = new MetricParameter<Double>(
	    "Smax", "", LevelOfMeasurement.RATIO, "Maximum entropy",
	    Double.class);
    public static final MetricParameter<Double> S_NORM = new MetricParameter<Double>(
	    "Snorm", "", LevelOfMeasurement.RATIO, "Normalized entropy",
	    Double.class);
    public static final MetricParameter<Double> RS = new MetricParameter<Double>(
	    "Rs", "", LevelOfMeasurement.RATIO, "Entropic redundancy",
	    Double.class);
    public static final MetricParameter<Double> R = new MetricParameter<Double>(
	    "R", "", LevelOfMeasurement.RATIO, "Redundancy", Double.class);
    public static final MetricParameter<Double> R_NORM = new MetricParameter<Double>(
	    "Rnorm", "", LevelOfMeasurement.RATIO, "Normalized redundancy",
	    Double.class);
    public static final ParameterWithArbitraryUnit<SourceCodeQuality> QUALITY = SourceCodeQualityParameter
	    .getInstance();
    public static final ParameterWithArbitraryUnit<QualityLevel> QUALITY_LEVEL = QualityLevelParameter
	    .getInstance();

    public static final Set<MetricParameter<?>> ALL = new HashSet<>();
    static {
	ALL.add(N_DIFF);
	ALL.add(N_TOTAL);
	ALL.add(S);
	ALL.add(S_MAX);
	ALL.add(S_NORM);
	ALL.add(RS);
	ALL.add(R);
	ALL.add(R_NORM);
    }
}
