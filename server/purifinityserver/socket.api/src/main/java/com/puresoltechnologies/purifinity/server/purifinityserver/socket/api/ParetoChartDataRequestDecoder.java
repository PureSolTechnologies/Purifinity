package com.puresoltechnologies.purifinity.server.purifinityserver.socket.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class ParetoChartDataRequestDecoder implements
		Decoder.BinaryStream<ParetoChartDataRequest> {

	@Override
	public void init(EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public ParetoChartDataRequest decode(InputStream is)
			throws DecodeException, IOException {
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(is);
			return (ParetoChartDataRequest) objectInputStream.readObject();
		} catch (ClassNotFoundException e) {
			throw new IOException("Could not read object.", e);
		}
	}

}
