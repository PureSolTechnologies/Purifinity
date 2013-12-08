package com.puresoltechnologies.purifinity.coding.metrics;

import java.util.HashMap;
import java.util.Map;

import com.puresoltechnologies.purifinity.coding.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.coding.evaluation.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresoltechnologies.purifinity.coding.metrics.sloc.SLOCEvaluator;
import com.puresoltechnologies.purifinity.coding.store.fs.metrics.SLOCEvaluatorStore;

public class EvaluatorStoreTestFactory extends EvaluatorStoreFactory {

	private static final Map<Class<? extends Evaluator>, EvaluatorStore> INSTANCES = new HashMap<>();
	static {
		INSTANCES.put(SLOCEvaluator.class, new SLOCEvaluatorStore());
	}

	@Override
	public EvaluatorStore createInstance(Class<? extends Evaluator> clazz) {
		return INSTANCES.get(clazz);
	}

}
