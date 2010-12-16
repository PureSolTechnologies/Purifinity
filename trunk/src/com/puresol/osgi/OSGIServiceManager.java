package com.puresol.osgi;

import java.util.List;

import javax.swingx.connect.ConnectionHandler;

/**
 * This interface is used for different service managers which are used to
 * register services from bundles.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <T>
 */
public interface OSGIServiceManager<T> extends ConnectionHandler {

	public void register(T o);

	public void unregister(T o);

	public List<T> getAll();
}
