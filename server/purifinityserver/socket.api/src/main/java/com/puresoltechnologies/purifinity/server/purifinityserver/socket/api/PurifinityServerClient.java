package com.puresoltechnologies.purifinity.server.purifinityserver.socket.api;

import java.io.IOException;

/**
 * This is the official interface to the Purifinity server for the client
 * implementation.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface PurifinityServerClient extends AutoCloseable {

	/**
	 * This method requests a new Purifinity Server status which is sent to all
	 * listeners.
	 * 
	 * @throws IOException
	 *             is throw in cases of IO issues.
	 */
	public void requestServerStatus() throws IOException;

	public void addPurifinityServerStatusListener(
			PurifinityServerStatusListener listener);

	public void removePurifinityServerStatusListener(
			PurifinityServerStatusListener listener);

	@Override
	public void close() throws IOException;

}