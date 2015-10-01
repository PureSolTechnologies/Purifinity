package com.puresoltechnologies.purifinity.server.database.hbase;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * This class provides convenient helpers for HBase and Phoenix connections.
 * 
 * @author Rick-Rainer Ludwig
 *
 */
public class HBaseHelper {

    public static Connection connect() throws SQLException {
	try {
	    Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
	} catch (ClassNotFoundException e) {
	    throw new RuntimeException("Could not find Phoenix driver class.", e);
	}
	return DriverManager.getConnection("jdbc:phoenix:localhost");
    }

    public static <T> T[] getArray(ResultSet resultSet, String columnLabel, Class<T> clazz) throws SQLException {
	@SuppressWarnings("unchecked")
	T[] array = (T[]) resultSet.getArray(columnLabel).getArray();
	if (array != null) {
	    return array;
	}
	@SuppressWarnings("unchecked")
	T[] empty = (T[]) Array.newInstance(clazz, 0);
	return empty;
    }

    public static <T> T[] getArray(ResultSet resultSet, int columnIndex, Class<T> clazz) throws SQLException {
	@SuppressWarnings("unchecked")
	T[] array = (T[]) resultSet.getArray(columnIndex).getArray();
	if (array != null) {
	    return array;
	}
	@SuppressWarnings("unchecked")
	T[] empty = (T[]) Array.newInstance(clazz, 0);
	return empty;
    }

    public static <T> List<T> getList(ResultSet resultSet, String columnLabel, Class<T> clazz) throws SQLException {
	return Arrays.asList(getArray(resultSet, columnLabel, clazz));
    }

    public static <T> List<T> getList(ResultSet resultSet, int columnIndex, Class<T> clazz) throws SQLException {
	return Arrays.asList(getArray(resultSet, columnIndex, clazz));
    }
}
