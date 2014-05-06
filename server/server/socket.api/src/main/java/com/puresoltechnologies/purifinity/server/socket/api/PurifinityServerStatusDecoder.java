package com.puresoltechnologies.purifinity.server.socket.api;

import java.io.IOException;
import java.io.Reader;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.puresoltechnologies.purifinity.server.domain.PurifinityServerStatus;

public class PurifinityServerStatusDecoder implements
		Decoder.TextStream<PurifinityServerStatus> {

	@Override
	public void init(EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public PurifinityServerStatus decode(Reader reader) throws DecodeException,
			IOException {
		return new PurifinityServerStatus();
	}

}
