package com.puresoltechnologies.purifinity.server.metrics.entropy;

import static com.puresoltechnologies.purifinity.server.metrics.entropy.EntropyMetricEvaluatorParameter.N_DIFF;
import static com.puresoltechnologies.purifinity.server.metrics.entropy.EntropyMetricEvaluatorParameter.N_TOTAL;
import static com.puresoltechnologies.purifinity.server.metrics.entropy.EntropyMetricEvaluatorParameter.R;
import static com.puresoltechnologies.purifinity.server.metrics.entropy.EntropyMetricEvaluatorParameter.RS;
import static com.puresoltechnologies.purifinity.server.metrics.entropy.EntropyMetricEvaluatorParameter.R_NORM;
import static com.puresoltechnologies.purifinity.server.metrics.entropy.EntropyMetricEvaluatorParameter.S;
import static com.puresoltechnologies.purifinity.server.metrics.entropy.EntropyMetricEvaluatorParameter.S_MAX;
import static com.puresoltechnologies.purifinity.server.metrics.entropy.EntropyMetricEvaluatorParameter.S_NORM;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.puresoltechnologies.purifinity.evaluation.domain.metrics.AbstractMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;

public abstract class AbstractEntropyResults extends AbstractMetrics {

    private static final long serialVersionUID = 2241293026079625803L;

    public AbstractEntropyResults(Date time) {
	super(EntropyMetric.ID, EntropyMetric.PLUGIN_VERSION, time);
    }

    protected Map<String, MetricValue<?>> convertToRow(EntropyResult result) {
	EntropyMetricResult entropy = result.getEntropyMetricResult();
	Map<String, MetricValue<?>> row = new HashMap<>();
	row.put(N_DIFF.getName(), new MetricValue<Integer>(entropy.getNDiff(),
		N_DIFF));

	row.put(N_TOTAL.getName(), new MetricValue<Integer>(
		entropy.getNTotal(), N_TOTAL));
	row.put(S.getName(), new MetricValue<Double>(entropy.getEntropy(), S));
	row.put(S_MAX.getName(),
		new MetricValue<Double>(entropy.getMaxEntropy(), S_MAX));
	row.put(S_NORM.getName(),
		new MetricValue<Double>(entropy.getNormEntropy(), S_NORM));
	row.put(RS.getName(),
		new MetricValue<Double>(entropy.getEntropyRedundancy(), RS));
	row.put(R.getName(),
		new MetricValue<Double>(entropy.getRedundancy(), R));
	row.put(R_NORM.getName(),
		new MetricValue<Double>(entropy.getNormalizedRedundancy(),
			R_NORM));
	return row;
    }
}
