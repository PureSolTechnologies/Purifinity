package com.puresol.purifinity.coding.metrics.cocomo.intermediate;

import java.util.HashSet;
import java.util.Set;

import com.puresol.commons.math.Parameter;
import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresol.purifinity.coding.evaluation.api.AbstractEvaluatorFactory;
import com.puresol.purifinity.coding.evaluation.api.Evaluator;
import com.puresol.purifinity.coding.evaluation.iso9126.QualityCharacteristic;
import com.puresol.purifinity.coding.metrics.sloc.SLOCEvaluator;

public class IntermediateCoCoMoServiceFactory extends AbstractEvaluatorFactory {

	@Override
	public String getDescription() {
		return IntermediateCoCoMoEvaluator.DESCRIPTION;
	}

	@Override
	public String getName() {
		return IntermediateCoCoMoEvaluator.NAME;
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
	public IntermediateCoCoMoEvaluator create(AnalysisRun analysisRun, HashIdFileTree path) {
		return new IntermediateCoCoMoEvaluator(analysisRun, path);
	}

	@Override
	public Set<Class<? extends Evaluator>> getDependencies() {
		Set<Class<? extends Evaluator>> dependencies = new HashSet<Class<? extends Evaluator>>();
		dependencies.add(SLOCEvaluator.class);
		return dependencies;
	}

	@Override
	public Class<? extends Evaluator> getEvaluatorClass() {
		return IntermediateCoCoMoEvaluator.class;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return IntermediateCoCoMoEvaluatorParameter.ALL;
	}
}
