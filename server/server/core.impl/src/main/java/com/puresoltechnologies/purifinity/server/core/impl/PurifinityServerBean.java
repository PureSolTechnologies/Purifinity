package com.puresoltechnologies.purifinity.server.core.impl;

import com.puresoltechnologies.purifinity.server.core.api.PurifinityServer;
import com.puresoltechnologies.purifinity.server.domain.PurifinityServerStatus;

/**
 * This is the central implementation of the Purifinity server.
 * 
 * @author Rick-Rainer Ludwig
 */
public class PurifinityServerBean implements PurifinityServer {

    @Override
    public PurifinityServerStatus getStatus() {
	return new PurifinityServerStatus();
    }

}
