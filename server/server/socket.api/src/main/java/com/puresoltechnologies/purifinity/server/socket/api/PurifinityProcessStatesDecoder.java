package com.puresoltechnologies.purifinity.server.socket.api;

import java.io.IOException;
import java.io.Reader;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.puresoltechnologies.commons.math.JSONSerializer;
import com.puresoltechnologies.purifinity.server.core.api.analysis.states.PurifinityProcessStates;

public class PurifinityProcessStatesDecoder implements
	Decoder.TextStream<PurifinityProcessStates> {

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public PurifinityProcessStates decode(Reader reader)
	    throws DecodeException, IOException {
	return JSONSerializer.fromJSONString(reader,
		PurifinityProcessStates.class);
    }
}
