package com.puresoltechnologies.purifinity.server.purifinityserver.socket.api;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.puresoltechnologies.purifinity.server.purifinityserver.domain.PurifinityServerStatus;

public class PurifinityServerStatusEncoder implements
		Encoder.Text<PurifinityServerStatus> {

	@Override
	public void init(EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public String encode(PurifinityServerStatus status) throws EncodeException {
		return "";
	}

}
