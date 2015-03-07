package com.puresoltechnologies.purifinity.server.metrics.cocomo.basic;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.money.Money;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.CodeRangeNameParameter;
import com.puresoltechnologies.purifinity.evaluation.api.CodeRangeTypeParameter;
import com.puresoltechnologies.purifinity.evaluation.api.SourceCodeLocationParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;

public class BasicCoCoMoEvaluatorParameter {

    public static final Parameter<SourceCodeLocation> SOURCE_CODE_LOCATION = SourceCodeLocationParameter
	    .getInstance();
    public static final Parameter<CodeRangeType> CODE_RANGE_TYPE = CodeRangeTypeParameter
	    .getInstance();
    public static final Parameter<String> CODE_RANGE_NAME = CodeRangeNameParameter
	    .getInstance();

    public static final MetricParameter<Double> KSLOC = new MetricParameter<Double>(
	    "kPhyLOC",
	    "kSLOC",
	    LevelOfMeasurement.RATIO,
	    "Total Physical Source Lines Of Code in thousands:\nkPhyLOC = phyLOC / 1000",
	    Double.class);
    public static final MetricParameter<Double> PERSON_MONTH = new MetricParameter<Double>(
	    "Development Effort in Month", "Person-Months",
	    LevelOfMeasurement.RATIO,
	    "Basic COCOMO model, Person-Months = c1 * (kPhyLOC ^ c2)",
	    Double.class);
    public static final MetricParameter<Double> PERSON_YEARS = new MetricParameter<Double>(
	    "Development Effort in Years", "Person-Years",
	    LevelOfMeasurement.RATIO,
	    "Person-Years = Person-Month / (12 Month / Year)", Double.class);
    public static final MetricParameter<Double> SCHEDULED_MONTH = new MetricParameter<Double>(
	    "Schedule in Months", "Months", LevelOfMeasurement.RATIO,
	    "Basic COCOMO model, Months = 2.5 * (Person-Months ^ c3)",
	    Double.class);
    public static final MetricParameter<Double> SCHEDULED_YEARS = new MetricParameter<Double>(
	    "Schedule in Years", "Years", LevelOfMeasurement.RATIO,
	    "Years = Months / 12", Double.class);
    public static final MetricParameter<Double> TEAM_SIZE = new MetricParameter<Double>(
	    "Team Size", "", LevelOfMeasurement.RATIO, "Effort / Schedule",
	    Double.class);
    public static final MetricParameter<Double> COSTS = new MetricParameter<Double>(
	    "Estimated Cost", "kMoney", LevelOfMeasurement.RATIO,
	    "cost = Schedule * Team Size * 2.4 / 1000.0 * Salary", Double.class);
    public static final MetricParameter<Money> SALARY = new MetricParameter<Money>(
	    "Salary", "Currency", LevelOfMeasurement.RATIO,
	    "Average developer salary.", Money.class);
    public static final MetricParameter<Double> C1 = new MetricParameter<Double>(
	    "c1", "", LevelOfMeasurement.RATIO,
	    "CoCoMo equation constant. This constant is set by complexity.",
	    Double.class);
    public static final MetricParameter<Double> C2 = new MetricParameter<Double>(
	    "c2", "", LevelOfMeasurement.RATIO,
	    "CoCoMo equation constant. This constant is set by complexity.",
	    Double.class);
    public static final MetricParameter<Double> C3 = new MetricParameter<Double>(
	    "c3", "", LevelOfMeasurement.RATIO,
	    "CoCoMo equation constant. This constant is set by complexity.",
	    Double.class);

    public static final Set<MetricParameter<?>> ALL = new HashSet<>();
    static {
	ALL.add(KSLOC);
	ALL.add(PERSON_MONTH);
	ALL.add(PERSON_YEARS);
	ALL.add(SCHEDULED_MONTH);
	ALL.add(SCHEDULED_YEARS);
	ALL.add(TEAM_SIZE);
	ALL.add(COSTS);
	ALL.add(SALARY);
	ALL.add(C1);
	ALL.add(C2);
	ALL.add(C3);
    }
}
