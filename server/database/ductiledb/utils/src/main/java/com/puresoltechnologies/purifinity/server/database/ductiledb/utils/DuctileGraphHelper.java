package com.puresoltechnologies.purifinity.server.database.ductiledb.utils;

import java.io.IOException;

import org.apache.commons.configuration.BaseConfiguration;

import com.puresoltechnologies.ductiledb.tinkerpop.DuctileGraph;

public class DuctileGraphHelper {

    public static DuctileGraph connect() throws IOException {
	return DuctileGraph.open(new BaseConfiguration());
    }

}
