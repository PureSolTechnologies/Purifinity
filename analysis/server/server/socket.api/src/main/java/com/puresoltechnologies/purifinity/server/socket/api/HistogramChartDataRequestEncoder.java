package com.puresoltechnologies.purifinity.server.socket.api;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class HistogramChartDataRequestEncoder implements
		Encoder.BinaryStream<HistogramChartDataRequest> {

	@Override
	public void init(EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void encode(HistogramChartDataRequest object, OutputStream os)
			throws EncodeException, IOException {
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(os);
		objectOutputStream.writeObject(object);
	}

}
