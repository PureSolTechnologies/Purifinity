package com.puresoltechnologies.purifinity.server.socket.api;

import java.io.IOException;
import java.io.Writer;
import java.util.Properties;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class PurifinityServerStatusRequestEncoder implements
		Encoder.TextStream<PurifinityServerStatusRequest> {

	@Override
	public void init(EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void encode(PurifinityServerStatusRequest object, Writer writer)
			throws EncodeException, IOException {
		Properties properties = new Properties();
		properties.store(writer, "");
	}

}
