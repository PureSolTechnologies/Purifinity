package com.puresoltechnologies.purifinity.server.preferences;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.database.cassandra.PreferencesStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraElementNames;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraPreparedStatements;

public class PreferencesStoreImpl implements PreferencesStore {

	@Inject
	private Logger logger;

	@Inject
	private CassandraPreparedStatements preparedStatements;

	@Inject
	@PreferencesStoreKeyspace
	private Session session;

	@Override
	public PreferencesValue getValue(String group, String name) {
		PreparedStatement preparedStatement = preparedStatements
				.getPreparedStatement(session,
						"SELECT changed, changed_by, value FROM "
								+ CassandraElementNames.PREFERENCES_TABLE
								+ " WHERE group=? AND name=?;");
		BoundStatement boundStatement = preparedStatement.bind(group, name);
		ResultSet resultSet = session.execute(boundStatement);
		Row result = resultSet.one();
		if (result == null) {
			return null;
		}
		PreferencesValue value = new PreferencesValue(result.getDate(0),
				result.getString(1), group, name, result.getString(2));
		logger.info("Read preference: " + value.toString());
		return value;
	}

	@Override
	public PreferencesValue getValue(String group, String name,
			String defaultValue) {
		PreferencesValue value = getValue(group, name);
		if (value != null) {
			return value;
		}
		return new PreferencesValue(null, null, group, name, defaultValue);
	}

	@Override
	public void setValue(String group, String name, String value) {
		PreparedStatement preparedStatement = preparedStatements
				.getPreparedStatement(session,
						"INSERT INTO preferences (group, name, value) VALUES (?, ?, ?);");
		BoundStatement boundStatement = preparedStatement.bind(group, name,
				value);
		session.execute(boundStatement);
		logger.info("Wrote preference: '" + group + "/" + name + "'='" + value
				+ "'");
	}

	@Override
	public boolean hasValue(String group, String name) {
		return getValue(group, name) != null;
	}

}
