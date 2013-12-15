package com.puresoltechnologies.purifinity.client.common.evaluation.metrics.cocomo.basic;

import org.eclipse.swt.widgets.Composite;

import com.puresoltechnologies.purifinity.analysis.api.AnalyzedCode;
import com.puresoltechnologies.purifinity.coding.evaluation.impl.EvaluatorStoreFactory;
import com.puresoltechnologies.purifinity.coding.metrics.cocomo.basic.BasicCoCoMoEvaluator;
import com.puresoltechnologies.purifinity.coding.metrics.cocomo.basic.BasicCoCoMoResults;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorStore;

public class BasicCoCoMoFileResultComponent extends BasicCoCoMoResultComponent {

	public BasicCoCoMoFileResultComponent(Composite parent,
			AnalyzedCode analyzedSourceCode) {
		super(parent);

		EvaluatorStore evaluatorStore = EvaluatorStoreFactory.getFactory()
				.createInstance(BasicCoCoMoEvaluator.class);

		BasicCoCoMoResults fileResults = (BasicCoCoMoResults) evaluatorStore
				.readFileResults(analyzedSourceCode.getHashId());

		setResults(fileResults);
	}
}
