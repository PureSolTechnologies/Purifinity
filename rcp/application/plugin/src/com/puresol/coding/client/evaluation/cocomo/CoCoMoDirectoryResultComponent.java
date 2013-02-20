package com.puresol.coding.client.evaluation.cocomo;

import org.eclipse.swt.widgets.Composite;

import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.evaluation.api.EvaluatorStore;
import com.puresol.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.coding.metrics.cocomo.CoCoMoEvaluator;
import com.puresol.coding.metrics.cocomo.CoCoMoValueSet;

public class CoCoMoDirectoryResultComponent extends CoCoMoResultComponent {

	public CoCoMoDirectoryResultComponent(Composite parent, int style,
			HashIdFileTree directory) {
		super(parent, style);

		EvaluatorStore evaluatorStore = EvaluatorStoreFactory.getFactory()
				.createInstance(CoCoMoEvaluator.class);

		CoCoMoValueSet directoryResults = (CoCoMoValueSet) evaluatorStore
				.readDirectoryResults(directory.getHashId());

		setResults(directoryResults);
	}

}
