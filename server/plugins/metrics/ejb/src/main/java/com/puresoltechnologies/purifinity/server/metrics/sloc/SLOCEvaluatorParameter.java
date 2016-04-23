package com.puresoltechnologies.purifinity.server.metrics.sloc;

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

public class SLOCEvaluatorParameter {

    public static final ParameterWithArbitraryUnit<SourceCodeLocation> SOURCE_CODE_LOCATION = SourceCodeLocationParameter
	    .getInstance();
    public static final ParameterWithArbitraryUnit<CodeRangeType> CODE_RANGE_TYPE = CodeRangeTypeParameter
	    .getInstance();
    public static final ParameterWithArbitraryUnit<String> CODE_RANGE_NAME = CodeRangeNameParameter.getInstance();

    public static final MetricParameter<Integer> PHY_LOC = new MetricParameter<Integer>("phyLOC", "",
	    LevelOfMeasurement.RATIO, "Number pf physical lines of code.", Integer.class);
    public static final MetricParameter<Integer> PRO_LOC = new MetricParameter<Integer>("proLOC", "",
	    LevelOfMeasurement.RATIO, "Number of productive lines of code.", Integer.class);
    public static final MetricParameter<Integer> COM_LOC = new MetricParameter<Integer>("comLOC", "",
	    LevelOfMeasurement.RATIO, "Number of comments lines of code.", Integer.class);
    public static final MetricParameter<Integer> BL_LOC = new MetricParameter<Integer>("blLOC", "",
	    LevelOfMeasurement.RATIO, "Number of blank lines of code.", Integer.class);

    public static final MetricParameter<Integer> MIN = new MetricParameter<Integer>("minLineLength", "",
	    LevelOfMeasurement.RATIO, "Minimal source code line length.", Integer.class);
    public static final MetricParameter<Integer> MAX = new MetricParameter<Integer>("maxLineLength", "",
	    LevelOfMeasurement.RATIO, "Maximal source code line length.", Integer.class);
    public static final MetricParameter<Double> AVG = new MetricParameter<Double>("avgLineLength", "",
	    LevelOfMeasurement.RATIO, "Average of source code line lengths.", Double.class);
    public static final MetricParameter<Double> MEDIAN = new MetricParameter<Double>("medLineLength", "",
	    LevelOfMeasurement.RATIO, "Median of source code line lengths.", Double.class);
    public static final MetricParameter<Double> STD_DEV = new MetricParameter<Double>("stdDevLineLength", "",
	    LevelOfMeasurement.RATIO, "Standard deviation of source code line length.", Double.class);

    public static final ParameterWithArbitraryUnit<Severity> SEVERITY = SeverityParameter.getInstance();

    public static final Set<MetricParameter<?>> ALL = new HashSet<>();

    static {
	ALL.add(PHY_LOC);
	ALL.add(PRO_LOC);
	ALL.add(COM_LOC);
	ALL.add(BL_LOC);
	ALL.add(MIN);
	ALL.add(MAX);
	ALL.add(AVG);
	ALL.add(MEDIAN);
	ALL.add(STD_DEV);
    }

    /**
     * Private constructor to avoid instantiation.
     */
    private SLOCEvaluatorParameter() {
    }

}
