package com.puresoltechnologies.purifinity.server.purifinityserver.socket.api;

import com.puresoltechnologies.purifinity.server.purifinityserver.domain.PurifinityServerStatus;

public interface PurifinityServerStatusListener {

	public void newServerStatus(PurifinityServerStatus status);

}
