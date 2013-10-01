package com.puresol.purifinity.coding.metrics.cocomo;

import java.util.HashSet;
import java.util.Set;

import com.puresol.commons.math.Parameter;
import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresol.purifinity.coding.evaluation.api.AbstractEvaluatorFactory;
import com.puresol.purifinity.coding.evaluation.api.Evaluator;
import com.puresol.purifinity.coding.evaluation.iso9126.QualityCharacteristic;
import com.puresol.purifinity.coding.metrics.sloc.SLOCEvaluator;

public class CoCoMoServiceFactory extends AbstractEvaluatorFactory {

	@Override
	public String getDescription() {
		return CoCoMoEvaluator.DESCRIPTION;
	}

	@Override
	public String getName() {
		return CoCoMoEvaluator.NAME;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * For CoCoMo there is no quality characteristics assigned. It's a pure
	 * economic evaluation.
	 */
	@Override
	public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return new HashSet<QualityCharacteristic>();
	}

	@Override
	public CoCoMoEvaluator create(AnalysisRun analysisRun, HashIdFileTree path) {
		return new CoCoMoEvaluator(analysisRun, path);
	}

	@Override
	public Set<Class<? extends Evaluator>> getDependencies() {
		Set<Class<? extends Evaluator>> dependencies = new HashSet<Class<? extends Evaluator>>();
		dependencies.add(SLOCEvaluator.class);
		return dependencies;
	}

	@Override
	public Class<? extends Evaluator> getEvaluatorClass() {
		return CoCoMoEvaluator.class;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return CoCoMoEvaluatorParameter.ALL;
	}
}
