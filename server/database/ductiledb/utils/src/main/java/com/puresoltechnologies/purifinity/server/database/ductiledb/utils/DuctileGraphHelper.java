package com.puresoltechnologies.purifinity.server.database.ductiledb.utils;

import java.io.IOException;

import org.apache.commons.configuration.BaseConfiguration;

import com.google.protobuf.ServiceException;
import com.puresoltechnologies.ductiledb.tinkerpop.DuctileGraph;
import com.puresoltechnologies.ductiledb.tinkerpop.DuctileGraphFactory;

public class DuctileGraphHelper {

    public static DuctileGraph connect() throws IOException, ServiceException {
	return DuctileGraphFactory.createGraph("localhost", 2181, "localhost", 60000, new BaseConfiguration());
    }

}
