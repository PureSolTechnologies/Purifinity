package com.puresoltechnologies.purifinity.server.purifinityserver.socket.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.puresoltechnologies.purifinity.server.purifinityserver.domain.ChartData1D;

public class ChartData1DDecoder implements Decoder.BinaryStream<ChartData1D> {

	@Override
	public void init(EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public ChartData1D decode(InputStream is) throws DecodeException,
			IOException {
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(is);
			return (ChartData1D) objectInputStream.readObject();
		} catch (ClassNotFoundException e) {
			throw new IOException("Could not read object.", e);
		}
	}

}
