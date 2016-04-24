package com.puresoltechnologies.purifinity.server.metrics.normmaint;

import com.puresoltechnologies.commons.domain.LevelOfMeasurement;
import com.puresoltechnologies.commons.domain.ParameterWithArbitraryUnit;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.CodeRangeNameParameter;
import com.puresoltechnologies.purifinity.evaluation.api.CodeRangeTypeParameter;
import com.puresoltechnologies.purifinity.evaluation.api.SeverityParameter;
import com.puresoltechnologies.purifinity.evaluation.api.SourceCodeLocationParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;

public class NormalizedMaintainabilityIndexEvaluatorParameter {

    public static final ParameterWithArbitraryUnit<SourceCodeLocation> SOURCE_CODE_LOCATION = SourceCodeLocationParameter
	    .getInstance();
    public static final ParameterWithArbitraryUnit<CodeRangeType> CODE_RANGE_TYPE = CodeRangeTypeParameter
	    .getInstance();
    public static final ParameterWithArbitraryUnit<String> CODE_RANGE_NAME = CodeRangeNameParameter.getInstance();
    public static final MetricParameter<Double> NORM_MI_WOC = new MetricParameter<Double>("nMIwoc", "",
	    LevelOfMeasurement.ORDINAL, "Normalized maintainability index without comments", Double.class);
    public static final MetricParameter<Double> NORM_MI_CW = new MetricParameter<Double>("nMIcw", "",
	    LevelOfMeasurement.ORDINAL, "Normalized maintainability index comment weight", Double.class);
    public static final MetricParameter<Double> NORM_MI = new MetricParameter<Double>("nMI", "",
	    LevelOfMeasurement.ORDINAL, "Normalized maintainability index including comments", Double.class);
    public static final ParameterWithArbitraryUnit<Severity> SEVERITY = SeverityParameter.getInstance();

    public static final MetricParameter<?>[] ALL = new MetricParameter<?>[] { NORM_MI_WOC, NORM_MI_CW, NORM_MI };
}
