package com.puresoltechnologies.purifinity.server.metrics.cocomo.basic;

import static com.puresoltechnologies.purifinity.server.metrics.cocomo.basic.BasicCoCoMoEvaluatorParameter.ALL;
import static com.puresoltechnologies.purifinity.server.metrics.cocomo.basic.BasicCoCoMoEvaluatorParameter.C1;
import static com.puresoltechnologies.purifinity.server.metrics.cocomo.basic.BasicCoCoMoEvaluatorParameter.C2;
import static com.puresoltechnologies.purifinity.server.metrics.cocomo.basic.BasicCoCoMoEvaluatorParameter.C3;
import static com.puresoltechnologies.purifinity.server.metrics.cocomo.basic.BasicCoCoMoEvaluatorParameter.COSTS;
import static com.puresoltechnologies.purifinity.server.metrics.cocomo.basic.BasicCoCoMoEvaluatorParameter.KSLOC;
import static com.puresoltechnologies.purifinity.server.metrics.cocomo.basic.BasicCoCoMoEvaluatorParameter.PERSON_MONTH;
import static com.puresoltechnologies.purifinity.server.metrics.cocomo.basic.BasicCoCoMoEvaluatorParameter.PERSON_YEARS;
import static com.puresoltechnologies.purifinity.server.metrics.cocomo.basic.BasicCoCoMoEvaluatorParameter.SALARY;
import static com.puresoltechnologies.purifinity.server.metrics.cocomo.basic.BasicCoCoMoEvaluatorParameter.SCHEDULED_MONTH;
import static com.puresoltechnologies.purifinity.server.metrics.cocomo.basic.BasicCoCoMoEvaluatorParameter.SCHEDULED_YEARS;
import static com.puresoltechnologies.purifinity.server.metrics.cocomo.basic.BasicCoCoMoEvaluatorParameter.TEAM_SIZE;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.commons.money.Money;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;
import com.puresoltechnologies.versioning.Version;

public class BasicCoCoMoDirectoryResults extends BasicCoCoMoResults implements DirectoryMetrics {

    private static final long serialVersionUID = 7272355142441159285L;

    private final HashId hashId;

    public BasicCoCoMoDirectoryResults(String evaluatorId, Version evaluatorVersion, HashId hashId, Date time) {
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
	Map<String, MetricValue<?>> row = new HashMap<String, MetricValue<?>>();

	row.put(KSLOC.getName(), new MetricValue<Double>(getKsloc(), KSLOC));
	row.put(PERSON_MONTH.getName(), new MetricValue<Double>(getPersonMonth(), PERSON_MONTH));
	row.put(PERSON_YEARS.getName(), new MetricValue<Double>(getPersonYears(), PERSON_YEARS));
	row.put(SCHEDULED_MONTH.getName(), new MetricValue<Double>(getScheduledMonth(), SCHEDULED_MONTH));
	row.put(SCHEDULED_YEARS.getName(), new MetricValue<Double>(getScheduledYears(), SCHEDULED_YEARS));
	row.put(TEAM_SIZE.getName(), new MetricValue<Double>(getTeamSize(), TEAM_SIZE));
	row.put(COSTS.getName(), new MetricValue<Double>(getEstimatedCosts(), COSTS));

	row.put(SALARY.getName(), new MetricValue<Money>(getMoney(), SALARY));
	row.put(C1.getName(), new MetricValue<Double>(getC1(), C1));
	row.put(C2.getName(), new MetricValue<Double>(getC2(), C2));
	row.put(C3.getName(), new MetricValue<Double>(getC3(), C3));

	return row;
    }

}
