package com.puresoltechnologies.purifinity.server.domain;

import java.io.Serializable;

public class PurifinityServerStatus implements Serializable {

	private static final long serialVersionUID = 8485467322922852041L;

	public String getStatusMessage() {
		return "Up and running.";
	}

}
