package com.puresol.coding.store.fs;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleContext;

import com.puresol.coding.analysis.api.AnalysisStore;
import com.puresol.coding.analysis.api.DirectoryStore;
import com.puresol.coding.analysis.api.FileStore;
import com.puresol.coding.evaluation.api.Evaluator;
import com.puresol.coding.evaluation.api.EvaluatorStore;
import com.puresol.coding.metrics.cocomo.CoCoMoEvaluator;
import com.puresol.coding.metrics.codedepth.CodeDepthMetricEvaluator;
import com.puresol.coding.metrics.entropy.EntropyMetricEvaluator;
import com.puresol.coding.metrics.halstead.HalsteadMetricEvaluator;
import com.puresol.coding.metrics.maintainability.MaintainabilityIndexEvaluator;
import com.puresol.coding.metrics.mccabe.McCabeMetricEvaluator;
import com.puresol.coding.metrics.normmaint.NormalizedMaintainabilityIndexEvaluator;
import com.puresol.coding.metrics.sloc.SLOCEvaluator;
import com.puresol.coding.store.fs.analysis.AnalysisStoreImpl;
import com.puresol.coding.store.fs.analysis.DirectoryStoreImpl;
import com.puresol.coding.store.fs.analysis.FileStoreImpl;
import com.puresol.coding.store.fs.metrics.CoCoMoEvaluatorStore;
import com.puresol.coding.store.fs.metrics.CodeDepthEvaluatorStore;
import com.puresol.coding.store.fs.metrics.EntropyEvaluatorStore;
import com.puresol.coding.store.fs.metrics.HalsteadMetricEvaluatorStore;
import com.puresol.coding.store.fs.metrics.MaintainabilityIndexEvaluatorStore;
import com.puresol.coding.store.fs.metrics.McCabeMetricEvaluatorStore;
import com.puresol.coding.store.fs.metrics.NormalizedMaintainabilityIndexEvaluatorStore;
import com.puresol.coding.store.fs.metrics.SLOCEvaluatorStore;
import com.puresol.commons.osgi.AbstractActivator;

public class Activator extends AbstractActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);

		registerService(AnalysisStore.class, new AnalysisStoreImpl());
		registerService(DirectoryStore.class, new DirectoryStoreImpl());
		registerService(FileStore.class, new FileStoreImpl());

		registerEvaluatorStore(new CoCoMoEvaluatorStore(),
				CoCoMoEvaluator.class);
		registerEvaluatorStore(new CodeDepthEvaluatorStore(),
				CodeDepthMetricEvaluator.class);
		registerEvaluatorStore(new EntropyEvaluatorStore(),
				EntropyMetricEvaluator.class);
		registerEvaluatorStore(new HalsteadMetricEvaluatorStore(),
				HalsteadMetricEvaluator.class);
		registerEvaluatorStore(new MaintainabilityIndexEvaluatorStore(),
				MaintainabilityIndexEvaluator.class);
		registerEvaluatorStore(new McCabeMetricEvaluatorStore(),
				McCabeMetricEvaluator.class);
		registerEvaluatorStore(
				new NormalizedMaintainabilityIndexEvaluatorStore(),
				NormalizedMaintainabilityIndexEvaluator.class);
		registerEvaluatorStore(new SLOCEvaluatorStore(), SLOCEvaluator.class);
	}

	private <T extends EvaluatorStore> void registerEvaluatorStore(T instance,
			Class<? extends Evaluator> evaluator) {
		Dictionary<String, String> dictionary = new Hashtable<String, String>();
		dictionary.put("evaluator", evaluator.getName());
		registerService(EvaluatorStore.class, instance, dictionary);
	}
}