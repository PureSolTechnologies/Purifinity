package com.puresoltechnologies.purifinity.coding.metrics;

import com.puresoltechnologies.purifinity.coding.analysis.api.DirectoryStore;
import com.puresoltechnologies.purifinity.coding.analysis.api.DirectoryStoreFactory;
import com.puresoltechnologies.purifinity.coding.store.fs.analysis.DirectoryStoreImpl;

public class DirectoryStoreTestFactory extends DirectoryStoreFactory {

	private static final DirectoryStore INSTANCE = new DirectoryStoreImpl();

	@Override
	public DirectoryStore getInstance() {
		return INSTANCE;
	}

}
