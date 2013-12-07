package com.puresoltechnologies.purifinity.coding.metrics.cocomo.intermediate;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.commons.math.Money;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.ParameterWithArbitraryUnit;
import com.puresoltechnologies.parser.impl.source.CodeLocation;
import com.puresoltechnologies.purifinity.coding.analysis.api.CodeRangeType;
import com.puresoltechnologies.purifinity.coding.evaluation.api.CodeRangeNameParameter;
import com.puresoltechnologies.purifinity.coding.evaluation.api.CodeRangeTypeParameter;
import com.puresoltechnologies.purifinity.coding.evaluation.api.SourceCodeLocationParameter;

public class IntermediateCoCoMoEvaluatorParameter {

	public static final ParameterWithArbitraryUnit<CodeLocation> SOURCE_CODE_LOCATION = SourceCodeLocationParameter
			.getInstance();
	public static final ParameterWithArbitraryUnit<CodeRangeType> CODE_RANGE_TYPE = CodeRangeTypeParameter
			.getInstance();
	public static final ParameterWithArbitraryUnit<String> CODE_RANGE_NAME = CodeRangeNameParameter
			.getInstance();

	public static final ParameterWithArbitraryUnit<Double> KSLOC = new ParameterWithArbitraryUnit<Double>(
			"kPhyLOC",
			"kSLOC",
			LevelOfMeasurement.RATIO,
			"Total Physical Source Lines Of Code in thousands:\nkPhyLOC = phyLOC / 1000",
			Double.class);
	public static final ParameterWithArbitraryUnit<Double> PERSON_MONTH = new ParameterWithArbitraryUnit<Double>(
			"Development Effort in Month", "Person-Months",
			LevelOfMeasurement.RATIO,
			"Intermediate COCOMO model, Person-Months = c1 * (kPhyLOC ^ c2)",
			Double.class);
	public static final ParameterWithArbitraryUnit<Double> PERSON_YEARS = new ParameterWithArbitraryUnit<Double>(
			"Development Effort in Years", "Person-Years",
			LevelOfMeasurement.RATIO,
			"Person-Years = Person-Month / (12 Month / Year)", Double.class);
	public static final ParameterWithArbitraryUnit<Double> SCHEDULED_MONTH = new ParameterWithArbitraryUnit<Double>(
			"Schedule in Months", "Months", LevelOfMeasurement.RATIO,
			"Intermediate COCOMO model, Months = 2.5 * (Person-Months ^ c3)",
			Double.class);
	public static final ParameterWithArbitraryUnit<Double> SCHEDULED_YEARS = new ParameterWithArbitraryUnit<Double>(
			"Schedule in Years", "Years", LevelOfMeasurement.RATIO,
			"Years = Months / 12", Double.class);
	public static final ParameterWithArbitraryUnit<Double> TEAM_SIZE = new ParameterWithArbitraryUnit<Double>(
			"Team Size", "", LevelOfMeasurement.RATIO, "Effort / Schedule",
			Double.class);
	public static final ParameterWithArbitraryUnit<Double> COSTS = new ParameterWithArbitraryUnit<Double>(
			"Estimated Cost", "kMoney", LevelOfMeasurement.RATIO,
			"cost = Schedule * Team Size * 2.4 / 1000.0 * Salary", Double.class);
	public static final ParameterWithArbitraryUnit<Money> SALARY = new ParameterWithArbitraryUnit<Money>(
			"Salary", "Currency", LevelOfMeasurement.RATIO,
			"Average developer salary.", Money.class);
	public static final ParameterWithArbitraryUnit<SoftwareProject> PROJECT = new ParameterWithArbitraryUnit<>(
			"project", "", LevelOfMeasurement.NOMINAL,
			"Software project. Specifies the type of project.",
			SoftwareProject.class);
	public static final ParameterWithArbitraryUnit<Double> AI = new ParameterWithArbitraryUnit<Double>(
			"ai",
			"",
			LevelOfMeasurement.RATIO,
			"Intermediate CoCoMo equation constant. This constant is set by project.",
			Double.class);
	public static final ParameterWithArbitraryUnit<Double> BI = new ParameterWithArbitraryUnit<Double>(
			"bi",
			"",
			LevelOfMeasurement.RATIO,
			"Intermediate CoCoMo equation constant. This constant is set by project.",
			Double.class);
	public static final ParameterWithArbitraryUnit<Double> CI = new ParameterWithArbitraryUnit<Double>(
			"ci",
			"",
			LevelOfMeasurement.RATIO,
			"Intermediate CoCoMo equation constant. This constant is set by project.",
			Double.class);
	public static final ParameterWithArbitraryUnit<Double> DI = new ParameterWithArbitraryUnit<Double>(
			"di",
			"",
			LevelOfMeasurement.RATIO,
			"Intermediate CoCoMo equation constant. This constant is set by project.",
			Double.class);

	public static final Set<Parameter<?>> ALL = new HashSet<Parameter<?>>();
	static {
		ALL.add(SOURCE_CODE_LOCATION);
		ALL.add(CODE_RANGE_TYPE);
		ALL.add(CODE_RANGE_NAME);
		ALL.add(KSLOC);
		ALL.add(PERSON_MONTH);
		ALL.add(PERSON_YEARS);
		ALL.add(SCHEDULED_MONTH);
		ALL.add(SCHEDULED_YEARS);
		ALL.add(TEAM_SIZE);
		ALL.add(COSTS);
		ALL.add(SALARY);
		ALL.add(PROJECT);
		ALL.add(AI);
		ALL.add(BI);
		ALL.add(CI);
	}
}
