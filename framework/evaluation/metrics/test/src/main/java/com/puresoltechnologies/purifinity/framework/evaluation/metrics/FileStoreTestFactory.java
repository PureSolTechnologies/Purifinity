package com.puresoltechnologies.purifinity.framework.evaluation.metrics;

import com.puresoltechnologies.purifinity.framework.store.api.FileStore;
import com.puresoltechnologies.purifinity.framework.store.api.FileStoreFactory;

public class FileStoreTestFactory extends FileStoreFactory {

	private static final FileStore INSTANCE = null;

	@Override
	public FileStore getInstance() {
		return INSTANCE;
	}

}
