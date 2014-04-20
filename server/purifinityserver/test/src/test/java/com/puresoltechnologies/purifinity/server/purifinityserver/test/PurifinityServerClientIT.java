package com.puresoltechnologies.purifinity.server.purifinityserver.test;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;

import com.puresoltechnologies.purifinity.server.purifinityserver.client.PurifinityServerClientImpl;
import com.puresoltechnologies.purifinity.server.purifinityserver.socket.api.PurifinityServerClient;

public class PurifinityServerClientIT extends
		AbstractPurifinityServerClientTest {

	@Test
	public void test() throws IOException, InterruptedException {
		PurifinityServerClient client = new PurifinityServerClientImpl();
		try {
			assertNotNull(client.requestServerStatus());
		} finally {
			client.close();
		}
	}

}
