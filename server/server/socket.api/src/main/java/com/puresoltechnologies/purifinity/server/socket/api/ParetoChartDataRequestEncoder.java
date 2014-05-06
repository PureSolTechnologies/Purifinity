package com.puresoltechnologies.purifinity.server.socket.api;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class ParetoChartDataRequestEncoder implements
		Encoder.BinaryStream<ParetoChartDataRequest> {

	@Override
	public void init(EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void encode(ParetoChartDataRequest object, OutputStream os)
			throws EncodeException, IOException {
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(os);
		objectOutputStream.writeObject(object);
	}

}
