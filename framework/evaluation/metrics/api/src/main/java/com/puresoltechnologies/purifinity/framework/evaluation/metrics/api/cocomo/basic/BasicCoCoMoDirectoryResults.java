package com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.basic;

import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.basic.BasicCoCoMoEvaluatorParameter.ALL;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.basic.BasicCoCoMoEvaluatorParameter.C1;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.basic.BasicCoCoMoEvaluatorParameter.C2;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.basic.BasicCoCoMoEvaluatorParameter.C3;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.basic.BasicCoCoMoEvaluatorParameter.CODE_RANGE_NAME;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.basic.BasicCoCoMoEvaluatorParameter.CODE_RANGE_TYPE;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.basic.BasicCoCoMoEvaluatorParameter.COMPLEXITY;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.basic.BasicCoCoMoEvaluatorParameter.COSTS;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.basic.BasicCoCoMoEvaluatorParameter.KSLOC;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.basic.BasicCoCoMoEvaluatorParameter.PERSON_MONTH;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.basic.BasicCoCoMoEvaluatorParameter.PERSON_YEARS;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.basic.BasicCoCoMoEvaluatorParameter.SALARY;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.basic.BasicCoCoMoEvaluatorParameter.SCHEDULED_MONTH;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.basic.BasicCoCoMoEvaluatorParameter.SCHEDULED_YEARS;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.basic.BasicCoCoMoEvaluatorParameter.SOURCE_CODE_LOCATION;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.basic.BasicCoCoMoEvaluatorParameter.TEAM_SIZE;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.puresoltechnologies.commons.math.GeneralValue;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.commons.math.money.Money;
import com.puresoltechnologies.parsers.api.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricDirectoryResults;

public class BasicCoCoMoDirectoryResults extends BasicCoCoMoResults implements
		MetricDirectoryResults {

	private static final long serialVersionUID = 7272355142441159285L;

	private final SourceCodeLocation codeLocation;

	public BasicCoCoMoDirectoryResults(SourceCodeLocation codeLocation) {
		super();
		this.codeLocation = codeLocation;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return ALL;
	}

	@Override
	public Map<String, Value<?>> getValues() {
		Map<String, Value<?>> row = new HashMap<String, Value<?>>();

		row.put(SOURCE_CODE_LOCATION.getName(),
				new GeneralValue<SourceCodeLocation>(codeLocation,
						SOURCE_CODE_LOCATION));
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
		row.put(COMPLEXITY.getName(), new GeneralValue<SoftwareProject>(
				getComplexity(), COMPLEXITY));
		row.put(C1.getName(), new GeneralValue<Double>(getC1(), C1));
		row.put(C2.getName(), new GeneralValue<Double>(getC2(), C2));
		row.put(C3.getName(), new GeneralValue<Double>(getC3(), C3));

		return row;
	}

}
