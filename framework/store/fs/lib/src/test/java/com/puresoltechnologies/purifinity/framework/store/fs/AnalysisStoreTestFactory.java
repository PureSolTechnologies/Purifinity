package com.puresoltechnologies.purifinity.framework.store.fs;

import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreFactory;
import com.puresoltechnologies.purifinity.framework.store.fs.analysis.AnalysisStoreImpl;

public class AnalysisStoreTestFactory extends AnalysisStoreFactory {

	private static final AnalysisStoreImpl INSTANCE = new AnalysisStoreImpl();

	@Override
	public AnalysisStore getInstance() {
		return INSTANCE;
	}

}
