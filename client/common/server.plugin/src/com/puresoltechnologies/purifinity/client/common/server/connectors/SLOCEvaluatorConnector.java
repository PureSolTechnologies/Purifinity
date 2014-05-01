package com.puresoltechnologies.purifinity.client.common.server.connectors;

import java.util.UUID;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.client.common.server.Activator;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricFileResults;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;

public class SLOCEvaluatorConnector implements EvaluatorStore {

	public static EvaluatorStore getStore() {
		BundleContext bundleContext = Activator.getDefault().getBundle()
				.getBundleContext();
		ServiceReference<SLOCEvaluatorConnector> serviceReference = bundleContext
				.getServiceReference(SLOCEvaluatorConnector.class);
		return bundleContext.getService(serviceReference);
	}

	@Override
	public String getStoreName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasFileResults(HashId hashId)
			throws EvaluationStoreException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasDirectoryResults(HashId hashId)
			throws EvaluationStoreException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasProjectResults(UUID analysisRunUUID)
			throws EvaluationStoreException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void storeFileResults(CodeAnalysis codeAnalysis,
			Evaluator evaluator, MetricFileResults results)
			throws EvaluationStoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public void storeDirectoryResults(AnalysisFileTree directory,
			Evaluator evaluator, MetricDirectoryResults results)
			throws EvaluationStoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public void storeProjectResults(UUID analysisRunUUID, Evaluator evaluator,
			AnalysisFileTree directory, MetricDirectoryResults results)
			throws EvaluationStoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public MetricFileResults readFileResults(HashId hashId)
			throws EvaluationStoreException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MetricDirectoryResults readDirectoryResults(HashId hashId)
			throws EvaluationStoreException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MetricDirectoryResults readProjectResults(UUID analysisRunUUID)
			throws EvaluationStoreException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeMetricsInBigTable(CodeAnalysis codeAnalysis,
			Evaluator evaluator, MetricFileResults results) {
		// TODO Auto-generated method stub

	}

	@Override
	public void storeMetricsInBigTable(AnalysisFileTree directory,
			Evaluator evaluator, MetricDirectoryResults results) {
		// TODO Auto-generated method stub

	}
}