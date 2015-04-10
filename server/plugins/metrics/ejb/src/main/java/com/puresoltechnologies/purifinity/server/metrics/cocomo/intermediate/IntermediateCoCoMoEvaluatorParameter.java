package com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.commons.domain.LevelOfMeasurement;
import com.puresoltechnologies.commons.domain.ParameterWithArbitraryUnit;
import com.puresoltechnologies.commons.money.Money;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.CodeRangeNameParameter;
import com.puresoltechnologies.purifinity.evaluation.api.CodeRangeTypeParameter;
import com.puresoltechnologies.purifinity.evaluation.api.SourceCodeLocationParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;

public class IntermediateCoCoMoEvaluatorParameter {

	public static final ParameterWithArbitraryUnit<SourceCodeLocation> SOURCE_CODE_LOCATION = SourceCodeLocationParameter
			.getInstance();
	public static final ParameterWithArbitraryUnit<CodeRangeType> CODE_RANGE_TYPE = CodeRangeTypeParameter
			.getInstance();
	public static final ParameterWithArbitraryUnit<String> CODE_RANGE_NAME = CodeRangeNameParameter
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
			"Intermediate COCOMO model, Person-Months = c1 * (kPhyLOC ^ c2)",
			Double.class);
	public static final MetricParameter<Double> PERSON_YEARS = new MetricParameter<Double>(
			"Development Effort in Years", "Person-Years",
			LevelOfMeasurement.RATIO,
			"Person-Years = Person-Month / (12 Month / Year)", Double.class);
	public static final MetricParameter<Double> SCHEDULED_MONTH = new MetricParameter<Double>(
			"Schedule in Months", "Months", LevelOfMeasurement.RATIO,
			"Intermediate COCOMO model, Months = 2.5 * (Person-Months ^ c3)",
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
	public static final MetricParameter<Double> AI = new MetricParameter<Double>(
			"ai",
			"",
			LevelOfMeasurement.RATIO,
			"Intermediate CoCoMo equation constant. This constant is set by project.",
			Double.class);
	public static final MetricParameter<Double> BI = new MetricParameter<Double>(
			"bi",
			"",
			LevelOfMeasurement.RATIO,
			"Intermediate CoCoMo equation constant. This constant is set by project.",
			Double.class);
	public static final MetricParameter<Double> CI = new MetricParameter<Double>(
			"ci",
			"",
			LevelOfMeasurement.RATIO,
			"Intermediate CoCoMo equation constant. This constant is set by project.",
			Double.class);
	public static final MetricParameter<Double> DI = new MetricParameter<Double>(
			"di",
			"",
			LevelOfMeasurement.RATIO,
			"Intermediate CoCoMo equation constant. This constant is set by project.",
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
		ALL.add(AI);
		ALL.add(BI);
		ALL.add(CI);
	}
}
