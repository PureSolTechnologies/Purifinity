package com.puresol.coding.store.fs;

import com.puresol.coding.analysis.api.AnalysisStore;
import com.puresol.coding.analysis.api.AnalysisStoreFactory;
import com.puresol.coding.store.fs.analysis.AnalysisStoreImpl;

public class AnalysisStoreFactoryTest extends AnalysisStoreFactory {

	private static final AnalysisStoreImpl INSTANCE = new AnalysisStoreImpl();

	@Override
	public AnalysisStore getInstance() {
		return INSTANCE;
	}

}
