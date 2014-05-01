package com.puresoltechnologies.purifinity.framework.database.cassandra.utils;

/**
 * This exception is thrown in cases of cassandra connection issues.
 * 
 * @author Rick-Rainer Ludwig
 */
public class CassandraConnectionException extends Exception {

	private static final long serialVersionUID = 2188434242557387014L;

	public CassandraConnectionException(String message, Throwable cause) {
		super(message, cause);
	}

	public CassandraConnectionException(String message) {
		super(message);
	}

}
