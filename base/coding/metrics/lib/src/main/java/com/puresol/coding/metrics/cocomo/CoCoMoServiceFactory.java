package com.puresol.coding.metrics.cocomo;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.evaluation.api.AbstractEvaluatorFactory;
import com.puresol.coding.evaluation.api.Evaluator;
import com.puresol.coding.evaluation.api.QualityCharacteristic;
import com.puresol.coding.metrics.sloc.SLOCEvaluator;
import com.puresol.utils.math.Parameter;

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
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return new ArrayList<QualityCharacteristic>();
	}

	@Override
	public CoCoMoEvaluator create(AnalysisRun analysisRun, HashIdFileTree path) {
		return new CoCoMoEvaluator(analysisRun, path);
	}

	@Override
	public List<Class<? extends Evaluator>> getDependencies() {
		List<Class<? extends Evaluator>> dependencies = new ArrayList<Class<? extends Evaluator>>();
		dependencies.add(SLOCEvaluator.class);
		return dependencies;
	}

	@Override
	public Class<? extends Evaluator> getEvaluatorClass() {
		return CoCoMoEvaluator.class;
	}

	@Override
	public List<Parameter<?>> getParameters() {
		return CoCoMoEvaluatorParameter.ALL;
	}
}
