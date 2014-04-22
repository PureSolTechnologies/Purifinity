package com.puresoltechnologies.purifinity.server.purifinityserver.socket.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.puresoltechnologies.purifinity.server.purifinityserver.domain.HistogramChartData;

public class ParetoChartDataDecoder implements
		Decoder.BinaryStream<HistogramChartData> {

	@Override
	public void init(EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public HistogramChartData decode(InputStream is) throws DecodeException,
			IOException {
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(is);
			return (HistogramChartData) objectInputStream.readObject();
		} catch (ClassNotFoundException e) {
			throw new IOException("Could not read object.", e);
		}
	}

}
