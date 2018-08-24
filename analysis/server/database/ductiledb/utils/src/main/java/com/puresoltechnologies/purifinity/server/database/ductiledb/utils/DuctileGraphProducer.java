package com.puresoltechnologies.purifinity.server.database.ductiledb.utils;

import java.io.IOException;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;

import com.puresoltechnologies.ductiledb.core.DuctileDB;
import com.puresoltechnologies.ductiledb.storage.api.StorageException;
import com.puresoltechnologies.ductiledb.storage.engine.schema.SchemaException;
import com.puresoltechnologies.ductiledb.tinkerpop.DuctileGraph;

@Singleton
public class DuctileGraphProducer {

    @Inject
    private Logger logger;

    @Inject
    private DuctileDB ductileDB;

    @Produces
    @Singleton
    public DuctileGraph getDuctileGraph() {
	try {
	    logger.info("Creating Ductile Graph...");
	    DuctileGraph ductileGraph = DuctileGraph.open(ductileDB);
	    logger.info("Ductile Graph created.");
	    return ductileGraph;
	} catch (StorageException | SchemaException | IOException e) {
	    throw new RuntimeException("Could not create Ductile Graph.", e);
	}
    }

    public void closeDuctileGraph(@Disposes DuctileGraph ductileGraph) {
	try {
	    ductileGraph.close();
	} catch (IOException e) {
	    logger.warn("Could not close Ductile Graph.", e);
	}
    }

}
