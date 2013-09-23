package com.puresol.purifinity.coding.metrics;

import java.util.HashMap;
import java.util.Map;

import com.puresol.purifinity.coding.evaluation.api.Evaluator;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStore;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStoreFactory;

public class EvaluatorStoreTestFactory extends EvaluatorStoreFactory {

	private static final Map<Class<? extends Evaluator>, EvaluatorStore> INSTANCES = new HashMap<>();

	@Override
	public EvaluatorStore createInstance(Class<? extends Evaluator> clazz) {
		return INSTANCES.get(clazz);
	}

}
