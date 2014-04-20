package com.puresoltechnologies.purifinity.server.purifinityserver.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.puresoltechnologies.purifinity.server.purifinityserver.client.PurifinityServerClientImpl;
import com.puresoltechnologies.purifinity.server.purifinityserver.domain.PurifinityServerStatus;
import com.puresoltechnologies.purifinity.server.purifinityserver.socket.api.PurifinityServerClient;
import com.puresoltechnologies.purifinity.server.purifinityserver.socket.api.PurifinityServerStatusListener;

public class PurifinityServerClientIT extends
		AbstractPurifinityServerClientTest {

	private boolean recievedStatus = false;

	private class StatusListener implements PurifinityServerStatusListener {

		@Override
		public void newServerStatus(PurifinityServerStatus status) {
			recievedStatus = true;
		}

	}

	@Test
	public void test() throws IOException, InterruptedException {
		PurifinityServerClient client = new PurifinityServerClientImpl();
		try {
			client.addPurifinityServerStatusListener(new StatusListener());
			client.requestServerStatus();
			int count = 100;
			while (!recievedStatus) {
				TimeUnit.MICROSECONDS.sleep(100);
				count--;
				if (count < 0) {
					break;
				}
			}
		} finally {
			client.close();
		}
	}

}
