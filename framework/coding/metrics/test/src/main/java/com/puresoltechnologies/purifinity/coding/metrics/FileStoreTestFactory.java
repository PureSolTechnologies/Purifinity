package com.puresoltechnologies.purifinity.coding.metrics;

import com.puresoltechnologies.purifinity.coding.analysis.api.FileStore;
import com.puresoltechnologies.purifinity.coding.analysis.api.FileStoreFactory;
import com.puresoltechnologies.purifinity.coding.store.fs.analysis.FileStoreImpl;

public class FileStoreTestFactory extends FileStoreFactory {

	private static final FileStore INSTANCE = new FileStoreImpl();

	@Override
	public FileStore getInstance() {
		return INSTANCE;
	}

}
