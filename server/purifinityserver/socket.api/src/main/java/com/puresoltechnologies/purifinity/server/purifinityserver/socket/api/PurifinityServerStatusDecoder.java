package com.puresoltechnologies.purifinity.server.purifinityserver.socket.api;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.puresoltechnologies.purifinity.server.purifinityserver.domain.PurifinityServerStatus;

public class PurifinityServerStatusDecoder implements
		Decoder.Text<PurifinityServerStatus> {

	@Override
	public void init(EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public PurifinityServerStatus decode(String s) throws DecodeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean willDecode(String s) {
		// TODO Auto-generated method stub
		return false;
	}

}
