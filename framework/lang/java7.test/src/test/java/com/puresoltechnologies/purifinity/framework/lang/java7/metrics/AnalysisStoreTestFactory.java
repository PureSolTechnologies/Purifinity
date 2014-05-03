package com.puresoltechnologies.purifinity.framework.lang.java7.metrics;

import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreFactory;

public class AnalysisStoreTestFactory extends AnalysisStoreFactory {

	private static final AnalysisStore INSTANCE = null;

	@Override
	public AnalysisStore getInstance() {
		return INSTANCE;
	}

}
