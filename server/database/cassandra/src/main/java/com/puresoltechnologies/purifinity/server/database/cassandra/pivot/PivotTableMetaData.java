package com.puresoltechnologies.purifinity.server.database.cassandra.pivot;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class PivotTableMetaData {

    public static Set<PivotTableMetaData> readFromDatabase(Session session) {
	try {
	    ResultSet resultSet = session.execute("SELECT " + //
		    "table_name, " //
		    + "column_name, " //
		    + "type, " //
		    + "can_filter, " //
		    + "can_group, " //
		    + "is_numeric, " //
		    + "is_aggregatable, " //
		    + "is_timestamp" + //
		    " FROM pivot_meta_data;");
	    Map<String, Set<PivotColumnMetaData>> tables = new HashMap<>();
	    while (!resultSet.isExhausted()) {
		Row row = resultSet.one();
		String tableName = row.getString(0);
		String columnName = row.getString(1);
		Class<?> type = Class.forName(row.getString(2));
		boolean canFilter = row.getBool(3);
		boolean canGroup = row.getBool(4);
		boolean isNumeric = row.getBool(5);
		boolean isAggregatable = row.getBool(6);
		boolean isTimestamp = row.getBool(7);
		PivotColumnMetaData pivotColumnMetaData = new PivotColumnMetaData(
			columnName, type, canFilter, canGroup, isNumeric,
			isAggregatable, isTimestamp);
		Set<PivotColumnMetaData> table = tables.get(tableName);
		if (table == null) {
		    table = new LinkedHashSet<PivotColumnMetaData>();
		    tables.put(tableName, table);
		}
		table.add(pivotColumnMetaData);
	    }
	    Set<PivotTableMetaData> configurations = new HashSet<>();
	    for (Entry<String, Set<PivotColumnMetaData>> entries : tables
		    .entrySet()) {
		configurations.add(new PivotTableMetaData(session, entries
			.getKey(), entries.getValue()));
	    }
	    return configurations;
	} catch (ClassNotFoundException e) {
	    throw new IllegalStateException("Could not read pivot meta data.",
		    e);
	}
    }

    private final Session session;
    private final String keyspaceName;
    private final String tableName;
    private final Set<PivotColumnMetaData> columnMetaData = new LinkedHashSet<>();

    private PivotTableMetaData(Session session, String tableName,
	    Set<PivotColumnMetaData> columns) {
	super();
	this.session = session;
	this.keyspaceName = session.getLoggedKeyspace();
	this.tableName = tableName;
	this.columnMetaData.addAll(columns);
    }

    public String getKeyspaceName() {
	return keyspaceName;
    }

    public String getTableName() {
	return tableName;
    }

    public Set<PivotColumnMetaData> getColumnMetaData() {
	return columnMetaData;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((columnMetaData == null) ? 0 : columnMetaData.hashCode());
	result = prime * result
		+ ((keyspaceName == null) ? 0 : keyspaceName.hashCode());
	result = prime * result
		+ ((tableName == null) ? 0 : tableName.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	PivotTableMetaData other = (PivotTableMetaData) obj;
	if (columnMetaData == null) {
	    if (other.columnMetaData != null)
		return false;
	} else if (!columnMetaData.equals(other.columnMetaData))
	    return false;
	if (keyspaceName == null) {
	    if (other.keyspaceName != null)
		return false;
	} else if (!keyspaceName.equals(other.keyspaceName))
	    return false;
	if (tableName == null) {
	    if (other.tableName != null)
		return false;
	} else if (!tableName.equals(other.tableName))
	    return false;
	return true;
    }

    public Set<PivotColumnMetaData<?>> getFilterColumns() {
	Set<PivotColumnMetaData<?>> filterColumns = new LinkedHashSet<>();
	for (PivotColumnMetaData<?> columnMetaData : getColumnMetaData()) {
	    if (columnMetaData.isFilter()) {
		filterColumns.add(columnMetaData);
	    }
	}
	return filterColumns;
    }

    public <T> Set<T> getFilterValues(PivotColumnMetaData<T> columnMetaData) {
	ResultSet resultSet = session.execute("SELECT DISTINCT "
		+ columnMetaData.getColumnName() + " FROM " + keyspaceName
		+ "." + tableName);
	if (columnMetaData.getColumnType().equals(Double.class)) {
	    Set<Double> filterValues = new HashSet<>();
	    resultSet.forEach(x -> filterValues.add(x.getDouble(0)));
	    @SuppressWarnings("unchecked")
	    Set<T> set = (Set<T>) filterValues;
	    return set;
	} else if (columnMetaData.getColumnType().equals(Float.class)) {
	    Set<Float> filterValues = new HashSet<>();
	    resultSet.forEach(x -> filterValues.add(x.getFloat(0)));
	    @SuppressWarnings("unchecked")
	    Set<T> set = (Set<T>) filterValues;
	    return set;
	} else if (columnMetaData.getColumnType().equals(String.class)) {
	    Set<String> filterValues = new HashSet<>();
	    resultSet.forEach(x -> filterValues.add(x.getString(0)));
	    @SuppressWarnings("unchecked")
	    Set<T> set = (Set<T>) filterValues;
	    return set;
	} else if (columnMetaData.getColumnType().equals(Date.class)) {
	    Set<Date> filterValues = new HashSet<>();
	    resultSet.forEach(x -> filterValues.add(x.getDate(0)));
	    @SuppressWarnings("unchecked")
	    Set<T> set = (Set<T>) filterValues;
	    return set;
	}
	throw new IllegalArgumentException("Not supported type '"
		+ columnMetaData.getColumnType().getName() + "'.");
    }
}
