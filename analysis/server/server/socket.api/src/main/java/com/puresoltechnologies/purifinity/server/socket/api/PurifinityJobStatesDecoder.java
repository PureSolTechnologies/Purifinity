package com.puresoltechnologies.purifinity.server.socket.api;

import java.io.IOException;
import java.io.Reader;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.puresoltechnologies.commons.domain.JSONSerializer;
import com.puresoltechnologies.purifinity.server.core.api.analysis.jobs.PurifinityJobStates;

public class PurifinityJobStatesDecoder implements
		Decoder.TextStream<PurifinityJobStates> {

	@Override
	public void init(EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public PurifinityJobStates decode(Reader reader)
			throws DecodeException, IOException {
		return JSONSerializer.fromJSONString(reader,
				PurifinityJobStates.class);
	}
}
