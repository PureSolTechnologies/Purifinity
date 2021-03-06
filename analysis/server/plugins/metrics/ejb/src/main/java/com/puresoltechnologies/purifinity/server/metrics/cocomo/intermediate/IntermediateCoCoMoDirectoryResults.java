package com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate;

import static com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.AI;
import static com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.ALL;
import static com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.BI;
import static com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.CI;
import static com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.COSTS;
import static com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.DI;
import static com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.KSLOC;
import static com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.PERSON_MONTH;
import static com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.PERSON_YEARS;
import static com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.SALARY;
import static com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.SCHEDULED_MONTH;
import static com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.SCHEDULED_YEARS;
import static com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.TEAM_SIZE;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.commons.money.Money;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;
import com.puresoltechnologies.versioning.Version;

public class IntermediateCoCoMoDirectoryResults extends IntermediateCoCoMoResults implements DirectoryMetrics {

    private static final long serialVersionUID = 7272355142441159285L;

    private final HashId hashId;

    public IntermediateCoCoMoDirectoryResults(String evaluatorId, Version evaluatorVersion, HashId hashId, Date time) {
	super(evaluatorId, evaluatorVersion, time);
	this.hashId = hashId;
    }

    @Override
    public HashId getHashId() {
	return hashId;
    }

    @Override
    public MetricParameter<?>[] getParameters() {
	return ALL;
    }

    @Override
    public Map<String, MetricValue<?>> getValues() {
	Map<String, MetricValue<?>> row = new HashMap<>();

	row.put(KSLOC.getName(), new MetricValue<Double>(getKsloc(), KSLOC));
	row.put(PERSON_MONTH.getName(), new MetricValue<Double>(getPersonMonth(), PERSON_MONTH));
	row.put(PERSON_YEARS.getName(), new MetricValue<Double>(getPersonYears(), PERSON_YEARS));
	row.put(SCHEDULED_MONTH.getName(), new MetricValue<Double>(getScheduledMonth(), SCHEDULED_MONTH));
	row.put(SCHEDULED_YEARS.getName(), new MetricValue<Double>(getScheduledYears(), SCHEDULED_YEARS));
	row.put(TEAM_SIZE.getName(), new MetricValue<Double>(getTeamSize(), TEAM_SIZE));
	row.put(COSTS.getName(), new MetricValue<Double>(getEstimatedCosts(), COSTS));

	row.put(SALARY.getName(), new MetricValue<Money>(getMoney(), SALARY));
	row.put(AI.getName(), new MetricValue<Double>(getProject().getAi(), AI));
	row.put(BI.getName(), new MetricValue<Double>(getProject().getBi(), BI));
	row.put(CI.getName(), new MetricValue<Double>(getProject().getCi(), CI));
	row.put(DI.getName(), new MetricValue<Double>(getProject().getDi(), DI));

	return row;
    }

}
