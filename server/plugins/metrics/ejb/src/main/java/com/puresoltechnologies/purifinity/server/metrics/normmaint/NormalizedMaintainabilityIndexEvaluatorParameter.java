package com.puresoltechnologies.purifinity.server.metrics.normmaint;

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

public class NormalizedMaintainabilityIndexEvaluatorParameter {

    public static final ParameterWithArbitraryUnit<SourceCodeLocation> SOURCE_CODE_LOCATION = SourceCodeLocationParameter
	    .getInstance();
    public static final ParameterWithArbitraryUnit<CodeRangeType> CODE_RANGE_TYPE = CodeRangeTypeParameter
	    .getInstance();
    public static final ParameterWithArbitraryUnit<String> CODE_RANGE_NAME = CodeRangeNameParameter
	    .getInstance();
    public static final MetricParameter<Double> NORM_MI_WOC = new MetricParameter<Double>(
	    "nMIwoc", "", LevelOfMeasurement.ORDINAL,
	    "Normalized maintainability index without comments", Double.class);
    public static final MetricParameter<Double> NORM_MI_CW = new MetricParameter<Double>(
	    "nMIcw", "", LevelOfMeasurement.ORDINAL,
	    "Normalized maintainability index comment weight", Double.class);
    public static final MetricParameter<Double> NORM_MI = new MetricParameter<Double>(
	    "nMI", "", LevelOfMeasurement.ORDINAL,
	    "Normalized maintainability index including comments", Double.class);
    public static final ParameterWithArbitraryUnit<SourceCodeQuality> QUALITY = SourceCodeQualityParameter
	    .getInstance();
    public static final ParameterWithArbitraryUnit<QualityLevel> QUALITY_LEVEL = QualityLevelParameter
	    .getInstance();

    public static final Set<MetricParameter<?>> ALL = new HashSet<MetricParameter<?>>();
    static {
	ALL.add(NORM_MI_WOC);
	ALL.add(NORM_MI_CW);
	ALL.add(NORM_MI);
    }
}
