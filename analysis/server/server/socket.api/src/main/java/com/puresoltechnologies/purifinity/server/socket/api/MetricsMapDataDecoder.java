package com.puresoltechnologies.purifinity.server.socket.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.puresoltechnologies.purifinity.server.domain.MetricsMapData;

public class MetricsMapDataDecoder implements
		Decoder.BinaryStream<MetricsMapData> {

	@Override
	public void init(EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public MetricsMapData decode(InputStream is) throws DecodeException,
			IOException {
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(is);
			return (MetricsMapData) objectInputStream.readObject();
		} catch (ClassNotFoundException e) {
			throw new IOException("Could not read object.", e);
		}
	}

}
