package com.puresoltechnologies.purifinity.server.metrics.halstead;

import static com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadMetricEvaluatorParameter.DIFFERENT_OPERANDS;
import static com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadMetricEvaluatorParameter.DIFFERENT_OPERATORS;
import static com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadMetricEvaluatorParameter.DIFFICULTY;
import static com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadMetricEvaluatorParameter.ESTIMATED_BUGS;
import static com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadMetricEvaluatorParameter.HALSTEAD_LENGTH;
import static com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadMetricEvaluatorParameter.HALSTEAD_VOLUMNE;
import static com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadMetricEvaluatorParameter.IMPLEMENTATION_EFFORT;
import static com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadMetricEvaluatorParameter.IMPLEMENTATION_TIME;
import static com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadMetricEvaluatorParameter.PROGRAM_LENGTH;
import static com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadMetricEvaluatorParameter.PROGRAM_LEVEL;
import static com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadMetricEvaluatorParameter.TOTAL_OPERANDS;
import static com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadMetricEvaluatorParameter.TOTAL_OPERATORS;
import static com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadMetricEvaluatorParameter.VOCABULARY_SIZE;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.puresoltechnologies.purifinity.evaluation.domain.metrics.AbstractMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;

public abstract class AbstractHalsteadResults extends AbstractMetrics {

    private static final long serialVersionUID = 7911283186514371553L;

    public AbstractHalsteadResults(String evaluatorId, Date time) {
	super(evaluatorId, time);
    }

    protected Map<String, MetricValue<?>> convertToRow(
	    HalsteadMetricResult result) {
	Map<String, MetricValue<?>> row = new HashMap<>();
	if (result != null) {
	    HalsteadResult halstead = result.getHalsteadResult();
	    row.put(DIFFERENT_OPERATORS.getName(), new MetricValue<Integer>(
		    halstead.getDifferentOperators(), DIFFERENT_OPERATORS));
	    row.put(DIFFERENT_OPERANDS.getName(), new MetricValue<Integer>(
		    halstead.getDifferentOperands(), DIFFERENT_OPERANDS));
	    row.put(TOTAL_OPERATORS.getName(), new MetricValue<Integer>(
		    halstead.getTotalOperators(), TOTAL_OPERATORS));
	    row.put(TOTAL_OPERANDS.getName(),
		    new MetricValue<Integer>(halstead.getTotalOperands(),
			    TOTAL_OPERANDS));
	    row.put(VOCABULARY_SIZE.getName(), new MetricValue<Integer>(
		    halstead.getVocabularySize(), VOCABULARY_SIZE));
	    row.put(PROGRAM_LENGTH.getName(),
		    new MetricValue<Integer>(halstead.getProgramLength(),
			    PROGRAM_LENGTH));
	    row.put(HALSTEAD_LENGTH.getName(),
		    new MetricValue<Double>(halstead.getHalsteadLength(),
			    HALSTEAD_LENGTH));
	    row.put(HALSTEAD_VOLUMNE.getName(), new MetricValue<Double>(
		    halstead.getHalsteadVolume(), HALSTEAD_VOLUMNE));
	    row.put(DIFFICULTY.getName(),
		    new MetricValue<Double>(halstead.getDifficulty(),
			    DIFFICULTY));
	    row.put(PROGRAM_LEVEL.getName(),
		    new MetricValue<Double>(halstead.getProgramLevel(),
			    PROGRAM_LEVEL));
	    row.put(IMPLEMENTATION_EFFORT.getName(), new MetricValue<Double>(
		    halstead.getImplementationEffort(), IMPLEMENTATION_EFFORT));
	    row.put(IMPLEMENTATION_TIME.getName(), new MetricValue<Double>(
		    halstead.getImplementationTime(), IMPLEMENTATION_TIME));
	    row.put(ESTIMATED_BUGS.getName(),
		    new MetricValue<Double>(halstead.getEstimatedBugs(),
			    ESTIMATED_BUGS));
	}
	return row;
    }

}
