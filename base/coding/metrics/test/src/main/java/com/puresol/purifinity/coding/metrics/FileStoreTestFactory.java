package com.puresol.purifinity.coding.metrics;

import com.puresol.purifinity.coding.analysis.api.FileStore;
import com.puresol.purifinity.coding.analysis.api.FileStoreFactory;
import com.puresol.purifinity.coding.store.fs.analysis.FileStoreImpl;

public class FileStoreTestFactory extends FileStoreFactory {

	private static final FileStore INSTANCE = new FileStoreImpl();

	@Override
	public FileStore getInstance() {
		return INSTANCE;
	}

}
