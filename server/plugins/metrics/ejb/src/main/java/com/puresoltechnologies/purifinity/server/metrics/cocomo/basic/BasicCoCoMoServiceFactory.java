package com.puresoltechnologies.purifinity.server.metrics.cocomo.basic;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.basic.BasicCoCoMoEvaluatorParameter;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluatorFactory;
import com.puresoltechnologies.purifinity.server.metrics.sloc.SLOCEvaluator;

public class BasicCoCoMoServiceFactory extends AbstractEvaluatorFactory {

	@Override
	public String getDescription() {
		return BasicCoCoMoEvaluator.DESCRIPTION;
	}

	@Override
	public String getName() {
		return BasicCoCoMoEvaluator.NAME;
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
	public BasicCoCoMoEvaluator create() {
		return new BasicCoCoMoEvaluator();
	}

	@Override
	public Set<Class<? extends Evaluator>> getDependencies() {
		Set<Class<? extends Evaluator>> dependencies = new HashSet<Class<? extends Evaluator>>();
		dependencies.add(SLOCEvaluator.class);
		return dependencies;
	}

	@Override
	public Class<? extends Evaluator> getEvaluatorClass() {
		return BasicCoCoMoEvaluator.class;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return BasicCoCoMoEvaluatorParameter.ALL;
	}
}