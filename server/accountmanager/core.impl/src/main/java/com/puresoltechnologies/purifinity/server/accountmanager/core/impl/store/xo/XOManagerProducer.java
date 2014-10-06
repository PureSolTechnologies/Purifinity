package com.puresoltechnologies.purifinity.server.accountmanager.core.impl.store.xo;

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
	xoManagerFactory = XO
		.createXOManagerFactory(UsersXOManager.XO_UNIT_NAME);
    }

    @PreDestroy
    public void preDestroy() {
	xoManagerFactory.close();
    }

    @Produces
    @UsersXOManager
    public XOManager produceXOManager() {
	logger.debug("Creating XOManager for User Management...");
	XOManager xoManager = xoManagerFactory.createXOManager();
	logger.debug("User Management for Titan created.");
	return xoManager;
    }

    public void closeXOManager(@Disposes @UsersXOManager XOManager xoManager) {
	logger.debug("Closing XOManager for User Management...");
	xoManager.close();
	logger.debug("XOManager for User Management closed.");
    }

}
