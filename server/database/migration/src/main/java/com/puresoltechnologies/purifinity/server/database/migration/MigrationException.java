package com.puresoltechnologies.purifinity.server.database.migration;

/**
 * This exception is thrown in cases of issues during Cassandra database
 * migration.
 * 
 * @author Rick-Rainer Ludwig
 */
public class MigrationException extends Exception {

	private static final long serialVersionUID = -9189936379022065854L;

	public MigrationException(String message, Throwable cause) {
		super(message, cause);
	}

	public MigrationException(String message) {
		super(message);
	}

}
