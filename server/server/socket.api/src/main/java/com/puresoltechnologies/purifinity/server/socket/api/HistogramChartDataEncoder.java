package com.puresoltechnologies.purifinity.server.socket.api;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.puresoltechnologies.purifinity.server.domain.HistogramChartData;

public class HistogramChartDataEncoder implements Encoder.BinaryStream<HistogramChartData> {

	@Override
	public void init(EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void encode(HistogramChartData object, OutputStream os)
			throws EncodeException, IOException {
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(os);
		objectOutputStream.writeObject(object);
	}

}
