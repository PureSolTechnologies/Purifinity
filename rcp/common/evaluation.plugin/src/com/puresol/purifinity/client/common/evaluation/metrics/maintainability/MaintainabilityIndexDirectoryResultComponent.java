package com.puresol.purifinity.client.common.evaluation.metrics.maintainability;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.analysis.api.CodeRangeType;
import com.puresol.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStore;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.purifinity.coding.metrics.maintainability.MaintainabilityIndexEvaluator;
import com.puresol.purifinity.coding.metrics.maintainability.MaintainabilityIndexFileResult;
import com.puresol.purifinity.coding.metrics.maintainability.MaintainabilityIndexFileResults;

public class MaintainabilityIndexDirectoryResultComponent extends Composite {

	public MaintainabilityIndexDirectoryResultComponent(Composite parent,
			int style, AnalysisRun analysisRun, HashIdFileTree directory) {
		super(parent, style);

		setLayout(new FillLayout());

		EvaluatorStore evaluatorStore = EvaluatorStoreFactory.getFactory()
				.createInstance(MaintainabilityIndexEvaluator.class);

		Map<Double, String> maintainability = new HashMap<Double, String>();
		for (HashIdFileTree child : directory.getChildren()) {
			if (child.isFile()) {
				MaintainabilityIndexFileResults childResults = (MaintainabilityIndexFileResults) evaluatorStore
						.readFileResults(child.getHashId());
				for (MaintainabilityIndexFileResult result : childResults
						.getResults()) {
					if (result.getCodeRangeType() == CodeRangeType.FILE) {
						maintainability.put(result
								.getMaintainabilityIndexResult().getMI(), child
								.getName());
					}
				}
			}
		}
	}

}
