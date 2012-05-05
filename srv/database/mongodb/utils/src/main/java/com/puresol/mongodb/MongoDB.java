package com.puresol.mongodb;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class MongoDB {

    private static final int TIMEOUT_INTERVAL = 60000;
    private static final int SLEEP_INTERVAL = 100;

    private static final MongoDB INSTANCE = new MongoDB();

    public static MongoDB getInstance() {
	return INSTANCE;
    }

    private Process process = null;

    private MongoDB() {
	super();
    }

    public void start() throws IOException, TimeoutException,
	    InterruptedException {
	if (process == null) {
	    process = Runtime.getRuntime().exec("/opt/mongodb/bin/mongod");
	    waitUntilUp();
	}
    }

    private void waitUntilUp() throws TimeoutException, InterruptedException {
	int time = 0;
	while (!isRunning()) {
	    Thread.sleep(SLEEP_INTERVAL);
	    time += SLEEP_INTERVAL;
	    if (time >= TIMEOUT_INTERVAL) {
		throw new TimeoutException("MongoDB is not answering!");
	    }
	}
    }

    public void stop() {
	if (process != null) {
	    process.destroy();
	    process = null;
	}
    }

    public boolean isRunning() {
	return (process != null) && (isStarted());
    }

    private static boolean isStarted() {
	try {
	    Mongo mongo = new Mongo("localhost");
	    mongo.getDatabaseNames();
	    return true;
	} catch (UnknownHostException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    return false;
	} catch (MongoException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    return false;
	}
    }

}
