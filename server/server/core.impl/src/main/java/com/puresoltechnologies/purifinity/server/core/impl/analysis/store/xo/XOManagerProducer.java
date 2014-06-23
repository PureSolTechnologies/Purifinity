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
	xoManagerFactory = XO.createXOManagerFactory(TitanXOUnit.XO_UNIT_NAME);
    }

    @PreDestroy
    public void preDestroy() {
	xoManagerFactory.close();
    }

    @Produces
    @TitanXOUnit
    public XOManager produceXOManager() {
	logger.debug("Creating XOManager for Titan...");
	XOManager xoManager = xoManagerFactory.createXOManager();
	logger.debug("XOManager for Titan created.");
	return xoManager;
    }

    public void closeXOManager(@Disposes @TitanXOUnit XOManager xoManager) {
	logger.debug("Closing XOManager for Titan...");
	xoManager.close();
	logger.debug("XOManager for Titan closed.");
    }

}
