package com.puresoltechnologies.purifinity.coding.metrics.cocomo.intermediate;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.purifinity.coding.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresoltechnologies.purifinity.coding.evaluation.api.AbstractEvaluatorFactory;
import com.puresoltechnologies.purifinity.coding.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.coding.evaluation.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.coding.metrics.sloc.SLOCEvaluator;

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
