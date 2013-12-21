package com.puresoltechnologies.purifinity.framework.evaluation.metrics;

import com.puresoltechnologies.purifinity.analysis.api.FileStore;
import com.puresoltechnologies.purifinity.analysis.api.FileStoreFactory;
import com.puresoltechnologies.purifinity.framework.store.fs.analysis.FileStoreImpl;

public class FileStoreTestFactory extends FileStoreFactory {

	private static final FileStore INSTANCE = new FileStoreImpl();

	@Override
	public FileStore getInstance() {
		return INSTANCE;
	}

}
