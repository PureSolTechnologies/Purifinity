package com.puresoltechnologies.purifinity.client.common.ui.parts;

/**
 * This interface is used in parts (views and editors) to signal parts which use
 * the database. It is used to disable and enable these parts and to signal the
 * user the availability of the database connection.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface DatabaseTarget {

	/**
	 * Tells the part whether the database is available or not.
	 * 
	 * @param available
	 *            specified whether the database is available. <code>true</code>
	 *            tells the database is available and functionality using it can
	 *            be enabled. <code>false</code> tells the opposite.
	 */
	public void setDatabaseAvailable(boolean available);

}
