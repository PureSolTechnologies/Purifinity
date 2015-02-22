package com.puresoltechnologies.purifinity.server.socket.api;

import java.io.IOException;
import java.io.Writer;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.puresoltechnologies.commons.math.JSONSerializer;
import com.puresoltechnologies.purifinity.server.core.api.analysis.states.PurifinityProcessStates;

public class PurifinityProcessStatesEncoder implements
	Encoder.TextStream<PurifinityProcessStates> {

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void encode(PurifinityProcessStates object, Writer writer)
	    throws EncodeException, IOException {
	String jsonString = JSONSerializer.toJSONString(object);
	writer.write(jsonString);
    }
}
