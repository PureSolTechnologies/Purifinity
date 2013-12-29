package com.puresoltechnologies.purifinity.framework.store.db;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleContext;

import com.puresoltechnologies.commons.osgi.AbstractActivator;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.cocomo.basic.BasicCoCoMoEvaluator;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluator;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.codedepth.CodeDepthMetricEvaluator;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.entropy.EntropyMetricEvaluator;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.halstead.HalsteadMetricEvaluator;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.maintainability.MaintainabilityIndexEvaluator;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.mccabe.McCabeMetricEvaluator;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.normmaint.NormalizedMaintainabilityIndexEvaluator;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc.SLOCEvaluator;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.DirectoryStore;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.framework.store.api.FileStore;
import com.puresoltechnologies.purifinity.framework.store.db.analysis.AnalysisStoreImpl;
import com.puresoltechnologies.purifinity.framework.store.db.analysis.DirectoryStoreImpl;
import com.puresoltechnologies.purifinity.framework.store.db.analysis.FileStoreImpl;
import com.puresoltechnologies.purifinity.framework.store.db.metrics.BasicCoCoMoEvaluatorStore;
import com.puresoltechnologies.purifinity.framework.store.db.metrics.CodeDepthEvaluatorStore;
import com.puresoltechnologies.purifinity.framework.store.db.metrics.EntropyEvaluatorStore;
import com.puresoltechnologies.purifinity.framework.store.db.metrics.HalsteadMetricEvaluatorStore;
import com.puresoltechnologies.purifinity.framework.store.db.metrics.IntermediateCoCoMoEvaluatorStore;
import com.puresoltechnologies.purifinity.framework.store.db.metrics.MaintainabilityIndexEvaluatorStore;
import com.puresoltechnologies.purifinity.framework.store.db.metrics.McCabeMetricEvaluatorStore;
import com.puresoltechnologies.purifinity.framework.store.db.metrics.NormalizedMaintainabilityIndexEvaluatorStore;
import com.puresoltechnologies.purifinity.framework.store.db.metrics.SLOCEvaluatorStore;

public class Activator extends AbstractActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);

		CassandraConnection.connect();
		TitanConnection.connect();

		registerService(AnalysisStore.class, new AnalysisStoreImpl());
		registerService(DirectoryStore.class, new DirectoryStoreImpl());
		registerService(FileStore.class, new FileStoreImpl());

		registerEvaluatorStore(new BasicCoCoMoEvaluatorStore(),
				BasicCoCoMoEvaluator.class);
		registerEvaluatorStore(new CodeDepthEvaluatorStore(),
				CodeDepthMetricEvaluator.class);
		registerEvaluatorStore(new EntropyEvaluatorStore(),
				EntropyMetricEvaluator.class);
		registerEvaluatorStore(new HalsteadMetricEvaluatorStore(),
				HalsteadMetricEvaluator.class);
		registerEvaluatorStore(new IntermediateCoCoMoEvaluatorStore(),
				IntermediateCoCoMoEvaluator.class);
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

	@Override
	public void stop(BundleContext context) throws Exception {
		TitanConnection.disconnect();
		CassandraConnection.disconnect();
		super.stop(context);
	}
}