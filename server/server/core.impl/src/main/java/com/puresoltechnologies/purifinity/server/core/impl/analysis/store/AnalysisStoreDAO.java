package com.puresoltechnologies.purifinity.server.core.impl.analysis.store;

import javax.inject.Inject;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.database.cassandra.AnalysisStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraElementNames;

public class AnalysisStoreDAO {

	@Inject
	@AnalysisStoreKeyspace
	private Session session;

	public int getFileSize(HashId hashId) throws AnalysisStoreException {
		ResultSet resultSet = session.execute("SELECT size FROM "
				+ CassandraElementNames.ANALYSIS_FILES_TABLE
				+ " WHERE hashId='" + hashId.toString() + "'");
		Row result = resultSet.one();
		if (result == null) {
			throw new AnalysisStoreException(
					"No file was stored for hashId '"
							+ hashId
							+ "'. The file needs to be stored before the tree. Database is inkonsistent!");
		}
		if (resultSet.one() != null) {
			throw new AnalysisStoreException("For file with hashId '" + hashId
					+ "' multiple files were stored. Database is inkonsistent!");
		}
		int size = result.getInt("size");
		return size;
	}

}
