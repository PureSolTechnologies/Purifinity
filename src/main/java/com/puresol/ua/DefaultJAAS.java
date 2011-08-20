package com.puresol.ua;

/**
 * This UA module is for JAAS usage. JAAS framework is used completely. The
 * configuration is done in "/config/JAAS.conf".
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DefaultJAAS extends JAAS {

	public DefaultJAAS() {
		super(DefaultJAAS.class.getResource("/config/JAAS.conf").toString());
	}
}
