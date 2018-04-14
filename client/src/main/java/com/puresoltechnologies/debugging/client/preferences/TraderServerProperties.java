package com.puresoltechnologies.debugging.client.preferences;

import com.puresoltechnologies.javafx.extensions.properties.PropertyDefinition;
import com.puresoltechnologies.javafx.extensions.properties.SimplePropertyDefinition;

public interface TraderServerProperties {

    static final String PROPERTY_BASE = TraderServerProperties.class.getPackage().getName();

    static final String traderHostDefault = "localhost";
    static final int traderPortDefault = 8080;

    public static final PropertyDefinition<String> traderHost = new SimplePropertyDefinition<>(
	    PROPERTY_BASE + ".trader.server.host", "Trader Server Host",
	    "Defines the hostname or IP address of the Trader server.", String.class, traderHostDefault);

    public static final PropertyDefinition<Integer> traderPort = new SimplePropertyDefinition<>(
	    PROPERTY_BASE + ".trader.server.port", "Trader Server Pot", "Defines the port of the Trader server.",
	    Integer.class, traderPortDefault);

}
