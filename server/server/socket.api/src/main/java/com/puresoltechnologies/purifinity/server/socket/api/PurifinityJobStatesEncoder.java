package com.puresoltechnologies.purifinity.server.socket.api;

import java.io.IOException;
import java.io.Writer;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.puresoltechnologies.commons.domain.JSONSerializer;
import com.puresoltechnologies.purifinity.server.core.api.analysis.jobs.PurifinityJobStates;

public class PurifinityJobStatesEncoder implements
		Encoder.TextStream<PurifinityJobStates> {

	@Override
	public void init(EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void encode(PurifinityJobStates object, Writer writer)
			throws EncodeException, IOException {
		String jsonString = JSONSerializer.toJSONString(object);
		writer.write(jsonString);
	}
}
