package org.mongodb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mongodb.Mongo;
import com.puresol.mongodb.MongoDB;

public class MongoDBTest {

    private static final MongoDB MONGO_DB = MongoDB.getInstance();

    @BeforeClass
    public static void initializeMongoDB() throws Exception {
	MONGO_DB.start();
    }

    @AfterClass
    public static void destroyMongoDB() {
	MONGO_DB.stop();
    }

    @Before
    public void setup() {
	assertTrue(MONGO_DB.isRunning());
    }

    @Test
    public void test() throws Exception {
	Mongo mongo = new Mongo();
	List<String> databaseNames = mongo.getDatabaseNames();
	assertNotNull(databaseNames);
	for (String databaseName : databaseNames) {
	    System.out.println(databaseName);
	}
    }

}
