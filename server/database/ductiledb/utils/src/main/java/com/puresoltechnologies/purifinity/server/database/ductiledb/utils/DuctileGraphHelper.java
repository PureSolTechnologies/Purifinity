package com.puresoltechnologies.purifinity.server.database.ductiledb.utils;

import java.io.IOException;

import org.apache.commons.configuration.BaseConfiguration;

import com.puresoltechnologies.ductiledb.tinkerpop.DuctileGraph;
import com.puresoltechnologies.ductiledb.tinkerpop.DuctileGraphFactory;

public class DuctileGraphHelper {

    public static DuctileGraph connect() throws IOException {
	return DuctileGraphFactory.createGraph(new BaseConfiguration());
    }

}
