package com.puresol.purifinity.coding.metrics.cocomo.basic;

import static com.puresol.purifinity.coding.metrics.cocomo.basic.BasicCoCoMoEvaluatorParameter.ALL;
import static com.puresol.purifinity.coding.metrics.cocomo.basic.BasicCoCoMoEvaluatorParameter.C1;
import static com.puresol.purifinity.coding.metrics.cocomo.basic.BasicCoCoMoEvaluatorParameter.C2;
import static com.puresol.purifinity.coding.metrics.cocomo.basic.BasicCoCoMoEvaluatorParameter.C3;
import static com.puresol.purifinity.coding.metrics.cocomo.basic.BasicCoCoMoEvaluatorParameter.CODE_RANGE_NAME;
import static com.puresol.purifinity.coding.metrics.cocomo.basic.BasicCoCoMoEvaluatorParameter.CODE_RANGE_TYPE;
import static com.puresol.purifinity.coding.metrics.cocomo.basic.BasicCoCoMoEvaluatorParameter.COMPLEXITY;
import static com.puresol.purifinity.coding.metrics.cocomo.basic.BasicCoCoMoEvaluatorParameter.COSTS;
import static com.puresol.purifinity.coding.metrics.cocomo.basic.BasicCoCoMoEvaluatorParameter.KSLOC;
import static com.puresol.purifinity.coding.metrics.cocomo.basic.BasicCoCoMoEvaluatorParameter.PERSON_MONTH;
import static com.puresol.purifinity.coding.metrics.cocomo.basic.BasicCoCoMoEvaluatorParameter.PERSON_YEARS;
import static com.puresol.purifinity.coding.metrics.cocomo.basic.BasicCoCoMoEvaluatorParameter.SALARY;
import static com.puresol.purifinity.coding.metrics.cocomo.basic.BasicCoCoMoEvaluatorParameter.SCHEDULED_MONTH;
import static com.puresol.purifinity.coding.metrics.cocomo.basic.BasicCoCoMoEvaluatorParameter.SCHEDULED_YEARS;
import static com.puresol.purifinity.coding.metrics.cocomo.basic.BasicCoCoMoEvaluatorParameter.SOURCE_CODE_LOCATION;
import static com.puresol.purifinity.coding.metrics.cocomo.basic.BasicCoCoMoEvaluatorParameter.TEAM_SIZE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.puresol.commons.math.GeneralValue;
import com.puresol.commons.math.Money;
import com.puresol.commons.math.Parameter;
import com.puresol.commons.math.Value;
import com.puresol.purifinity.coding.analysis.api.CodeRangeType;
import com.puresol.purifinity.coding.evaluation.api.MetricFileResults;
import com.puresol.purifinity.uhura.source.CodeLocation;

public class BasicCoCoMoFileResults extends BasicCoCoMoResults implements
		MetricFileResults {

	private static final long serialVersionUID = 7272355142441159285L;

	private final CodeLocation codeLocation;

	public BasicCoCoMoFileResults(CodeLocation codeLocation) {
		super();
		this.codeLocation = codeLocation;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return ALL;
	}

	@Override
	public List<Map<String, Value<?>>> getValues() {
		Map<String, Value<?>> row = new HashMap<String, Value<?>>();

		row.put(SOURCE_CODE_LOCATION.getName(), new GeneralValue<CodeLocation>(
				codeLocation, SOURCE_CODE_LOCATION));
		row.put(CODE_RANGE_TYPE.getName(), new GeneralValue<CodeRangeType>(
				CodeRangeType.FILE, CODE_RANGE_TYPE));
		row.put(CODE_RANGE_NAME.getName(), new GeneralValue<String>(
				codeLocation.getName(), CODE_RANGE_NAME));

		row.put(KSLOC.getName(), new GeneralValue<Double>(getKsloc(), KSLOC));
		row.put(PERSON_MONTH.getName(), new GeneralValue<Double>(
				getPersonMonth(), PERSON_MONTH));
		row.put(PERSON_YEARS.getName(), new GeneralValue<Double>(
				getPersonYears(), PERSON_YEARS));
		row.put(SCHEDULED_MONTH.getName(), new GeneralValue<Double>(
				getScheduledMonth(), SCHEDULED_MONTH));
		row.put(SCHEDULED_YEARS.getName(), new GeneralValue<Double>(
				getScheduledYears(), SCHEDULED_YEARS));
		row.put(TEAM_SIZE.getName(), new GeneralValue<Double>(getTeamSize(),
				TEAM_SIZE));
		row.put(COSTS.getName(), new GeneralValue<Double>(getEstimatedCosts(),
				COSTS));

		row.put(SALARY.getName(), new GeneralValue<Money>(getMoney(), SALARY));
		row.put(COMPLEXITY.getName(), new GeneralValue<SoftwareComplexity>(
				getComplexity(), COMPLEXITY));
		row.put(C1.getName(), new GeneralValue<Double>(getC1(), C1));
		row.put(C2.getName(), new GeneralValue<Double>(getC2(), C2));
		row.put(C3.getName(), new GeneralValue<Double>(getC3(), C3));

		List<Map<String, Value<?>>> values = new ArrayList<Map<String, Value<?>>>();
		values.add(row);
		return values;
	}
}
