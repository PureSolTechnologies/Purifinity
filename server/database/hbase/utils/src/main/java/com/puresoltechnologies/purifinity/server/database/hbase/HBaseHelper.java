package com.puresoltechnologies.purifinity.server.database.hbase;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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

    public static <T> void writeList(Connection connection, PreparedStatement preparedStatement, int column,
	    Class<T> type, List<T> list) throws SQLException {
	@SuppressWarnings("unchecked")
	T[] array = (T[]) list.toArray();
	writeArray(connection, preparedStatement, column, type, array);
    }

    public static <T> void writeArray(Connection connection, PreparedStatement preparedStatement, int column,
	    Class<T> type, T[] array) throws SQLException {
	if (type == String.class) {
	    java.sql.Array sqlArray = connection.createArrayOf("VARCHAR", array);
	    preparedStatement.setArray(column, sqlArray);
	} else {
	    throw new IllegalArgumentException("Type '" + type.getName() + "' is not supported.");
	}
    }

    public static <K, V> void writeMap(Connection connection, PreparedStatement preparedStatement, int keyColumn,
	    int valueColumn, Class<K> keyType, Class<V> valueType, Map<K, V> map) throws SQLException {
	List<K> keys = new ArrayList<>(map.keySet());
	List<V> values = new ArrayList<>();
	for (K key : keys) {
	    values.add(map.get(key));
	}
	@SuppressWarnings("unchecked")
	K[] keyArray = (K[]) keys.toArray();
	@SuppressWarnings("unchecked")
	V[] valueArray = (V[]) values.toArray();
	writeArray(connection, preparedStatement, keyColumn, keyType, keyArray);
	writeArray(connection, preparedStatement, valueColumn, valueType, valueArray);
    }

    public static void writeProperties(Connection connection, PreparedStatement preparedStatement, int keyColumn,
	    int valueColumn, Properties properties) throws SQLException {
	List<Object> keys = new ArrayList<>(properties.keySet());
	List<Object> values = new ArrayList<>();
	for (Object key : keys) {
	    values.add(properties.get(key));
	}
	writeArray(connection, preparedStatement, keyColumn, Object.class, keys.toArray());
	writeArray(connection, preparedStatement, valueColumn, Object.class, values.toArray());
    }

    public static <K, V> Map<K, V> getMap(ResultSet resultSet, int keyColunn, int valueColumn, Class<K> keyType,
	    Class<V> valueType) throws SQLException {
	List<K> keys = HBaseHelper.getList(resultSet, keyColunn, keyType);
	List<V> values = HBaseHelper.getList(resultSet, valueColumn, valueType);
	if (keys.size() != values.size()) {
	    throw new SQLException("Key list and value list differ in length! Map construction not possible.");
	}
	Map<K, V> map = new HashMap<>();
	for (int i = 0; i < keys.size(); ++i) {
	    map.put(keys.get(i), values.get(i));
	}
	return map;
    }

}
