package com.puresoltechnologies.purifinity.server.database.ductiledb.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;

import com.puresoltechnologies.ductiledb.core.DuctileDB;
import com.puresoltechnologies.ductiledb.core.DuctileDBBootstrap;
import com.puresoltechnologies.ductiledb.core.DuctileDBConfiguration;
import com.puresoltechnologies.purifinity.server.common.configuration.PurifinityConfiguration;

/**
 * This producer connects to DuctileDB and produces the {@link DuctileDB} object
 * which can be injected.
 * 
 * @author Rick-Rainer Ludwig
 */
@Singleton
public class DuctileDBProducer {

    @Inject
    private Logger logger;

    @Inject
    private PurifinityConfiguration configuration;

    @Produces
    @Singleton
    public DuctileDB getDuctileDB() {
	logger.info("Opening DuctileDB database...");
	File configDir = configuration.getConfigDir();
	File ductileDBConfFile = new File(configDir, "ductiledb.yml");
	if ((!ductileDBConfFile.exists()) || (!ductileDBConfFile.isFile())) {
	    throw new IllegalStateException("DuctileDB configuration file '" + ductileDBConfFile + "' does not exist.");
	}

	try (FileInputStream fileInputStream = new FileInputStream(ductileDBConfFile)) {
	    DuctileDBConfiguration configuration = DuctileDBBootstrap.readConfiguration(fileInputStream);
	    DuctileDBBootstrap.start(configuration);
	    DuctileDB session = DuctileDBBootstrap.getInstance();
	    logger.info("DuctileDB database opened.");
	    return session;
	} catch (IOException e) {
	    throw new RuntimeException("Could not start DuctileDB database.", e);
	}
    }

    public void closePluginsKeyspaceSession(@Disposes DuctileDB ductileDB) {
	try {
	    ductileDB.close();
	} catch (IOException e) {
	    logger.error("Could not close DuctileDB database.", e);
	}
    }

}
