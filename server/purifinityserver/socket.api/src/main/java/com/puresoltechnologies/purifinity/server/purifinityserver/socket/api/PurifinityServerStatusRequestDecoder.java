package com.puresoltechnologies.purifinity.server.purifinityserver.socket.api;

import java.io.IOException;
import java.io.Reader;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class PurifinityServerStatusRequestDecoder implements
		Decoder.TextStream<PurifinityServerStatusRequest> {

	@Override
	public void init(EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public PurifinityServerStatusRequest decode(Reader reader)
			throws DecodeException, IOException {
		return new PurifinityServerStatusRequest();
	}

}
