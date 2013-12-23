package com.puresoltechnologies.purifinity.framework.evaluation.metrics;

import com.puresoltechnologies.purifinity.framework.store.api.DirectoryStore;
import com.puresoltechnologies.purifinity.framework.store.api.DirectoryStoreFactory;

public class DirectoryStoreTestFactory extends DirectoryStoreFactory {

	private static final DirectoryStore INSTANCE = null;

	@Override
	public DirectoryStore getInstance() {
		return INSTANCE;
	}

}
