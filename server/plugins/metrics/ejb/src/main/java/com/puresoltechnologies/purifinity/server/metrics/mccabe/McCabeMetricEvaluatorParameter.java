package com.puresoltechnologies.purifinity.server.metrics.mccabe;

import java.util.HashSet;
import java.util.Set;

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

public class McCabeMetricEvaluatorParameter {

    public static final ParameterWithArbitraryUnit<SourceCodeLocation> SOURCE_CODE_LOCATION = SourceCodeLocationParameter
	    .getInstance();
    public static final ParameterWithArbitraryUnit<CodeRangeType> CODE_RANGE_TYPE = CodeRangeTypeParameter
	    .getInstance();
    public static final ParameterWithArbitraryUnit<String> CODE_RANGE_NAME = CodeRangeNameParameter.getInstance();
    public static final MetricParameter<Integer> VG = new MetricParameter<Integer>("v(G)", "", LevelOfMeasurement.RATIO,
	    "McCabe's cyclomatic complexity for evaluated code range.", Integer.class);
    public static final ParameterWithArbitraryUnit<Severity> SEVERITY = SeverityParameter.getInstance();

    public static final Set<MetricParameter<?>> ALL = new HashSet<>();

    static {
	ALL.add(VG);
    }
}
