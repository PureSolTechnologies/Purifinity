package com.puresoltechnologies.purifinity.server.purifinityserver.socket.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class ChartData1DRequestDecoder implements
		Decoder.BinaryStream<ChartData1DRequest> {

	@Override
	public void init(EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public ChartData1DRequest decode(InputStream is) throws DecodeException,
			IOException {
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(is);
			return (ChartData1DRequest) objectInputStream.readObject();
		} catch (ClassNotFoundException e) {
			throw new IOException("Could not read object.", e);
		}
	}

}
