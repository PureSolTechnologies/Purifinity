package com.puresol.coding.metrics.cocomo;

import static com.puresol.coding.metrics.cocomo.CoCoMoEvaluatorParameter.ALL;
import static com.puresol.coding.metrics.cocomo.CoCoMoEvaluatorParameter.C1;
import static com.puresol.coding.metrics.cocomo.CoCoMoEvaluatorParameter.C2;
import static com.puresol.coding.metrics.cocomo.CoCoMoEvaluatorParameter.C3;
import static com.puresol.coding.metrics.cocomo.CoCoMoEvaluatorParameter.COMPLEXITY;
import static com.puresol.coding.metrics.cocomo.CoCoMoEvaluatorParameter.COSTS;
import static com.puresol.coding.metrics.cocomo.CoCoMoEvaluatorParameter.KSLOC;
import static com.puresol.coding.metrics.cocomo.CoCoMoEvaluatorParameter.PERSON_MONTH;
import static com.puresol.coding.metrics.cocomo.CoCoMoEvaluatorParameter.PERSON_YEARS;
import static com.puresol.coding.metrics.cocomo.CoCoMoEvaluatorParameter.SALARY;
import static com.puresol.coding.metrics.cocomo.CoCoMoEvaluatorParameter.SCHEDULED_MONTH;
import static com.puresol.coding.metrics.cocomo.CoCoMoEvaluatorParameter.SCHEDULED_YEARS;
import static com.puresol.coding.metrics.cocomo.CoCoMoEvaluatorParameter.TEAM_SIZE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.puresol.coding.evaluation.api.MetricFileResults;
import com.puresol.utils.math.GeneralValue;
import com.puresol.utils.math.Money;
import com.puresol.utils.math.Parameter;
import com.puresol.utils.math.Value;

public class CoCoMoFileResults extends CoCoMoResults implements
		MetricFileResults {

	private static final long serialVersionUID = 7272355142441159285L;

	public CoCoMoFileResults() {
		super();
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return ALL;
	}

	@Override
	public List<Map<String, Value<?>>> getValues() {
		Map<String, Value<?>> row = new HashMap<String, Value<?>>();
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
		row.put(COMPLEXITY.getName(), new GeneralValue<Complexity>(
				getComplexity(), COMPLEXITY));
		row.put(C1.getName(), new GeneralValue<Double>(getC1(), C1));
		row.put(C2.getName(), new GeneralValue<Double>(getC2(), C2));
		row.put(C3.getName(), new GeneralValue<Double>(getC3(), C3));

		List<Map<String, Value<?>>> values = new ArrayList<Map<String, Value<?>>>();
		values.add(row);
		return values;
	}

}
