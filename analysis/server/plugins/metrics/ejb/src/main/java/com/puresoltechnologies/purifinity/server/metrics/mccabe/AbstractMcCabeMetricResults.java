package com.puresoltechnologies.purifinity.server.metrics.mccabe;

import static com.puresoltechnologies.purifinity.server.metrics.mccabe.McCabeMetricEvaluatorParameter.VG;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.puresoltechnologies.purifinity.evaluation.domain.metrics.AbstractMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;
import com.puresoltechnologies.versioning.Version;

public abstract class AbstractMcCabeMetricResults extends AbstractMetrics {

    private static final long serialVersionUID = 8270749745560040672L;

    public AbstractMcCabeMetricResults(String evaluatorId,
	    Version evaluatorVersion, Date time) {
	super(evaluatorId, evaluatorVersion, time);
    }

    protected Map<String, MetricValue<?>> convertToRow(McCabeMetricResult result) {
	Map<String, MetricValue<?>> row = new HashMap<>();
	if (result != null) {
	    row.put(VG.getName(),
		    new MetricValue<Integer>(result.getCyclomaticComplexity(),
			    VG));
	}
	return row;
    }

}
