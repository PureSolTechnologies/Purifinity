/***************************************************************************
 *
 * Copyright 2009-2010 PureSol Technologies 
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 *
 ***************************************************************************/

package com.puresol.purifinity.utils.data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import com.puresol.purifinity.utils.io.ValueType;

/**
 * This class keeps the complete data of a vertical table. A vertical table
 * consists of one row of column names and a lot of data rows.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class VerticalData {

    /**
     * This variable keeps the column names indexed in this vector.
     */
    protected Vector<String> columnNames;

    /**
     * This variable keeps the column names indexed in this vector.
     */
    protected Vector<ValueType> columnTypes;

    /**
     * This Hashtable stores the column indexes referenced by their names.
     */
    protected Hashtable<String, Integer> columnIDs;

    /**
     * This cascaded vector stores all integer based columns.
     */
    protected Vector<Vector<Object>> columnData;

    /**
     * This cascaded vector stores all integer based columns.
     */
    protected Vector<Vector<Object>> rowData;

    /**
     * This constructor initializes all variables to standard values without
     * specified initial values.
     */
    public VerticalData() {
	clear();
    }

    /**
     * This constructor sets initial values and settings specified by a
     * ResultSet.
     * 
     * @param resultSet
     *            is the ResultSet where the values are to be taken out.
     */
    private VerticalData(ResultSet resultSet) {
	this();
	set(resultSet);
    }

    public static VerticalData fromResultSet(ResultSet resultSet) {
	return new VerticalData(resultSet);
    }

    /**
     * This methods empties the complete table and resets all information to an
     * initial empty state.
     */
    public void clear() {
	columnNames = new Vector<String>();
	columnTypes = new Vector<ValueType>();
	columnIDs = new Hashtable<String, Integer>();

	columnData = new Vector<Vector<Object>>();
	rowData = new Vector<Vector<Object>>();
    }

    /**
     * This method adds a new column to the end of the table.
     * 
     * @param name
     *            is the name of the column.
     * @param type
     *            is the type of the column's content as a fully qualified class
     *            name.
     */
    public void addColumn(String name, String type) {
	addColumn(name, ValueType.fromClassName(type));
    }

    /**
     * This method adds a new column to the end of the table.
     * 
     * @param name
     *            is the name of the column.
     * @param type
     *            is the type of the column's content as a Class variable.
     */
    public void addColumn(String name, Class<?> type) {
	addColumn(name, ValueType.fromClass(type));
    }

    /**
     * This method adds a new column to the end of the table.
     * 
     * @param name
     *            is the name of the column.
     * @param type
     *            is the type of the column's content.
     */
    public void addColumn(String name, ValueType type) {
	columnNames.add(name);
	columnIDs.put(name, columnNames.size() - 1);
	columnTypes.add(type);
    }

    /**
     * This method adds a data row to the end of the table. The number of values
     * and the types are checked against the number and types of columns.
     * 
     * @param values
     *            is a list of values for the table row.
     */
    public void addRow(Object... values) throws IllegalArgumentException {
	if (values.length != columnNames.size()) {
	    throw new IllegalArgumentException(
		    "The number of arguments does not match the number of column in table!");
	}
	Vector<Object> row = new Vector<Object>();
	for (int column = 0; column < values.length; column++) {
	    if (columnTypes.get(column).getClassObject()
		    .isAssignableFrom(values[column].getClass())) {
		row.add(values[column]);
	    } else {
		try {
		    Method valueOf = columnTypes.get(column).getClassObject()
			    .getMethod("valueOf", values[column].getClass());
		    row.add(valueOf.invoke(columnTypes.get(column),
			    values[column]));
		    continue;
		} catch (SecurityException e) {
		    e.printStackTrace();
		} catch (NoSuchMethodException e) {
		    e.printStackTrace();
		} catch (IllegalAccessException e) {
		    e.printStackTrace();
		} catch (InvocationTargetException e) {
		    if (values[column] == null) {
			row.add(null);
			continue;
		    } else if (values[column].getClass().equals(String.class)) {
			if (((String) values[column]).isEmpty()) {
			    row.add(null);
			    continue;
			}
		    }
		    e.printStackTrace();
		}
		throw new IllegalArgumentException("Class '"
			+ values.getClass().getName()
			+ "' is not castable to column type '"
			+ columnTypes.get(column).getClassName()
			+ "' of column " + (column + 1) + "!");
	    }
	}
	rowData.add(row);
    }

    /**
     * This method sets the content of the table by a ResultSet.
     * 
     * @param resultSet
     *            is the ResultSet where all data is to be taken out.
     */
    public void set(ResultSet resultSet) {
	clear();
	try {
	    ResultSetMetaData metaData = resultSet.getMetaData();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 0; column < columnCount; column++) {
		String name = metaData.getColumnName(column + 1);
		addColumn(name, ValueType.fromClassName(metaData
			.getColumnClassName(column + 1)));
	    }
	    while (resultSet.next()) {
		Vector<Object> row = new Vector<Object>();
		for (int column = 0; column < columnCount; column++) {
		    row.add(resultSet.getObject(column + 1));
		}
		rowData.add(row);
	    }
	} catch (SQLException e) {
	    clear();
	    return;
	}
    }

    /**
     * This method returns a table entry.
     * 
     * @param row
     *            is the row number of the element to be returned.
     * @param col
     *            is the column number of the element to be returned.
     * @return A Object is returned representing the specified element.
     * @throws IllegalArgumentException
     *             is thrown in case of an illegal argument.
     */
    public Object getElement(int row, int col) throws IllegalArgumentException {
	if (row < 0) {
	    System.err.println("row is smaller than zero!");
	    throw new IllegalArgumentException("row is smaller than zero!");
	}
	if (row >= getRowNumber()) {
	    System.err.println("row is too large!");
	    throw new IllegalArgumentException("row is too large!");
	}
	if (col < 0) {
	    System.err.println("col is smaller than zero!");
	    throw new IllegalArgumentException("col is smaller than zero!");
	}
	if (col >= getColumnNumber()) {
	    System.err.println("col is too large!");
	    throw new IllegalArgumentException("col is too large!");
	}
	return rowData.get(row).get(col);
    }

    /**
     * This method converts a table entry to a Integer.
     * 
     * @param row
     *            is the row number of the element to be returned.
     * @param col
     *            is the column number of the element to be returned.
     * @return A Integer is returned representing the specified element.
     * @throws IllegalArgumentException
     *             is thrown in case of an illegal argument.
     */
    public Integer getInteger(int row, int col) throws IllegalArgumentException {
	Object o = getElement(row, col);
	return Integer.class.cast(o);
    }

    /**
     * This method converts a table entry to a Float.
     * 
     * @param row
     *            is the row number of the element to be returned.
     * @param col
     *            is the column number of the element to be returned.
     * @return A Float is returned representing the specified element.
     * @throws IllegalArgumentException
     *             is thrown in case of an illegal argument.
     */
    public Float getFloat(int row, int col) throws IllegalArgumentException {
	Object o = getElement(row, col);
	return Float.class.cast(o);
    }

    /**
     * This method converts a table entry to a Double.
     * 
     * @param row
     *            is the row number of the element to be returned.
     * @param col
     *            is the column number of the element to be returned.
     * @return A Double is returned representing the specified element.
     * @throws IllegalArgumentException
     *             is thrown in case of an illegal argument.
     */
    public Double getDouble(int row, int col) throws IllegalArgumentException {
	Object o = getElement(row, col);
	return Double.class.cast(o);
    }

    /**
     * This method converts a table entry to a String.
     * 
     * @param row
     *            is the row number of the element to be returned.
     * @param col
     *            is the column number of the element to be returned.
     * @return A String is returned representing the specified element.
     * @throws IllegalArgumentException
     *             is thrown in case of an illegal argument.
     */
    public String getString(int row, int col) throws IllegalArgumentException {
	Object o = getElement(row, col);
	return String.valueOf(o);
    }

    /**
     * This method converts a table entry to a Date.
     * 
     * @param row
     *            is the row number of the element to be returned.
     * @param col
     *            is the column number of the element to be returned.
     * @return A Date is returned representing the specified element.
     * @throws IllegalArgumentException
     *             is thrown in case of an illegal argument.
     */
    public Date getDate(int row, int col) throws IllegalArgumentException {
	Object o = getElement(row, col);
	return Date.class.cast(o);
    }

    /**
     * This method is for reading the column number from the current vertical
     * data set.
     * 
     * @return An int is returned containing the number of columns.
     */
    public int getColumnNumber() {
	return columnNames.size();
    }

    /**
     * This method is for reading the row number from the current vertical data
     * set.
     * 
     * @return An int is returned containing the number of rows.
     */
    public int getRowNumber() {
	return rowData.size();
    }

    /**
     * This method returns the name of the column.
     * 
     * @param col
     *            is the column number the information is to returned.
     * @return A String is returned containing the column name.
     */
    public String getName(int col) {
	return columnNames.get(col);
    }

    /**
     * This method returns the VariableType of the column.
     * 
     * @param col
     *            is the column number the information is to returned.
     * @return A VariableType class is returned containing the type information.
     */
    public ValueType getType(int col) {
	return columnTypes.get(col);
    }

    /**
     * This method looks for a column name and return
     * 
     * @param columnName
     * @return
     */
    public int getColumnID(String columnName) {
	if (!columnIDs.containsKey(columnName)) {
	    return -1;
	}
	return columnIDs.get(columnName);
    }

    public void println() {
	for (int id = 0; id < getColumnNumber(); id++) {
	    if (id > 0) {
		System.out.print("\t");
	    }
	    System.out.print(columnNames.get(id));
	}
	System.out.println();
	for (int rowID = 0; rowID < getRowNumber(); rowID++) {
	    for (int colID = 0; colID < getColumnNumber(); colID++) {
		if (colID > 0) {
		    System.out.print("\t");
		}
		System.out.print(getString(rowID, colID));
	    }
	    System.out.println();
	}
    }

    public Vector<String> getColumnNames() {
	return columnNames;
    }
}
