package com.puresoltechnologies.purifinity.coding.analysis.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PersistenceUtils {

    public static <T> T restore(File file) throws IOException {
	FileInputStream fileInputStream = new FileInputStream(file);
	try {
	    ObjectInputStream objectInputStream = new ObjectInputStream(
		    fileInputStream);
	    try {
		@SuppressWarnings("unchecked")
		T t = (T) objectInputStream.readObject();
		return t;
	    } catch (ClassNotFoundException e) {
		throw new RuntimeException(e);
	    } finally {
		objectInputStream.close();
	    }
	} finally {
	    fileInputStream.close();
	}
    }

    public static <T> void store(File file, T object) throws IOException {
	FileOutputStream fileInputStream = new FileOutputStream(file);
	try {
	    ObjectOutputStream objectInputStream = new ObjectOutputStream(
		    fileInputStream);
	    try {
		objectInputStream.writeObject(object);
	    } finally {
		objectInputStream.close();
	    }
	} finally {
	    fileInputStream.close();
	}
    }

}
