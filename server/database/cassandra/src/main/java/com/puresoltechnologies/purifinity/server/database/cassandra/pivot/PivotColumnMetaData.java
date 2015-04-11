package com.puresoltechnologies.purifinity.server.database.cassandra.pivot;

public class PivotColumnMetaData<T> {

	private final String columnName;
	private final Class<T> columnType;
	private final boolean filter;
	private final boolean group;
	private final boolean aggregatable;
	private final boolean numeric;
	private final boolean timestamp;

	public PivotColumnMetaData(String columnName, Class<T> columnType,
			boolean filter, boolean group, boolean numeric,
			boolean aggregatable, boolean timestamp) {
		super();
		this.columnName = columnName;
		this.columnType = columnType;
		this.filter = filter;
		this.group = group;
		this.numeric = numeric;
		this.aggregatable = aggregatable;
		this.timestamp = timestamp;
	}

	public String getColumnName() {
		return columnName;
	}

	public Class<?> getColumnType() {
		return columnType;
	}

	public boolean isFilter() {
		return filter;
	}

	public boolean isGroup() {
		return group;
	}

	public boolean isNumeric() {
		return numeric;
	}

	public boolean isAggregatable() {
		return aggregatable;
	}

	public boolean isTimestamp() {
		return timestamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (aggregatable ? 1231 : 1237);
		result = prime * result
				+ ((columnName == null) ? 0 : columnName.hashCode());
		result = prime * result
				+ ((columnType == null) ? 0 : columnType.hashCode());
		result = prime * result + (filter ? 1231 : 1237);
		result = prime * result + (group ? 1231 : 1237);
		result = prime * result + (numeric ? 1231 : 1237);
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
		PivotColumnMetaData<?> other = (PivotColumnMetaData<?>) obj;
		if (aggregatable != other.aggregatable)
			return false;
		if (columnName == null) {
			if (other.columnName != null)
				return false;
		} else if (!columnName.equals(other.columnName))
			return false;
		if (columnType == null) {
			if (other.columnType != null)
				return false;
		} else if (!columnType.equals(other.columnType))
			return false;
		if (filter != other.filter)
			return false;
		if (group != other.group)
			return false;
		if (numeric != other.numeric)
			return false;
		return true;
	}

}
