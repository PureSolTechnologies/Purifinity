package com.puresol.osgi;

/**
 * This interface is to be implemented from all classes which want to be
 * notified by OSGi framework starts and stops.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface OSGiFrameworkListener {

	public void preStart();

	public void postStart();

	public void preStop();

	public void postStop();

}
