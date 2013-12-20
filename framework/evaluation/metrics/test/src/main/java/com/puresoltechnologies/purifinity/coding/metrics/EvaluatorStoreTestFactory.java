package com.puresoltechnologies.purifinity.coding.metrics;

import java.util.HashMap;
import java.util.Map;

import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.EvaluatorStoreFactory;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc.SLOCEvaluator;
import com.puresoltechnologies.purifinity.store.fs.metrics.SLOCEvaluatorStore;

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
