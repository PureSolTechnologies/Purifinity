package com.puresol.purifinity.coding.metrics;

import com.puresol.purifinity.coding.analysis.api.AnalysisStore;
import com.puresol.purifinity.coding.analysis.api.AnalysisStoreFactory;
import com.puresol.purifinity.coding.store.fs.analysis.AnalysisStoreImpl;

public class AnalysisStoreTestFactory extends AnalysisStoreFactory {

	private static final AnalysisStoreImpl INSTANCE = new AnalysisStoreImpl();

	@Override
	public AnalysisStore getInstance() {
		return INSTANCE;
	}

}
