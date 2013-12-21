package com.puresoltechnologies.purifinity.framework.evaluation.metrics;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.analysis.impl.AnalysisStoreFactory;
import com.puresoltechnologies.purifinity.framework.store.fs.analysis.AnalysisStoreImpl;

public class AnalysisStoreTestFactory extends AnalysisStoreFactory {

	private static final AnalysisStoreImpl INSTANCE = new AnalysisStoreImpl();

	@Override
	public AnalysisStore getInstance() {
		return INSTANCE;
	}

}
