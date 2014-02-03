package com.puresoltechnologies.purifinity.framework.database.cassandra.utils;

import com.datastax.driver.core.Cluster;

/**
 * This interface is for the implementation of Cassandra migration procedures
 * which are to be implemented in Java to perform more complex operations than
 * what can be expressed in CQL.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface MigrationProcedure {

	/**
	 * This method implements the actual migration procedure.
	 * 
	 * @param cluster
	 *            is the {@link Cluster} which is to be migrated.
	 */
	public void perform(Cluster cluster);

}
