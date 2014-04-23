package com.puresoltechnologies.purifinity.server.purifinityserver.socket.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.puresoltechnologies.purifinity.server.purifinityserver.domain.ParetoChartData;

public class ParetoChartDataDecoder implements
		Decoder.BinaryStream<ParetoChartData> {

	@Override
	public void init(EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public ParetoChartData decode(InputStream is) throws DecodeException,
			IOException {
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(is);
			return (ParetoChartData) objectInputStream.readObject();
		} catch (ClassNotFoundException e) {
			throw new IOException("Could not read object.", e);
		}
	}

}
