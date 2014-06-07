package com.puresoltechnologies.purifinity.client.common.evaluation.metrics.cocomo.intermediate;

import org.eclipse.core.runtime.Status;
import org.eclipse.swt.widgets.Composite;
import org.osgi.framework.Bundle;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.client.common.evaluation.Activator;
import com.puresoltechnologies.purifinity.client.common.server.connectors.BasicCoCoMoEvaluatorConnector;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.intermediate.IntermediateCoCoMoResults;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;

public class IntermediateCoCoMoFileResultComponent extends
		IntermediateCoCoMoResultComponent {

	public IntermediateCoCoMoFileResultComponent(Composite parent,
			AnalysisInformation analyzedSourceCode) {
		super(parent);
		try {
			EvaluatorStore evaluatorStore = BasicCoCoMoEvaluatorConnector
					.getStore();
			IntermediateCoCoMoResults fileResults = (IntermediateCoCoMoResults) evaluatorStore
					.readFileResults(analyzedSourceCode.getHashId());
			setResults(fileResults);
		} catch (EvaluationStoreException e) {
			Activator activator = Activator.getDefault();
			Bundle bundle = activator.getBundle();
			activator.getLog().log(
					new Status(Status.ERROR, bundle.getSymbolicName(),
							"Could not read results.", e));
		}
	}
}
