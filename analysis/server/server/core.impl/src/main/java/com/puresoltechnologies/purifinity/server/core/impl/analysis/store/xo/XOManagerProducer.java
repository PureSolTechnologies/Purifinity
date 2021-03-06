package com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;

import com.buschmais.xo.api.XOManager;
import com.buschmais.xo.api.XOManagerFactory;
import com.buschmais.xo.api.bootstrap.XO;

@Singleton
public class XOManagerProducer {

    @Inject
    private Logger logger;

    private XOManagerFactory xoManagerFactory;

    @PostConstruct
    public void postConstruct() {
	xoManagerFactory = XO.createXOManagerFactory(DuctileDBXOManager.XO_UNIT_NAME);
    }

    @PreDestroy
    public void preDestroy() {
	xoManagerFactory.close();
    }

    @Produces
    @DuctileDBXOManager
    public XOManager produceXOManager() {
	logger.debug("Creating XOManager for DuctileDB...");
	XOManager xoManager = xoManagerFactory.createXOManager();
	logger.debug("XOManager for DuctileDB created.");
	return xoManager;
    }

    public void closeXOManager(@Disposes @DuctileDBXOManager XOManager xoManager) {
	logger.debug("Closing XOManager for DuctileDB...");
	xoManager.close();
	logger.debug("XOManager for DuctileDB closed.");
    }

}
