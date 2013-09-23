package com.puresol.purifinity.coding.metrics;

import com.puresol.purifinity.coding.analysis.api.DirectoryStore;
import com.puresol.purifinity.coding.analysis.api.DirectoryStoreFactory;
import com.puresol.purifinity.coding.store.fs.analysis.DirectoryStoreImpl;

public class DirectoryStoreTestFactory extends DirectoryStoreFactory {

	private static final DirectoryStore INSTANCE = new DirectoryStoreImpl();

	@Override
	public DirectoryStore getInstance() {
		return INSTANCE;
	}

}
