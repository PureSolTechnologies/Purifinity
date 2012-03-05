package com.puresol.rendering;

public class ChartValue<T extends Comparable<T>> implements
	Comparable<ChartValue<T>> {

    private final T value;
    private final String rowKey;
    private final String columnKey;

    public ChartValue(T value, String rowKey, String columnKey) {
	super();
	this.value = value;
	this.rowKey = rowKey;
	this.columnKey = columnKey;
    }

    public T getValue() {
	return value;
    }

    public String getRowKey() {
	return rowKey;
    }

    public String getColumnKey() {
	return columnKey;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((columnKey == null) ? 0 : columnKey.hashCode());
	result = prime * result + ((rowKey == null) ? 0 : rowKey.hashCode());
	result = prime * result + ((value == null) ? 0 : value.hashCode());
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
	ChartValue<?> other = (ChartValue<?>) obj;
	if (columnKey == null) {
	    if (other.columnKey != null)
		return false;
	} else if (!columnKey.equals(other.columnKey))
	    return false;
	if (rowKey == null) {
	    if (other.rowKey != null)
		return false;
	} else if (!rowKey.equals(other.rowKey))
	    return false;
	if (value == null) {
	    if (other.value != null)
		return false;
	} else if (!value.equals(other.value))
	    return false;
	return true;
    }

    @Override
    public int compareTo(ChartValue<T> other) {
	return this.value.compareTo(other.value);
    }

}
