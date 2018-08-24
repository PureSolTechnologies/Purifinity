package com.puresoltechnologies.purifinity.server.metrics.maintainability;

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

public class MaintainabilityIndexEvaluatorParameter {

    public static final ParameterWithArbitraryUnit<SourceCodeLocation> SOURCE_CODE_LOCATION = SourceCodeLocationParameter
	    .getInstance();
    public static final ParameterWithArbitraryUnit<CodeRangeType> CODE_RANGE_TYPE = CodeRangeTypeParameter
	    .getInstance();
    public static final ParameterWithArbitraryUnit<String> CODE_RANGE_NAME = CodeRangeNameParameter.getInstance();
    public static final MetricParameter<Double> MI_WOC = new MetricParameter<Double>("MIwoc", "",
	    LevelOfMeasurement.ORDINAL, "Maintainability index without comments", Double.class);
    public static final MetricParameter<Double> MI_CW = new MetricParameter<Double>("MIcw", "",
	    LevelOfMeasurement.ORDINAL, "Maintainability index comment weight", Double.class);
    public static final MetricParameter<Double> MI = new MetricParameter<Double>("MI", "", LevelOfMeasurement.ORDINAL,
	    "Maintainability index including comments", Double.class);
    public static final ParameterWithArbitraryUnit<Severity> SEVERITY = SeverityParameter.getInstance();

    public static final MetricParameter<?>[] ALL = new MetricParameter<?>[] { MI_WOC, MI_CW, MI };
}
