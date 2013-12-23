package com.puresoltechnologies.purifinity.framework.evaluation.metrics;

import java.util.HashMap;
import java.util.Map;

import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc.SLOCEvaluator;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStoreFactory;

public class EvaluatorStoreTestFactory extends EvaluatorStoreFactory {

	private static final Map<Class<? extends Evaluator>, EvaluatorStore> INSTANCES = new HashMap<>();
	static {
		INSTANCES.put(SLOCEvaluator.class, null);
	}

	@Override
	public EvaluatorStore createInstance(Class<? extends Evaluator> clazz) {
		return INSTANCES.get(clazz);
	}

}
