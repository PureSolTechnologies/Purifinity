package com.puresoltechnologies.purifinity.client.common.evaluation.metrics.cocomo.basic;

import org.eclipse.core.runtime.Status;
import org.eclipse.swt.widgets.Composite;
import org.osgi.framework.Bundle;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.client.common.evaluation.Activator;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.cocomo.basic.BasicCoCoMoEvaluator;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.cocomo.basic.BasicCoCoMoResults;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStoreFactory;

public class BasicCoCoMoFileResultComponent extends BasicCoCoMoResultComponent {

	public BasicCoCoMoFileResultComponent(Composite parent,
			AnalysisInformation analyzedSourceCode) {
		super(parent);
		try {
			EvaluatorStore evaluatorStore = EvaluatorStoreFactory.getFactory()
					.createInstance(BasicCoCoMoEvaluator.class);
			BasicCoCoMoResults fileResults = (BasicCoCoMoResults) evaluatorStore
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
