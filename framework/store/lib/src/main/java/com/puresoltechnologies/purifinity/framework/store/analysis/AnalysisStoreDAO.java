package com.puresoltechnologies.purifinity.framework.store.analysis;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.purifinity.framework.store.CassandraConnection;
import com.puresoltechnologies.purifinity.framework.store.CassandraElementNames;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;

public class AnalysisStoreDAO {

	public static int getFileSize(HashId hashId) throws AnalysisStoreException {
		Session session = CassandraConnection.getAnalysisSession();
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
