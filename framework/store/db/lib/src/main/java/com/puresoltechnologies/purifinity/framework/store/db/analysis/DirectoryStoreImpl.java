package com.puresoltechnologies.purifinity.framework.store.db.analysis;

import java.io.File;
import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.purifinity.framework.store.api.DirectoryStore;
import com.puresoltechnologies.purifinity.framework.store.api.DirectoryStoreException;
import com.puresoltechnologies.purifinity.framework.store.db.CassandraConnection;

public class DirectoryStoreImpl implements DirectoryStore {

	private static final String FILES_FILE = "files.persist";
	private static final String DIRECTORIES_FILE = "directories.persist";

	@Override
	public boolean isAvailable(HashId hashId) {
		Session session = CassandraConnection.getAnalysisSession();
		ResultSet resultSet = session
				.execute("SELECT hashid FROM files WHERE hashid="
						+ hashId.getHash());
		return resultSet.one() != null;
	}

	@Override
	public List<HashId> getFiles(HashId hashId) throws DirectoryStoreException {
		return restore(new File(getDirectoryStoreDirectory(hashId), FILES_FILE));
	}

	@Override
	public List<HashId> getDirectories(HashId hashId)
			throws DirectoryStoreException {
		return restore(new File(getDirectoryStoreDirectory(hashId),
				DIRECTORIES_FILE));
	}
}
