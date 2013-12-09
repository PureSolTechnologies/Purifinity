package com.puresoltechnologies.purifinity.coding.metrics.maintainability;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.HashIdFileTree;
import com.puresoltechnologies.purifinity.coding.evaluation.impl.AbstractEvaluatorFactory;
import com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetricEvaluator;
import com.puresoltechnologies.purifinity.coding.metrics.mccabe.McCabeMetricEvaluator;
import com.puresoltechnologies.purifinity.coding.metrics.sloc.SLOCEvaluator;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;

public class MaintainabilityIndexEvaluatorFactory extends
		AbstractEvaluatorFactory {

	@Override
	public String getDescription() {
		return MaintainabilityIndexEvaluator.DESCRIPTION;
	}

	@Override
	public String getName() {
		return MaintainabilityIndexEvaluator.NAME;
	}

	@Override
	public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return MaintainabilityIndexEvaluator.EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	public MaintainabilityIndexEvaluator create(AnalysisRun analysisRun,
			HashIdFileTree path) {
		return new MaintainabilityIndexEvaluator(analysisRun, path);
	}

	@Override
	public Set<Class<? extends Evaluator>> getDependencies() {
		Set<Class<? extends Evaluator>> dependencies = new HashSet<Class<? extends Evaluator>>();
		dependencies.add(SLOCEvaluator.class);
		dependencies.add(McCabeMetricEvaluator.class);
		dependencies.add(HalsteadMetricEvaluator.class);
		return dependencies;
	}

	@Override
	public Class<? extends Evaluator> getEvaluatorClass() {
		return MaintainabilityIndexEvaluator.class;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return MaintainabilityIndexEvaluatorParameter.ALL_FILE;
	}
}
