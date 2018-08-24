package com.puresoltechnologies.toolshed.server.impl.system;

import com.puresoltechnologies.toolshed.server.api.nodes.OS;

public class LocalNode {

    private static final OS os = OS.local();

    public static OS getOS() {
	return os;
    }
}
