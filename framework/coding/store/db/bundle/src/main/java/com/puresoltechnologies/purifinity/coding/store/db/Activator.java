package com.puresoltechnologies.purifinity.coding.store.db;

import org.osgi.framework.BundleContext;

import com.puresoltechnologies.commons.osgi.AbstractActivator;

public class Activator extends AbstractActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);

		// registerService(AnalysisStore.class, new AnalysisStoreImpl());
		// registerService(DirectoryStore.class, new DirectoryStoreImpl());
		// registerService(FileStore.class, new FileStoreImpl());
		//
		// registerEvaluatorStore(new BasicCoCoMoEvaluatorStore(),
		// BasicCoCoMoEvaluator.class);
		// registerEvaluatorStore(new CodeDepthEvaluatorStore(),
		// CodeDepthMetricEvaluator.class);
		// registerEvaluatorStore(new EntropyEvaluatorStore(),
		// EntropyMetricEvaluator.class);
		// registerEvaluatorStore(new HalsteadMetricEvaluatorStore(),
		// HalsteadMetricEvaluator.class);
		// registerEvaluatorStore(new IntermediateCoCoMoEvaluatorStore(),
		// IntermediateCoCoMoEvaluator.class);
		// registerEvaluatorStore(new MaintainabilityIndexEvaluatorStore(),
		// MaintainabilityIndexEvaluator.class);
		// registerEvaluatorStore(new McCabeMetricEvaluatorStore(),
		// McCabeMetricEvaluator.class);
		// registerEvaluatorStore(
		// new NormalizedMaintainabilityIndexEvaluatorStore(),
		// NormalizedMaintainabilityIndexEvaluator.class);
		// registerEvaluatorStore(new SLOCEvaluatorStore(),
		// SLOCEvaluator.class);
	}

	// private <T extends EvaluatorStore> void registerEvaluatorStore(T
	// instance,
	// Class<? extends Evaluator> evaluator) {
	// Dictionary<String, String> dictionary = new Hashtable<String, String>();
	// dictionary.put("evaluator", evaluator.getName());
	// registerService(EvaluatorStore.class, instance, dictionary);
	// }
}