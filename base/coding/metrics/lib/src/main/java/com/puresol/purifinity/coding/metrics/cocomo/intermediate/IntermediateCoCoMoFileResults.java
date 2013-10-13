package com.puresol.purifinity.coding.metrics.cocomo.intermediate;

import static com.puresol.purifinity.coding.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.AI;
import static com.puresol.purifinity.coding.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.ALL;
import static com.puresol.purifinity.coding.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.BI;
import static com.puresol.purifinity.coding.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.CI;
import static com.puresol.purifinity.coding.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.CODE_RANGE_NAME;
import static com.puresol.purifinity.coding.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.CODE_RANGE_TYPE;
import static com.puresol.purifinity.coding.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.COSTS;
import static com.puresol.purifinity.coding.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.DI;
import static com.puresol.purifinity.coding.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.KSLOC;
import static com.puresol.purifinity.coding.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.PERSON_MONTH;
import static com.puresol.purifinity.coding.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.PERSON_YEARS;
import static com.puresol.purifinity.coding.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.PROJECT;
import static com.puresol.purifinity.coding.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.SALARY;
import static com.puresol.purifinity.coding.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.SCHEDULED_MONTH;
import static com.puresol.purifinity.coding.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.SCHEDULED_YEARS;
import static com.puresol.purifinity.coding.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.SOURCE_CODE_LOCATION;
import static com.puresol.purifinity.coding.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.TEAM_SIZE;

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

public class IntermediateCoCoMoFileResults extends IntermediateCoCoMoResults
		implements MetricFileResults {

	private static final long serialVersionUID = 7272355142441159285L;

	private final CodeLocation codeLocation;

	public IntermediateCoCoMoFileResults(CodeLocation codeLocation) {
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
		row.put(PROJECT.getName(), new GeneralValue<SoftwareProject>(
				getProject(), PROJECT));
		row.put(AI.getName(),
				new GeneralValue<Double>(getProject().getAi(), AI));
		row.put(BI.getName(),
				new GeneralValue<Double>(getProject().getBi(), BI));
		row.put(CI.getName(),
				new GeneralValue<Double>(getProject().getCi(), CI));
		row.put(DI.getName(),
				new GeneralValue<Double>(getProject().getDi(), DI));

		List<Map<String, Value<?>>> values = new ArrayList<Map<String, Value<?>>>();
		values.add(row);
		return values;
	}
}