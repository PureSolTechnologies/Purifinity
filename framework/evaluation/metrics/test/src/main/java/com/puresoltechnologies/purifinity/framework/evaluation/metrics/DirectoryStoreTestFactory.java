package com.puresoltechnologies.purifinity.framework.evaluation.metrics;

import com.puresoltechnologies.purifinity.analysis.api.DirectoryStore;
import com.puresoltechnologies.purifinity.analysis.api.DirectoryStoreFactory;
import com.puresoltechnologies.purifinity.framework.store.fs.analysis.DirectoryStoreImpl;

public class DirectoryStoreTestFactory extends DirectoryStoreFactory {

	private static final DirectoryStore INSTANCE = new DirectoryStoreImpl();

	@Override
	public DirectoryStore getInstance() {
		return INSTANCE;
	}

}
