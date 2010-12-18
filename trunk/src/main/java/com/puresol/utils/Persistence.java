package com.puresol.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This class is for simple object persistence. It stores and loads objects to
 * and from files by Java's internal serialization mechanism. This class is just
 * for simple usage.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Persistence {

	/**
	 * This method persists an object into a specified file.
	 * 
	 * @param o
	 * @param file
	 * @throws PersistenceException
	 */
	public static void persist(Object o, File file) throws IOException {
		ObjectOutputStream oos = null;
		try {
			File directory = file.getParentFile();
			if (!directory.exists()) {
				if (!directory.mkdirs()) {
					throw new IOException(
							"Could not create necessary directory '"
									+ directory + "'!");
				}
			}
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(o);
			oos.close();
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	public static Object restore(File file) throws IOException,
			PersistenceException {
		return restore(new FileInputStream(file));
	}

	public static Object restore(InputStream inputStream) throws IOException,
			PersistenceException {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(inputStream);
			Object o = ois.readObject();
			ois.close();
			return o;
		} catch (ClassNotFoundException e) {
			throw new PersistenceException(e);
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e1) {
				}
			}
		}
	}

}
