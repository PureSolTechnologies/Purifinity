package com.puresol.osgi;

import javax.swingx.connect.ConnectionManager;

/**
 * This abstract implementation for an OSGIServiceManager implements the
 * handling of the connections.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <T>
 */
public abstract class AbstractOSGIServiceManager<T> implements
		OSGIServiceManager<T> {

	private final ConnectionManager connectionManager = ConnectionManager
			.createFor(this);

	@Override
	public final void connect(String signal, Object receiver, String slot,
			Class<?>... types) {
		connectionManager.connect(signal, receiver, slot, types);
	}

	@Override
	public final void release(String signal, Object receiver, String slot,
			Class<?>... types) {
		connectionManager.release(signal, receiver, slot, types);
	}

	@Override
	public final boolean isConnected(String signal, Object receiver,
			String slot, Class<?>... types) {
		return connectionManager.isConnected(signal, receiver, slot, types);
	}

}
