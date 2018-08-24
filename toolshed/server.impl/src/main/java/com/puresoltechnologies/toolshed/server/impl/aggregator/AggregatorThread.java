package com.puresoltechnologies.toolshed.server.impl.aggregator;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AggregatorThread implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(AggregatorThread.class);

    @Override
    public void run() {
	try {
	    for (;;) {
		logger.info("aggregating...");
		TimeUnit.SECONDS.sleep(3);
	    }
	} catch (InterruptedException e) {
	    if (logger.isDebugEnabled()) {
		logger.debug("Aggregator thread was interrupted.", e);
	    } else {
		logger.info("Aggregator thread was interrupted.");
	    }
	}
    }

}
