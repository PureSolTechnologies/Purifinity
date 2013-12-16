package com.puresoltechnologies.purifinity.coding.metrics;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisStore;
import com.puresoltechnologies.purifinity.coding.analysis.impl.AnalysisStoreFactory;
import com.puresoltechnologies.purifinity.store.fs.analysis.AnalysisStoreImpl;

public class AnalysisStoreTestFactory extends AnalysisStoreFactory {

	private static final AnalysisStoreImpl INSTANCE = new AnalysisStoreImpl();

	@Override
	public AnalysisStore getInstance() {
		return INSTANCE;
	}

}
