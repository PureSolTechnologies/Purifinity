package com.puresol.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

public class Persistence {

    private static final Logger logger = Logger.getLogger(Persistence.class);

    public static void persist(Object o, File file) throws PersistenceException {
	ObjectOutputStream oos = null;
	try {
	    oos = new ObjectOutputStream(new FileOutputStream(file));
	    oos.writeObject(o);
	    oos.close();
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	    if (oos != null) {
		try {
		    oos.close();
		} catch (IOException e1) {
		}
	    }
	    throw new PersistenceException(e);
	}
    }

    public static Object restore(File file) throws PersistenceException {
	ObjectInputStream ois = null;
	try {
	    ois = new ObjectInputStream(new FileInputStream(file));
	    Object o = ois.readObject();
	    ois.close();
	    return o;
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	    if (ois != null) {
		try {
		    ois.close();
		} catch (IOException e1) {
		}
	    }
	    throw new PersistenceException(e);
	} catch (ClassNotFoundException e) {
	    if (ois != null) {
		try {
		    ois.close();
		} catch (IOException e1) {
		}
	    }
	    throw new PersistenceException(e);
	}
    }

}
