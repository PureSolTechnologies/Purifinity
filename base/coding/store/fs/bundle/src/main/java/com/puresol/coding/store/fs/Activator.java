package com.puresol.coding.store.fs;

import java.util.Collection;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.puresol.coding.analysis.api.AnalysisStore;
import com.puresol.coding.analysis.api.CodeStore;
import com.puresol.coding.analysis.api.ModuleStore;
import com.puresol.coding.evaluation.api.EvaluatorStore;
import com.puresol.coding.store.fs.analysis.AnalysisStoreImpl;
import com.puresol.coding.store.fs.analysis.CodeStoreImpl;
import com.puresol.coding.store.fs.analysis.ModuleStoreImpl;
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
	registerService(ModuleStore.class, new ModuleStoreImpl());
	registerService(CodeStore.class, new CodeStoreImpl());

	registerService(EvaluatorStore.class, new CoCoMoEvaluatorStore());
	registerService(EvaluatorStore.class, new CodeDepthEvaluatorStore());
	registerService(EvaluatorStore.class, new EntropyEvaluatorStore());
	registerService(EvaluatorStore.class,
		new HalsteadMetricEvaluatorStore());
	registerService(EvaluatorStore.class,
		new MaintainabilityIndexEvaluatorStore());
	registerService(EvaluatorStore.class, new McCabeMetricEvaluatorStore());
	registerService(EvaluatorStore.class,
		new NormalizedMaintainabilityIndexEvaluatorStore());
	registerService(EvaluatorStore.class, new SLOCEvaluatorStore());

	Collection<ServiceReference<EvaluatorStore>> serviceReferences = context
		.getServiceReferences(EvaluatorStore.class, null);
	serviceReferences.size();
    }
}