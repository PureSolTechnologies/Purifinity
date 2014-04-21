package com.puresoltechnologies.purifinity.server.purifinityserver.socket.api;

import java.io.IOException;
import java.io.Writer;
import java.util.Properties;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.puresoltechnologies.purifinity.server.purifinityserver.domain.PurifinityServerStatus;

/**
 * This class encodes {@link PurifinityServerStatus}.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class PurifinityServerStatusEncoder implements
		Encoder.TextStream<PurifinityServerStatus> {

	@Override
	public void init(EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void encode(PurifinityServerStatus object, Writer writer)
			throws EncodeException, IOException {
		Properties properties = new Properties();
		properties.store(writer, "");
	}

}
